package dev.twme.debugstickpro;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.retrooper.packetevents.PacketEvents;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.commands.MainCommand;
import dev.twme.debugstickpro.commands.MainCommandTabComplete;
import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.config.ConfigLoader;
import dev.twme.debugstickpro.display.ActionBarDisplayTask;
import dev.twme.debugstickpro.hook.CoreProtectUtil;
import dev.twme.debugstickpro.hook.PlaceholderAPIUtil;
import dev.twme.debugstickpro.listeners.BlockBreakEventListener;
import dev.twme.debugstickpro.listeners.BlockPlaceEventListenerCanBuildChecker;
import dev.twme.debugstickpro.listeners.ChunkLoadEventListener;
import dev.twme.debugstickpro.listeners.ChunkUnloadEventListener;
import dev.twme.debugstickpro.listeners.FreezeBlockIsolationListener;
import dev.twme.debugstickpro.listeners.LeftClickListener;
import dev.twme.debugstickpro.listeners.PlayerChangeDebugStickModeEventListener;
import dev.twme.debugstickpro.listeners.PlayerChangedWorldEventListener;
import dev.twme.debugstickpro.listeners.PlayerDropItemListener;
import dev.twme.debugstickpro.listeners.PlayerItemHeldListener;
import dev.twme.debugstickpro.listeners.PlayerJoinListener;
import dev.twme.debugstickpro.listeners.PlayerLocaleChangeEventListener;
import dev.twme.debugstickpro.listeners.PlayerQuitListener;
import dev.twme.debugstickpro.listeners.PlayerSwapHandItemsEventListener;
import dev.twme.debugstickpro.listeners.RightClickListener;
import dev.twme.debugstickpro.listeners.WorldUnloadEventListener;
import dev.twme.debugstickpro.localization.LangFileManager;
import dev.twme.debugstickpro.localization.PlayerLanguageManager;
import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.mode.freeze.FreezePacketLayer;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.scheduler.PlatformScheduler;
import dev.twme.debugstickpro.utils.DebugStickItem;
import dev.twme.debugstickpro.utils.Log;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import io.github.twme.virtualentities.VirtualEntities;
import io.github.twme.virtualentities.VirtualEntityManager;

public final class DebugStickPro extends JavaPlugin {
    /**
     * This is the instance of the plugin
     */

    private static DebugStickPro instance;
    private VirtualEntityManager virtualEntityManager;

    /**
     * This is the task ID of the action bar task
     */
    private PlatformScheduler.Cancellable actionBarTask;

    /**
     * This is the version of the plugin
     */
    public static final int CONFIG_VERSION = 8;

    /**
     * This is the version of the language file
     */
    public static final int LANG_VERSION = 5;

    /**
     * This method is called when the plugin is loaded
     * It sets the PacketEvents API for Spigot
     */
    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        //On Bukkit, calling this here is essential, hence the name "load"
        PacketEvents.getAPI().load();
    }

    /**
     * This method is called when the plugin is enabled
     */

    @Override
    public void onEnable() {
        instance = this;

        //Initialize!
        PacketEvents.getAPI().init();
        FreezePacketLayer.initialize();
        virtualEntityManager = VirtualEntities.create();

        boolean isCoreProtectLoaded = CoreProtectUtil.initCoreProtect();
        if (!isCoreProtectLoaded) {
            Log.warning("CoreProtect is not loaded or is not compatible with this version of the plugin.");
        }

        boolean isPlaceholderAPILoaded = PlaceholderAPIUtil.initPlaceholderAPI();
        if (!isPlaceholderAPILoaded) {
            Log.warning("PlaceholderAPI is not loaded or is not compatible with this version of the plugin.");
        }

        ConfigLoader.getInstance().load();

        LangFileManager.initialization();

        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            Log.warning("The server is reloaded. The plugin may not work normally");
            onServerReloadCommand();
        }

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
        unregisterTasks();
        FreezePacketLayer.shutdown();
        FreezeBlockManager.removeOnServerClose();
        if (virtualEntityManager != null) {
            virtualEntityManager.close();
            virtualEntityManager = null;
        }
        //Terminate the instance (clean up process)
        PacketEvents.getAPI().terminate();
    }

    /*
     * When a stupid admin executes the /reload command
     */
    public void onServerReloadCommand() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerLanguageManager.setPlayerLocale(player.getUniqueId(), player.getLocale());

            UUID playerUUID = player.getUniqueId();
            PlayerDataManager.setPlayerData(playerUUID, new PlayerData());

            if (!player.hasPermission("debugstickpro.use")) {
                continue;
            }
            ItemStack item = player.getInventory().getItemInMainHand();

            if (DebugStickItem.isDebugStickItem(item)) {
                PlayerDataManager.addPlayerToDisplayList(playerUUID);
            }
        }
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
        registerListener(new FreezeBlockIsolationListener());
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
        registerListener(new PlayerDropItemListener());
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
        actionBarTask = PlatformScheduler.runGlobalAtFixedRate(
                this,
                new ActionBarDisplayTask(this),
                ConfigFile.ActionBarDisplay.UpdateInterval
        );
    }

    /**
     * This method unregisters the tasks
     */

    private void unregisterTasks() {
        if (actionBarTask != null) {
            actionBarTask.cancel();
            actionBarTask = null;
        }
    }

    /**
     * This method returns the instance of the plugin
     */

    public static DebugStickPro getInstance() {
        return instance;
    }

    public VirtualEntityManager getVirtualEntityManager() {
        if (virtualEntityManager == null) {
            throw new IllegalStateException("VirtualEntities is not initialized");
        }
        return virtualEntityManager;
    }
}
