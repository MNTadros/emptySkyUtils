package com.empty.emptyskyutils.commands;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.items.enchantShard;
import com.empty.emptyskyutils.items.mobBox;
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
            plugin.loadMobBoxConfig(); // Reload mobBoxes.yml
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
            ((Player) sender).getInventory().addItem(enchantShard.protectionShard);
            ((Player) sender).getInventory().addItem(enchantShard.efficiencyShard);
            return true;
        }

        if (command.getName().equalsIgnoreCase("giveboxes")) {
            sender.sendMessage("§f§l[§bempty§7SkyUtils§f§l] All MobBoxes given!");
            ((Player) sender).getInventory().addItem(mobBox.tier1mobBox);
            ((Player) sender).getInventory().addItem(mobBox.tier2mobBox);
            ((Player) sender).getInventory().addItem(mobBox.tier3mobBox);
            ((Player) sender).getInventory().addItem(mobBox.tier4mobBox);
            ((Player) sender).getInventory().addItem(mobBox.tier5mobBox);
            ((Player) sender).getInventory().addItem(mobBox.tier6mobBox);
            ((Player) sender).getInventory().addItem(mobBox.resourcemobBox);
            ((Player) sender).getInventory().addItem(mobBox.bossmobBox);
            return true;
        }

            return false;
        }
    }



