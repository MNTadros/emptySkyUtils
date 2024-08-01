package com.empty.emptyskyutils.commands;

import com.empty.emptyskyutils.EmptySkyUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class reloadConfig implements CommandExecutor {

    private EmptySkyUtils plugin;

    public reloadConfig(EmptySkyUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadconfig")) {
            plugin.reloadConfig();
            sender.sendMessage("emptySkyUtils configuration reloaded!");
            return true;
        }
        return false;
    }
}
