package org.beastrein;

import org.beastrein.commands.home.*;
import org.beastrein.commands.info.discord;
import org.beastrein.commands.tp.Back;
import org.beastrein.commands.tp.Spawn;
import org.beastrein.commands.tp.tpWorld;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.sql.SQLException;
import java.util.ArrayList;

public final class ServerCore extends JavaPlugin {
    FileConfiguration c;
    static ServerCore plugin;
    public static ServerCore getPlugin() {
        return plugin;
    }
    public static ArrayList<String> muted = new ArrayList<>();

    @Override
    public BiomeProvider getDefaultBiomeProvider(String worldName, @Nullable String id) {
        return new SimpleBiomeProvider();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Homes
        tabComplete tabcomplete = new tabComplete();
        this.getCommand("copyHomes").setExecutor(new CopyHomes());
        this.getCommand("setHome").setExecutor(new setHome());
        this.getCommand("delHome").setExecutor(new deleteHome());
        this.getCommand("delHome").setTabCompleter(tabcomplete);
        this.getCommand("home").setExecutor(new Home());
        this.getCommand("home").setTabCompleter(tabcomplete);
        // operator commands
        this.getCommand("openInv").setExecutor(new openInv());
        this.getCommand("tpWorld").setExecutor(new tpWorld());
        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("fly").setExecutor(new Fly());
        this.getCommand("discord").setExecutor(new discord());
        this.getCommand("back").setExecutor(new Back());

        try {
            this.getConfig().load("homes.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        getServer().getPluginManager().registerEvents(new WorldProtection(), this);
        getServer().getPluginManager().registerEvents(new ChatManager(), this);
        getServer().getPluginManager().registerEvents(new PortalHandler(), this);

        if (Bukkit.getWorld("Survival") == null) {
            getServer().createWorld(new WorldCreator("Survival"));
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Don't forget to do /discord");
            }
        }, 0L, 24000L);

        plugin = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            DBManager.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
