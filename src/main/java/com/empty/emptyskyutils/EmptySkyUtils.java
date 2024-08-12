package com.empty.emptyskyutils;

import com.empty.emptyskyutils.commands.skyUtils;
import com.empty.emptyskyutils.events.enchantShardApplication;
import com.empty.emptyskyutils.events.mobBoxEntityDeath;
import com.empty.emptyskyutils.events.mobBoxEvents;
import com.empty.emptyskyutils.events.shardEntityDeath;
import com.empty.emptyskyutils.items.enchantShard;
import com.empty.emptyskyutils.items.mobBox;
import com.empty.emptyskyutils.items.spawnerShard;
import com.empty.emptyskyutils.recipes.craftShardsToSpawners;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class EmptySkyUtils extends JavaPlugin {
    private FileConfiguration mobBoxConfig;
    private File mobBoxFile;

    @Override
    public void onEnable() {
        loadMobBoxConfig();
        this.saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        spawnerShard.init();
        enchantShard.init();
        mobBox.init();
        craftShardsToSpawners craftShardsToSpawners = new craftShardsToSpawners(this);
        craftShardsToSpawners.registerRecipes();
        this.getCommand("emptySkyUtils").setExecutor(new skyUtils(this));
        Bukkit.getPluginManager().registerEvents(new mobBoxEntityDeath(this), this);
        Bukkit.getPluginManager().registerEvents(new shardEntityDeath(this), this);
        Bukkit.getPluginManager().registerEvents(new enchantShardApplication(this), this);
        Bukkit.getPluginManager().registerEvents(new mobBoxEvents(this), this);
        getServer().getConsoleSender().sendMessage("§l[emptySkyUtils] §6Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§l[emptySkyUtils] §6Plugin disabled!");
    }

    public void loadMobBoxConfig() {
        mobBoxFile = new File(getDataFolder(), "mobBoxes.yml");
        if (!mobBoxFile.exists()) {
            saveResource("mobBoxes.yml", false);
        }
        mobBoxConfig = YamlConfiguration.loadConfiguration(mobBoxFile);
    }

    public FileConfiguration getMobBoxConfig() {
        return mobBoxConfig;
    }

}
