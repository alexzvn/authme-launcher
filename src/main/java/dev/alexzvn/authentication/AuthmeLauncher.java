package dev.alexzvn.authentication;

import java.net.InetSocketAddress;

import org.bukkit.plugin.java.JavaPlugin;

public class AuthmeLauncher extends JavaPlugin {
    private static AuthmeLauncher instance;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
    }

    public static AuthmeLauncher getInstance() {
        return instance;
    }
}
