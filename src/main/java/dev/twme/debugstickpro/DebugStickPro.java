package dev.twme.debugstickpro;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.configs.ConfigFile;
import dev.twme.debugstickpro.configs.ConfigLoader;
import dev.twme.debugstickpro.configs.LangLoader;
import dev.twme.debugstickpro.display.ActionBarDisplayTask;
import dev.twme.debugstickpro.hook.CoreProtectUtil;
import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.commands.MainCommands;
import dev.twme.debugstickpro.listeners.*;
import dev.twme.debugstickpro.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class DebugStickPro extends JavaPlugin {
    private static DebugStickPro instance;
    private int taskID;

    // TODO: 如果更改此值，請確保在 config.yml 中也更改了相應的值
    public static final int ConfigVersion = 3;

    // TODO: 如果更改此值，請確保在 lang.yml 中也更改了相應的值
    public static final int LangVersion = 1;

    @Override
    public void onEnable() {
        instance = this;

        boolean isCoreProtectLoaded = CoreProtectUtil.initCoreProtect();
        if (!isCoreProtectLoaded) {
            Log.warning("CoreProtect is not loaded or is not compatible with this version of the plugin.");
        }

        ConfigLoader.getInstance().load();
        LangLoader.getInstance().load();

        registerCommands();
        registerListeners();
        registerTasks();
    }

    public void onReload() {
        FreezeBlockManager.removeOnServerClose();
        unregisterTasks();
        ConfigLoader.getInstance().load();
        LangLoader.getInstance().load();
        registerTasks();
        BlockDataSeparater.clearCache();
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
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerChangeDebugStickModeEventListener(), this);
    }

    private void registerTasks() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(this, new ActionBarDisplayTask(), 0L, ConfigFile.ActionBarDisplay.UpdateInterval);
    }

    private void unregisterTasks() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public static DebugStickPro getInstance() {
        return instance;
    }
}
