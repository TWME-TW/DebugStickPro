package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.util.DebugStickItemCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID playerUUID = event.getPlayer().getUniqueId();
        PlayerDataManager.setPlayerData(playerUUID, new PlayerData());

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();

        if (DebugStickItemCheck.isDebugStickItem(item)) {
            PlayerDataManager.addPlayerToDisplayList(playerUUID);
        }
    }
}
