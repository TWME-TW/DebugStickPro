package dev.twme.debugstickpro;

import dev.twme.debugstickpro.display.ActionBarDisplayTask;
import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.commmands.MainCommands;
import dev.twme.debugstickpro.listeners.*;
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
        registerTasks();
    }

    @Override
    public void onDisable() {
        FreezeBlockManager.removeOnServerClose();
    }

    private void registerCommands() {
        this.getCommand("debugstickpro").setExecutor(new MainCommands());
    }

    private void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new ChunkLoadEventListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceEventListenerCanBuildChecker(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChunkUnloadEventListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LeftClickListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerSwapHandItemsEventListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WorldUnloadEventListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerChangedWorldEventListener(), this);
    }

    private void registerTasks() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new ActionBarDisplayTask(), 0L, 1L);
    }

    public static DebugStickPro getInstance() {
        return instance;
    }
}
