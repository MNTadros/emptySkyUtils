package com.empty.emptyskyutils.commands;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.items.enchantShard;
import com.empty.emptyskyutils.items.mobBox;
import org.bukkit.Bukkit;
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
        if (!command.getName().equalsIgnoreCase("emptyskyutils")) {
            return false;
        }

        if (!sender.hasPermission("emptyskyutils.admin")) {
            sender.sendMessage("§cYou do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§f[§bempty§7SkyUtils§f] Available commands:");
            sender.sendMessage("§7/esu reload");
            sender.sendMessage("§7/esu giveshards <player> <amount> [type]");
            sender.sendMessage("§7/esu giveboxes <player> <amount> [type]");
            sender.sendMessage(" ");
            sender.sendMessage("§bShard types: §7[looting, fortune, sharpness, smite, unbreaking, bane, protection, efficiency]");
            sender.sendMessage("§bBox types: §7[tier1, tier2, tier3, tier4, tier5, tier6, resource, boss]");
            return true;
        }

        // Check for reload command and permission
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("emptyskyutils.admin")) {
                sender.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }
            plugin.reloadConfig();
            plugin.loadMobBoxConfig();
            sender.sendMessage("§f[§bempty§7SkyUtils§f] Configuration reloaded!");
            return true;
        }

        // Check for other commands and permission
        if (args.length < 3) {
            sender.sendMessage("§cUsage: /esu <command> <player> <amount> [type]");
            return false;
        }

        // Check for giveshards command
        if (args[0].equalsIgnoreCase("giveshards")) {
            if (!(sender instanceof Player) && !sender.hasPermission("emptyskyutils.admin")) {
                sender.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }
            giveShards(sender, args[1], args[2], args.length > 3 ? args[3] : null);
            return true;
        }

        // Check for giveboxes command
        if (args[0].equalsIgnoreCase("giveboxes")) {
            if (!(sender instanceof Player) && !sender.hasPermission("emptyskyutils.admin")) {
                sender.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }
            giveBoxes(sender, args[1], args[2], args.length > 3 ? args[3] : null);
            return true;
        }

        sender.sendMessage("§cUnknown subcommand. Use /esu for a list of commands.");
        return false;
    }

    private void giveShards(CommandSender sender, String playerName, String amountStr, String type) {
        Player target = Bukkit.getPlayer(playerName);
        if (target == null) {
            sender.sendMessage("§cPlayer not found.");
            return;
        }

        int amount;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cInvalid number: " + amountStr);
            return;
        }

        if (amount <= 0) {
            sender.sendMessage("§cThe amount must be greater than 0.");
            return;
        }

        if (type == null) {
            // Give all shards
            for (int i = 0; i < amount; i++) {
                target.getInventory().addItem(enchantShard.lootingShard.clone());
                target.getInventory().addItem(enchantShard.fortuneShard.clone());
                target.getInventory().addItem(enchantShard.sharpnessShard.clone());
                target.getInventory().addItem(enchantShard.smiteShard.clone());
                target.getInventory().addItem(enchantShard.unbreakingShard.clone());
                target.getInventory().addItem(enchantShard.baneShard.clone());
                target.getInventory().addItem(enchantShard.protectionShard.clone());
                target.getInventory().addItem(enchantShard.efficiencyShard.clone());
            }
            sender.sendMessage("§f[§bempty§7SkyUtils§f] " + amount + " of each enchant shard given to " + target.getName() + "!");
        } else {
            // Give specific shard
            for (int i = 0; i < amount; i++) {
                switch (type.toLowerCase()) {
                    case "looting":
                        target.getInventory().addItem(enchantShard.lootingShard.clone());
                        break;
                    case "fortune":
                        target.getInventory().addItem(enchantShard.fortuneShard.clone());
                        break;
                    case "sharpness":
                        target.getInventory().addItem(enchantShard.sharpnessShard.clone());
                        break;
                    case "smite":
                        target.getInventory().addItem(enchantShard.smiteShard.clone());
                        break;
                    case "unbreaking":
                        target.getInventory().addItem(enchantShard.unbreakingShard.clone());
                        break;
                    case "bane":
                        target.getInventory().addItem(enchantShard.baneShard.clone());
                        break;
                    case "protection":
                        target.getInventory().addItem(enchantShard.protectionShard.clone());
                        break;
                    case "efficiency":
                        target.getInventory().addItem(enchantShard.efficiencyShard.clone());
                        break;
                    default:
                        sender.sendMessage("§cUnknown shard type. Available types: looting, fortune, sharpness, smite, unbreaking, bane, protection, efficiency.");
                        return;
                }
            }
            sender.sendMessage("§f[§bempty§7SkyUtils§f] " + amount + " " + type + " shard(s) given to " + target.getName() + "!");
        }
    }

    private void giveBoxes(CommandSender sender, String playerName, String amountStr, String type) {
        Player target = Bukkit.getPlayer(playerName);
        if (target == null) {
            sender.sendMessage("§cPlayer not found.");
            return;
        }

        int amount;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cInvalid number: " + amountStr);
            return;
        }

        if (amount <= 0) {
            sender.sendMessage("§cThe amount must be greater than 0.");
            return;
        }

        if (type == null) {
            // Give all boxes
            for (int i = 0; i < amount; i++) {
                target.getInventory().addItem(mobBox.tier1mobBox.clone());
                target.getInventory().addItem(mobBox.tier2mobBox.clone());
                target.getInventory().addItem(mobBox.tier3mobBox.clone());
                target.getInventory().addItem(mobBox.tier4mobBox.clone());
                target.getInventory().addItem(mobBox.tier5mobBox.clone());
                target.getInventory().addItem(mobBox.tier6mobBox.clone());
                target.getInventory().addItem(mobBox.resourcemobBox.clone());
                target.getInventory().addItem(mobBox.bossmobBox.clone());
            }
            sender.sendMessage("§f[§bempty§7SkyUtils§f] " + amount + " of each MobBox given to " + target.getName() + "!");
        } else {
            // Give specific box
            for (int i = 0; i < amount; i++) {
                switch (type.toLowerCase()) {
                    case "tier1":
                        target.getInventory().addItem(mobBox.tier1mobBox.clone());
                        break;
                    case "tier2":
                        target.getInventory().addItem(mobBox.tier2mobBox.clone());
                        break;
                    case "tier3":
                        target.getInventory().addItem(mobBox.tier3mobBox.clone());
                        break;
                    case "tier4":
                        target.getInventory().addItem(mobBox.tier4mobBox.clone());
                        break;
                    case "tier5":
                        target.getInventory().addItem(mobBox.tier5mobBox.clone());
                        break;
                    case "tier6":
                        target.getInventory().addItem(mobBox.tier6mobBox.clone());
                        break;
                    case "resource":
                        target.getInventory().addItem(mobBox.resourcemobBox.clone());
                        break;
                    case "boss":
                        target.getInventory().addItem(mobBox.bossmobBox.clone());
                        break;
                    default:
                        sender.sendMessage("§cUnknown box type. Available types: tier1, tier2, tier3, tier4, tier5, tier6, resource, boss.");
                        return;
                }
            }
            sender.sendMessage("§f[§bempty§7SkyUtils§f] " + amount + " " + type + " box(es) given to " + target.getName() + "!");
        }
    }
}
