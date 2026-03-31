package com.example.epicfightsmartitems.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.example.epicfightsmartitems.EpicFightSmartItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SmartItemsConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("epicfight-smart-items.json");

    public static final Codec<SmartItemsConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.list(ItemSelector.CODEC).fieldOf("items").forGetter(c -> c.items)
    ).apply(instance, SmartItemsConfig::new));

    private final List<ItemSelector> items;

    public SmartItemsConfig(List<ItemSelector> items) {
        this.items = items;
    }

    public static SmartItemsConfig createDefault() {
        return new SmartItemsConfig(new ArrayList<>());
    }

    public boolean matchesAny(ItemStack stack) {
        for (ItemSelector selector : items) {
            if (selector.matches(stack)) return true;
        }
        return false;
    }

    public List<ItemSelector> getItems() {
        return items;
    }

    public static SmartItemsConfig load() {
        if (!Files.exists(CONFIG_PATH)) {
            SmartItemsConfig defaultConfig = createDefault();
            defaultConfig.save();
            return defaultConfig;
        }

        try {
            String json = Files.readString(CONFIG_PATH);
            JsonElement element = GSON.fromJson(json, JsonElement.class);
            return CODEC.decode(JsonOps.INSTANCE, element)
                    .resultOrPartial(error -> EpicFightSmartItems.LOGGER.error("Failed to parse config: {}", error))
                    .map(pair -> pair.getFirst())
                    .orElseGet(SmartItemsConfig::createDefault);
        } catch (IOException e) {
            EpicFightSmartItems.LOGGER.error("Failed to load config", e);
            return createDefault();
        }
    }

    public void save() {
        try {
            JsonElement element = CODEC.encodeStart(JsonOps.INSTANCE, this)
                    .resultOrPartial(error -> EpicFightSmartItems.LOGGER.error("Failed to encode config: {}", error))
                    .orElse(null);
            if (element != null) {
                Files.writeString(CONFIG_PATH, GSON.toJson(element));
            }
        } catch (IOException e) {
            EpicFightSmartItems.LOGGER.error("Failed to save config", e);
        }
    }
}
