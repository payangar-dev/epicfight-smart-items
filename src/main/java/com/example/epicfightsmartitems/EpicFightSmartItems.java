package com.example.epicfightsmartitems;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import com.example.epicfightsmartitems.config.SmartItemsConfig;
import com.example.epicfightsmartitems.event.HotbarSelectionHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(EpicFightSmartItems.MODID)
public class EpicFightSmartItems {
    public static final String MODID = "epicfightsmartitems";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static SmartItemsConfig config;

    public EpicFightSmartItems() {
        LOGGER.info("Epic Fight Smart Items initializing...");

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new HotbarSelectionHandler());
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        config = SmartItemsConfig.load();
        LOGGER.info("Loaded {} item selectors", config.getItems().size());
    }

    public static SmartItemsConfig getConfig() {
        return config;
    }
}
