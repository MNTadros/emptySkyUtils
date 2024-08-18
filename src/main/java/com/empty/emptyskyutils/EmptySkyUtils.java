package com.empty.emptyskyutils;

import com.empty.emptyskyutils.commands.skyUtils;
import com.empty.emptyskyutils.events.*;
import com.empty.emptyskyutils.items.enchantShard;
import com.empty.emptyskyutils.items.mobBox;
import com.empty.emptyskyutils.items.spawnerShard;
import com.empty.emptyskyutils.recipes.craftShardsToSpawners;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class EmptySkyUtils extends JavaPlugin {
    private FileConfiguration mobBoxConfig;
    private File mobBoxFile;
    private originEvents originEvents;

    @Override
    public void onEnable() {
        loadMobBoxConfig();

        originEvents = new originEvents(this);
        Bukkit.getPluginManager().registerEvents(originEvents, this);

        mobBox.init();
        spawnerShard.init();
        enchantShard.init();

        craftShardsToSpawners craftShardsToSpawners = new craftShardsToSpawners(this);
        craftShardsToSpawners.registerRecipes();

        this.getCommand("emptySkyUtils").setExecutor(new skyUtils(this));
        getServer().getPluginManager().registerEvents(new mobBoxEntityDeath(this), this);
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

        try (BufferedReader reader = new BufferedReader(new FileReader(mobBoxFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                getLogger().info(line);
            }
        } catch (IOException e) {
            getLogger().warning("Error reading mobBoxes.yml file.");
        }

        ConfigurationSection mobBoxDrops = mobBoxConfig.getConfigurationSection("mobBoxDrops");
        if (mobBoxDrops == null) {
            getLogger().warning("No mobBoxDrops section found in the config.");
        } else {
            getLogger().info("mobBoxDrops section found in the config.");
            for (String key : mobBoxDrops.getKeys(false)) {
                getLogger().info("Tier found: " + key);
            }
        }
    }


    public FileConfiguration getMobBoxConfig() {
        return mobBoxConfig;
    }

    public originEvents getOriginEvents() {
        return originEvents;
    }
}