package me.controls.disarm.hub;

import me.controls.disarm.hub.command.FlyCommand;
import me.controls.disarm.hub.listener.HubListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends JavaPlugin {
    public static Hub instance;

    public Hub() {
        super();
    }

    public void onEnable() {
        Hub.instance = this;
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Hub plugin enabled");
        registerCommands();
        registerListeners();
    }

    public void onDisable() {
        Hub.instance = null;
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Hub plugin disabled");
    }

    public void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand());
    }

    public void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new HubListener(), this);
    }
}
