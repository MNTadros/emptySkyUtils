package com.empty.emptyskyutils.commands;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.inventories.originSelection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class originOpen implements CommandExecutor {
    private final EmptySkyUtils plugin;

    public originOpen(EmptySkyUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[!] Only players can use this command! [!]");
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("origin")) {
            originSelection gui = new originSelection(plugin);
            player.openInventory(gui.getInventory());
            player.sendMessage("§6[!] Opening origin selection! [!]");
            return true;
        }
        return true;
    }
}
