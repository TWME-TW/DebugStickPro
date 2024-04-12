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
import dev.twme.debugstickpro.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class DebugStickPro extends JavaPlugin {
    /**
     * This is the instance of the plugin
     */

    private static DebugStickPro instance;

    /**
     * This is the task ID of the action bar task
     */
    private int actionBarTaskID;

    /**
     * This is the version of the plugin
     */
    public static final int CONFIG_VERSION = 5;

    /**
     * This is the version of the language file
     */

    public static final int LANG_VERSION = 3;

    /**
     * This method is called when the plugin is enabled
     */

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

    /**
     * This method is called when the plugin is reloaded
     */

    public void onReload() {
        FreezeBlockManager.removeOnServerClose();
        unregisterTasks();

        ConfigLoader.getInstance().load();
        LangFileManager.initialization();
        registerTasks();
        BlockDataSeparater.clearCache();
    }

    /**
     * This method is called when the plugin is disabled
     */

    @Override
    public void onDisable() {
        FreezeBlockManager.removeOnServerClose();
    }

    /**
     * This method registers the commands
     */

    private void registerCommands() {
        getCommand("debugstickpro").setExecutor(new MainCommand());
        getCommand("debugstickpro").setTabCompleter(new MainCommandTabComplete());
    }

    /**
     * This method registers the listeners
     */

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

    /**
     * This method makes it easier to register listeners
     */

    private void registerListener(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
    }

    /**
     * This method registers the tasks
     */

    private void registerTasks() {

        // this is a task that will display the action bar
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        actionBarTaskID = scheduler.scheduleSyncRepeatingTask(this, new ActionBarDisplayTask(), 0L, ConfigFile.ActionBarDisplay.UpdateInterval);
    }

    /**
     * This method unregisters the tasks
     */

    private void unregisterTasks() {
        Bukkit.getScheduler().cancelTask(actionBarTaskID);
    }

    /**
     * This method returns the instance of the plugin
     */

    public static DebugStickPro getInstance() {
        return instance;
    }
}
