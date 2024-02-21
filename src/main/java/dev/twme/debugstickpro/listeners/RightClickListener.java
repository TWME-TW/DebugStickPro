package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.util.player.playerdata.PlayerData;
import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class RightClickListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onRightClick(PlayerInteractEvent event) {

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        Block block;
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }
        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        if (!DebugStickItemCheck.checkPlayer(player)) {
            return;
        }

        event.setCancelled(true);

        block = event.getClickedBlock();

        PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());
        playerData.changeValue();
    }
}
