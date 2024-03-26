package dev.twme.debugstickpro.hook;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class CoreProtectUtil {
    private static CoreProtectAPI coreProtectAPI;
    private static boolean isCoreProtectLoaded = true;

    public static boolean initCoreProtect() {
        try {

            Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");

            // Check that CoreProtect is loaded
            if (!(plugin instanceof CoreProtect)) {
                isCoreProtectLoaded = false;
            }

            // Check that the API is enabled
            CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
            if (!CoreProtect.isEnabled()) {
                isCoreProtectLoaded = false;
            }

            // Check that a compatible version of the API is loaded
            if (CoreProtect.APIVersion() < 9) {
                isCoreProtectLoaded = false;
            }
            coreProtectAPI = CoreProtect;
            return isCoreProtectLoaded;
        } catch (NoClassDefFoundError e) {
            isCoreProtectLoaded = false;
            return false;
        }
    }

    public static CoreProtectAPI getCoreProtectAPI() {
        return coreProtectAPI;
    }

    public static void logBlockPlace(String playerName, Location location, BlockData blockData) {
        if (isCoreProtectLoaded) {
            coreProtectAPI.logPlacement(playerName, location, blockData.getMaterial(), blockData);
        }
    }

    public static void logBlockBreak(String playerName, Location location, BlockData blockData) {
        if (isCoreProtectLoaded) {
            coreProtectAPI.logRemoval(playerName, location, blockData.getMaterial(), blockData);
        }
    }
}
