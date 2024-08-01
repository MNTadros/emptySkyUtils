package com.empty.emptyskyutils;

import com.empty.emptyskyutils.commands.reloadConfig;
import com.empty.emptyskyutils.events.onEntityDeath;
import com.empty.emptyskyutils.items.spawnerShard;
import com.empty.emptyskyutils.recipes.craftShardsToSpawners;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EmptySkyUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        spawnerShard.init();
        craftShardsToSpawners craftShardsToSpawners = new craftShardsToSpawners(this);
        craftShardsToSpawners.registerRecipes();
        this.getCommand("reloadconfig").setExecutor(new reloadConfig(this));
        Bukkit.getPluginManager().registerEvents(new onEntityDeath(this), this);
        getServer().getConsoleSender().sendMessage("§l[emptySkyUtils] §6Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§l[emptySkyUtils] §6Plugin disabled!");
    }
}
