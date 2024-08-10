package com.empty.emptyskyutils;

import com.empty.emptyskyutils.commands.skyUtils;
import com.empty.emptyskyutils.events.enchantShardApplication;
import com.empty.emptyskyutils.events.shardEntityDeath;
import com.empty.emptyskyutils.items.enchantShard;
import com.empty.emptyskyutils.items.mobBox;
import com.empty.emptyskyutils.items.spawnerShard;
import com.empty.emptyskyutils.recipes.craftShardsToSpawners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EmptySkyUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        spawnerShard.init();
        enchantShard.init();
        mobBox.init();
        craftShardsToSpawners craftShardsToSpawners = new craftShardsToSpawners(this);
        craftShardsToSpawners.registerRecipes();
        this.getCommand("emptyskyUtilsreload").setExecutor(new skyUtils(this));
        this.getCommand("emptySkyUtils").setExecutor(new skyUtils(this));
        this.getCommand("giveshards").setExecutor(new skyUtils(this));
        this.getCommand("giveboxes").setExecutor(new skyUtils(this));
        Bukkit.getPluginManager().registerEvents(new shardEntityDeath(this), this);
        Bukkit.getPluginManager().registerEvents(new enchantShardApplication(this), this);
        getServer().getConsoleSender().sendMessage("§l[emptySkyUtils] §6Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§l[emptySkyUtils] §6Plugin disabled!");
    }
}
