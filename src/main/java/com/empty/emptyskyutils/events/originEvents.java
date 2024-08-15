package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.inventories.originSelection;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class originEvents implements Listener {
    private final EmptySkyUtils plugin;

    public originEvents(EmptySkyUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSelection(InventoryClickEvent event) {
        if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT || event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT || event.getClick() == ClickType.MIDDLE || event.getClick() == ClickType.UNKNOWN) {
            if (event.getInventory() == null || event.getCurrentItem() == null) {
                return;
            }
            if (event.getClickedInventory().getHolder() instanceof originSelection) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();

                if (event.getCurrentItem().getType() == Material.IRON_HORSE_ARMOR) {
                    player.sendMessage("§6[!] You have selected the §8§lPrisoner §6origin! [!]");
                    player.closeInventory();
                }
                if (event.getCurrentItem().getType() == Material.GOLD_INGOT) {
                    player.sendMessage("§6[!] You have selected the §3§lMerchant §6origin! [!]");
                    player.closeInventory();
                }
                if (event.getCurrentItem().getType() == Material.MAGMA_CREAM) {
                    player.sendMessage("§6[!] You have selected the §6§lMagma §6origin! [!]");
                    player.closeInventory();
                }
                if (event.getCurrentItem().getType() == Material.COAL_BLOCK) {
                    player.sendMessage("§6[!] You have selected the §f§lBandit §6origin! [!]");
                    player.closeInventory();
                }
                if (event.getCurrentItem().getType() == Material.BARRIER && event.getClickedInventory().getHolder() instanceof originSelection) {
                player.sendMessage("§6[!] You have closed the selection! [!]");
                player.closeInventory();
                }
            }
            else return;
        }
    }
}
