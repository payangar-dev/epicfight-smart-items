package com.example.epicfightsmartitems.event;

import com.example.epicfightsmartitems.EpicFightSmartItems;
import com.example.epicfightsmartitems.config.SmartItemsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

public class HotbarSelectionHandler {
    private int lastSelectedSlot = -1;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!event.player.level().isClientSide()) return;

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) return;
        if (event.player != player) return;

        SmartItemsConfig config = EpicFightSmartItems.getConfig();
        if (config == null) return;

        int currentSlot = player.getInventory().selected;

        if (lastSelectedSlot != currentSlot) {
            lastSelectedSlot = currentSlot;

            ItemStack heldItem = player.getMainHandItem();
            LocalPlayerPatch playerPatch = EpicFightCapabilities.getEntityPatch(player, LocalPlayerPatch.class);
            if (playerPatch == null) return;

            if (config.matchesAny(heldItem)) {
                if (playerPatch.isEpicFightMode()) {
                    playerPatch.toVanillaMode(true);
                    EpicFightSmartItems.LOGGER.debug("Switched {} to mining mode", player.getName().getString());
                }
            } else {
                if (playerPatch.isVanillaMode()) {
                    playerPatch.toEpicFightMode(true);
                    EpicFightSmartItems.LOGGER.debug("Switched {} to combat mode", player.getName().getString());
                }
            }
        }
    }
}
