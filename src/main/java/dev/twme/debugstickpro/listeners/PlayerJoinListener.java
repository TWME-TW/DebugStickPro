package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.util.actionbar.TargetBlockTask;
import dev.twme.debugstickpro.util.player.playerdata.PlayerData;
import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData(player.getUniqueId());
        PlayerDataManager.setPlayerData(player.getUniqueId(), playerData);

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();

        if (DebugStickItemCheck.isDebugStickItem(item)) {
            PlayerDataManager.addPlayerEnableDisplay(player.getUniqueId());
        }
    }
}
