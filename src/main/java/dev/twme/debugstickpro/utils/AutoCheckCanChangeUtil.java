package dev.twme.debugstickpro.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.listeners.BlockPlaceEventListenerCanBuildChecker;

public final class AutoCheckCanChangeUtil {
    /**
     * Check if the player can change the block
     *
     * @param playerUUID player UUID
     * @param block block
     * @return true if the player can change the block
     */
    public static boolean canChange(UUID playerUUID, Block block) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) {
            return false;
        }

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

        if (ConfigFile.AutoRegionProtection.Enabled && canChange && !player.hasPermission("debugstickpro.bypassregion")) {
            if (!BlockPlaceEventListenerCanBuildChecker.canBuild(block, playerUUID)) {
                canChange = false;
            }
        }

        if (canChange && !BlockFilterUtil.isAllowed(player, block)) {
            canChange = false;
        }

        return canChange;
    }
}
