package dev.twme.debugstickpro.utils;

import java.util.UUID;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import dev.twme.debugstickpro.config.ConfigFile;

public final class BlockFilterUtil {

    /**
     * Check if the block is allowed by the BlockFilter settings.
     *
     * @param playerUUID player UUID
     * @param block      the block to check
     * @return true if the block is allowed, false if it is filtered out
     */
    public static boolean isAllowed(UUID playerUUID, Block block) {
        Player player = org.bukkit.Bukkit.getPlayer(playerUUID);
        if (player == null) {
            return false;
        }

        // bypass permission
        if (player.hasPermission("debugstickpro.bypassblockfilter")) {
            return true;
        }

        String materialName = block.getType().name();

        // Whitelist check
        if (ConfigFile.BlockFilter.Whitelist.Enabled) {
            boolean inWhitelist = ConfigFile.BlockFilter.Whitelist.Blocks.contains("*")
                    || ConfigFile.BlockFilter.Whitelist.Blocks.contains(materialName);
            if (!inWhitelist) {
                return false;
            }
        }

        // Blacklist check (takes precedence)
        if (ConfigFile.BlockFilter.Blacklist.Enabled) {
            if (ConfigFile.BlockFilter.Blacklist.Blocks.contains(materialName)) {
                return false;
            }
        }

        return true;
    }
}
