package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class mobBoxEvents implements Listener {

    private EmptySkyUtils plugin;

    public mobBoxEvents(EmptySkyUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (item != null && isMobBox(item)) {
            event.setCancelled(true);
        }
    }

    private boolean isMobBox(ItemStack item) {
        if (item.getType() == Material.ENDER_CHEST && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasDisplayName()) {
                String displayName = meta.getDisplayName();
                return displayName.startsWith("§f§lMob Box");
            }
        }
        return false;
    }
}
