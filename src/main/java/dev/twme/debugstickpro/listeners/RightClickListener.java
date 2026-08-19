package dev.twme.debugstickpro.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.playerdata.DebugStickMode;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.DebugStickItem;

public class RightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }

        if (!DebugStickItem.checkPlayer(player)) {
            return;
        }

        Block targetBlock = event.getClickedBlock();
        if (targetBlock == null) {
            targetBlock = player.getTargetBlockExact(5);
        }

        // In classic mode, don't cancel the event if the target block has no available SubBlockData
        PlayerData playerData = PlayerDataManager.getOrCreatePlayerData(player.getUniqueId());
        if (playerData.getDebugStickMode() == DebugStickMode.CLASSIC) {
            if (targetBlock == null || BlockDataSeparater.separate(targetBlock, player.getUniqueId()).isEmpty()) {
                return;
            }
        }

        event.setCancelled(true);

        // The vanilla client may follow a main-hand PASS with an off-hand interaction.
        // Consume that event, but only execute the Debug Stick action for the main hand.
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        PlayerDataManager.playerRightClick(
                player.getUniqueId(),
                event.getAction(),
                targetBlock,
                event.getClickedBlock(),
                event.getBlockFace()
        );
    }
}
