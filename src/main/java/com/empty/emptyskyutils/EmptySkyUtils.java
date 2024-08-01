package com.empty.emptyskyutils;

import com.empty.emptyskyutils.events.onEntityDeath;
import com.empty.emptyskyutils.items.spawnerShard;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EmptySkyUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        spawnerShard.init();
        Bukkit.getPluginManager().registerEvents(new onEntityDeath(), this);
        getServer().getConsoleSender().sendMessage(NamedTextColor.GOLD + "§l[emptySkyUtils] §6Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(NamedTextColor.GOLD + "§l[emptySkyUtils] §6Plugin disabled!");
    }
}
