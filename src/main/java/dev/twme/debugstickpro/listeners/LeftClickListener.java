package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.DebugStickItem;
import dev.twme.debugstickpro.utils.SendFakeBarrier;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class LeftClickListener implements Listener {
    @EventHandler
    public void onLeftClickEvent(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Location blockLocation = Objects.requireNonNull(event.getClickedBlock()).getLocation();
            if (FreezeBlockManager.isFreezeBlock(blockLocation)) {
                SendFakeBarrier.sendFakeBarrier(player, blockLocation);
            }
        }

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        if (!DebugStickItem.checkPlayer(player)) {
            return;
        }

        event.setCancelled(true);

        // TODO: 未來改成這個版本
        PlayerDataManager.playerLeftClick(player.getUniqueId());

    }
}
