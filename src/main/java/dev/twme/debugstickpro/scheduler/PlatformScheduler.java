package dev.twme.debugstickpro.scheduler;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Routes work through the scheduler required by the running server.
 */
public final class PlatformScheduler {
    private static final boolean FOLIA = detectFolia();

    private PlatformScheduler() {
    }

    public static Cancellable runGlobalAtFixedRate(Plugin plugin, Runnable runnable, long periodTicks) {
        long period = Math.max(1L, periodTicks);
        if (FOLIA) {
            ScheduledTask task = Bukkit.getGlobalRegionScheduler()
                    .runAtFixedRate(plugin, ignored -> runnable.run(), 1L, period);
            return task::cancel;
        }

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, runnable, 1L, period);
        return task::cancel;
    }

    public static void runForPlayer(Plugin plugin, Player player, Runnable runnable) {
        if (FOLIA) {
            player.getScheduler().run(plugin, ignored -> runnable.run(), null);
            return;
        }
        runnable.run();
    }

    public static void runAtLocationLater(Plugin plugin, Location location, Runnable runnable, long delayTicks) {
        long delay = Math.max(1L, delayTicks);
        if (FOLIA) {
            Bukkit.getRegionScheduler().runDelayed(plugin, location, ignored -> runnable.run(), delay);
            return;
        }
        Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
    }

    public static boolean isFolia() {
        return FOLIA;
    }

    private static boolean detectFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

    @FunctionalInterface
    public interface Cancellable {
        void cancel();
    }
}
