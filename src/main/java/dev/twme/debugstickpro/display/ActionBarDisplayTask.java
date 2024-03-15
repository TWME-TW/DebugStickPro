package dev.twme.debugstickpro.display;

import dev.twme.debugstickpro.mode.classic.ClassicActionBarDisplay;
import dev.twme.debugstickpro.mode.copy.CopyActionBarDisplay;
import dev.twme.debugstickpro.mode.freeze.FreezeActionBarDisplay;
import dev.twme.debugstickpro.playerdata.NewPlayerData;
import dev.twme.debugstickpro.playerdata.NewPlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionBarDisplayTask implements Runnable{
    @Override
    public void run() {
        for (UUID uuid : NewPlayerDataManager.getDisplaySet()) {
            Player player = Bukkit.getPlayer(uuid);

            Block block;
            if (player == null) {
                return;
            }
            block = player.getTargetBlockExact(5);


            NewPlayerData playerData = NewPlayerDataManager.getPlayerData(uuid);

            switch (playerData.getDebugStickMode()) {
                case Classic:
                    ActionbarUtil.sendActionBar(player, ClassicActionBarDisplay.getDisplay(uuid, block));
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
