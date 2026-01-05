package com.example.epicfightsmartitems;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import com.example.epicfightsmartitems.config.SmartItemsConfig;
import com.example.epicfightsmartitems.event.HotbarSelectionHandler;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;

@Mod(EpicFightSmartItems.MODID)
public class EpicFightSmartItems {
    public static final String MODID = "epicfightsmartitems";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    private static SmartItemsConfig config;

    public EpicFightSmartItems(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Epic Fight Smart Items initializing...");
        
        modEventBus.addListener(this::onCommonSetup);
        
        if (FMLEnvironment.dist == Dist.CLIENT) {
            NeoForge.EVENT_BUS.register(new HotbarSelectionHandler());
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
