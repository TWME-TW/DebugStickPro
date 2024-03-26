package dev.twme.debugstickpro.util;

import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.listeners.BlockPlaceEventListenerCanBuildChecker;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class AutoCheckCanChangeUtil {
    public static boolean canChange(UUID playerUUID, Block block) {
        Player player = Bukkit.getPlayer(playerUUID);
        World world = block.getWorld();

        boolean canChange = true;

        if (ConfigFile.WhitelistWorlds.Enabled) {
            if (!ConfigFile.WhitelistWorlds.Worlds.contains(world.getName())) {
                canChange = false;

            }
            if (ConfigFile.WhitelistWorlds.Worlds.contains("*")) {
                canChange = true;
            }
        }

        if (ConfigFile.BlacklistWorlds.Enabled) {
            if (ConfigFile.BlacklistWorlds.Worlds.contains(world.getName())) {
                canChange = false;
            }
        }

        if (ConfigFile.AutoRegionProtection.Enabled) {
            if (canChange) {
                if (!BlockPlaceEventListenerCanBuildChecker.canBuild(block, playerUUID)) {
                    canChange = false;
                }
            }
        }
        if (player.hasPermission("debugstickpro.bypassregion")) {
            canChange = true;
        }

        return canChange;
    }
}
