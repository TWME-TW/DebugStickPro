package dev.twme.debugstickpro;

import dev.twme.debugstickpro.commmands.MainCommands;
import dev.twme.debugstickpro.listeners.LeftClickListener;
import dev.twme.debugstickpro.listeners.PlayerItemHeldListener;
import dev.twme.debugstickpro.listeners.PlayerQuitListener;
import dev.twme.debugstickpro.listeners.RightClickListener;
import dev.twme.debugstickpro.util.actionbar.ActionBarTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class DebugStickPro extends JavaPlugin {

    private static DebugStickPro instance;

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerListeners();

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new ActionBarTask(), 0L, 5L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        this.getCommand("debugstickpro").setExecutor(new MainCommands());
    }

    private void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LeftClickListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
    }

    public static DebugStickPro getInstance() {
        return instance;
    }
}
