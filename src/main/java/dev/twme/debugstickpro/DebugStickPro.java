package dev.twme.debugstickpro;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.commands.MainCommand;
import dev.twme.debugstickpro.commands.MainCommandTabComplete;
import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.config.ConfigLoader;
import dev.twme.debugstickpro.display.ActionBarDisplayTask;
import dev.twme.debugstickpro.hook.CoreProtectUtil;
import dev.twme.debugstickpro.listeners.*;
import dev.twme.debugstickpro.localization.LangFileManager;
import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class DebugStickPro extends JavaPlugin {
    private static DebugStickPro instance;
    private int taskID;

    // TODO: 如果更改此值，請確保在 config.yml 中也更改了相應的值
    public static final int CONFIG_VERSION = 4;

    // TODO: 如果更改此值，請確保在 lang/your_language.yml 中也更改了相應的值
    public static final int LANG_VERSION = 3;

    @Override
    public void onEnable() {
        instance = this;

        boolean isCoreProtectLoaded = CoreProtectUtil.initCoreProtect();
        if (!isCoreProtectLoaded) {
            Log.warning("CoreProtect is not loaded or is not compatible with this version of the plugin.");
        }


        ConfigLoader.getInstance().load();

        LangFileManager.initialization();


        registerCommands();
        registerListeners();
        registerTasks();
    }

    public void onReload() {
        FreezeBlockManager.removeOnServerClose();
        unregisterTasks();

        ConfigLoader.getInstance().load();
        LangFileManager.initialization();
        registerTasks();
        BlockDataSeparater.clearCache();
    }

    @Override
    public void onDisable() {
        FreezeBlockManager.removeOnServerClose();
    }

    private void registerCommands() {
        this.getCommand("debugstickpro").setExecutor(new MainCommand());
        this.getCommand("debugstickpro").setTabCompleter(new MainCommandTabComplete());
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
        registerListener(new PlayerLocaleChangeEventListener());
        registerListener(new PlayerLocaleChangeEventListener());
    }

    private void registerListener(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
    }

    private void registerTasks() {

        // this is a task that will display the action bar
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
