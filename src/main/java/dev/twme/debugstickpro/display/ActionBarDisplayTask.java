package dev.twme.debugstickpro.display;

import dev.twme.debugstickpro.mode.classic.ClassicActionBarDisplay;
import dev.twme.debugstickpro.mode.copy.CopyActionBarDisplay;
import dev.twme.debugstickpro.mode.freeze.FreezeActionBarDisplay;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionBarDisplayTask implements Runnable{
    @Override
    public void run() {
        for (UUID uuid : PlayerDataManager.getDisplaySet()) {
            Player player = Bukkit.getPlayer(uuid);

            Block block;
            if (player == null) {
                return;
            }
            block = player.getTargetBlockExact(5);


            PlayerData playerData = PlayerDataManager.getPlayerData(uuid);

            switch (playerData.getDebugStickMode()) {
                case Classic:
                    if (block == null) {
                        return;
                    }
                    ActionbarUtil.sendActionBar(player, ClassicActionBarDisplay.getDisplay(uuid, block.getBlockData()));
                    return;
                case Copy:
                    ActionbarUtil.sendActionBar(player, CopyActionBarDisplay.getDisplay(uuid));
                    return;
                case Freeze:
                    ActionbarUtil.sendActionBar(player, FreezeActionBarDisplay.getDisplay());
                    return;
            }
        }
    }
}
