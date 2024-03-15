package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.playerdata.NewPlayerData;
import dev.twme.debugstickpro.playerdata.NewPlayerDataManager;
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
        NewPlayerDataManager.setPlayerData(playerUUID, new NewPlayerData());

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();

        if (DebugStickItemCheck.isDebugStickItem(item)) {
            NewPlayerDataManager.addPlayerToDisplayList(playerUUID);
        }
    }
}
