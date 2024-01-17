package dev.twme.debugstickpro;

import dev.twme.debugstickpro.commmands.MainCommands;
import dev.twme.debugstickpro.listeners.LeftClickListener;
import dev.twme.debugstickpro.listeners.PlayerItemHeldListener;
import dev.twme.debugstickpro.listeners.PlayerQuitListener;
import dev.twme.debugstickpro.listeners.RightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DebugStickPro extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();
        registerListeners();
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
}
