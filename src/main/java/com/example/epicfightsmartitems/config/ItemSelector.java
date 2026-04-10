package com.example.epicfightsmartitems.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public record ItemSelector(
        Optional<ResourceLocation> id,
        Optional<ResourceLocation> tag,
        Optional<CompoundTag> nbt
) {
    public static final Codec<ItemSelector> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.optionalFieldOf("id").forGetter(ItemSelector::id),
            ResourceLocation.CODEC.optionalFieldOf("tag").forGetter(ItemSelector::tag),
            CompoundTag.CODEC.optionalFieldOf("nbt").forGetter(ItemSelector::nbt)
    ).apply(instance, ItemSelector::new));

    public boolean matches(ItemStack stack) {
        if (stack.isEmpty()) {
            return id.isPresent()
                && id.get().equals(ResourceLocation.withDefaultNamespace("air"));
        }

        // Check item ID
        if (id.isPresent()) {
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
            if (!id.get().equals(itemId)) return false;
        }

        // Check item tag
        if (tag.isPresent()) {
            TagKey<Item> tagKey = TagKey.create(Registries.ITEM, tag.get());
            if (!stack.is(tagKey)) return false;
        }

        // Check NBT data
        if (nbt.isPresent()) {
            CompoundTag stackTag = stack.getTag();
            if (stackTag == null) return false;
            if (!NbtUtils.compareNbt(nbt.get(), stackTag, true)) return false;
        }

        return true;
    }
}
