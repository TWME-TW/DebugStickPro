package dev.twme.debugstickpro.display;

import dev.twme.debugstickpro.mode.classic.ClassicActionBarDisplay;
import dev.twme.debugstickpro.mode.copy.CopyActionBarDisplay;
import dev.twme.debugstickpro.mode.freeze.FreezeActionBarDisplay;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.DebugStickItem;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.UUID;

public class ActionBarDisplayTask implements Runnable {
    @Override
    public void run() {
        Iterator<UUID> iterator = PlayerDataManager.getDisplaySet().iterator();
        while (iterator.hasNext()) {
            UUID uuid = iterator.next();
            Player player = Bukkit.getPlayer(uuid);

            // player is offline or something
            if (player == null) {
                iterator.remove();
                continue;
            }

            if (!player.hasPermission("debugstickpro.use")) {
                removeFromDisplayList(iterator, uuid);
                continue;
            }

            if (!DebugStickItem.checkPlayer(player)) {
                removeFromDisplayList(iterator, uuid);
                continue;
            }

            PlayerData playerData = PlayerDataManager.getOrCreatePlayerData(uuid);

            switch (playerData.getDebugStickMode()) {
                case CLASSIC:
                    Block block = player.getTargetBlockExact(5);
                    ActionbarUtil.sendActionBar(player, ClassicActionBarDisplay.getDisplay(uuid, block == null ? null : block.getBlockData()));
                    continue;
                case COPY:
                    ActionbarUtil.sendActionBar(player, CopyActionBarDisplay.getDisplay(uuid));
                    continue;
                case FREEZE:
                    ActionbarUtil.sendActionBar(player, FreezeActionBarDisplay.getDisplay(uuid));
                    continue;
            }
        }
    }

    private void removeFromDisplayList(Iterator<UUID> iterator, UUID uuid) {
        iterator.remove();
        ActionbarUtil.removeActionBar(uuid);
    }
}
