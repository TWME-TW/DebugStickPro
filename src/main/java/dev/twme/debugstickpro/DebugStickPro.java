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
import org.bukkit.event.Listener;
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
        registerListener(new ChunkLoadEventListener());
        registerListener(new BlockPlaceEventListenerCanBuildChecker());
        registerListener(new ChunkUnloadEventListener());
        registerListener(new RightClickListener());
        registerListener(new LeftClickListener());
        registerListener(new PlayerQuitListener());
        registerListener(new PlayerItemHeldListener());
        registerListener(new PlayerJoinListener());
        registerListener(new BlockBreakEventListener());
        registerListener(new PlayerSwapHandItemsEventListener());
        registerListener(new WorldUnloadEventListener());
        registerListener(new PlayerChangedWorldEventListener());
        registerListener(new PlayerChangeDebugStickModeEventListener());
    }

    private void registerListener(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
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
