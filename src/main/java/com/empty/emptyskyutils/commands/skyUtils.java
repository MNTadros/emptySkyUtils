package com.empty.emptyskyutils.commands;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.items.enchantShard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class skyUtils implements CommandExecutor {

    private EmptySkyUtils plugin;

    public skyUtils(EmptySkyUtils plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("emptySkyUtils")) {
            sender.sendMessage("§f§l[§b§lempty§7§lSkyUtils§f§l] Avaliable commands:");
            sender.sendMessage("§7§l-> §b/skyUtilsreload");
            return true;
        }

        if (command.getName().equalsIgnoreCase("emptyskyUtilsreload")) {
            plugin.reloadConfig();
            sender.sendMessage("§f§l[§bempty§7SkyUtils§f§l] Configuration reloaded!");
            return true;
        }

        if (command.getName().equalsIgnoreCase("giveshards")) {
            sender.sendMessage("§f§l[§bempty§7SkyUtils§f§l] All enchant shards given!");
            ((Player) sender).getInventory().addItem(enchantShard.lootingShard);
            ((Player) sender).getInventory().addItem(enchantShard.fortuneShard);
            ((Player) sender).getInventory().addItem(enchantShard.sharpnessShard);
            ((Player) sender).getInventory().addItem(enchantShard.smiteShard);
            ((Player) sender).getInventory().addItem(enchantShard.unbreakingShard);
            ((Player) sender).getInventory().addItem(enchantShard.baneShard);
            return true;
        }

            return false;
        }
    }



