package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.items.enchantShard;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class enchantShardApplication implements Listener {

    private final EmptySkyUtils plugin;
    private final Map<Enchantment, ItemStack> shardMap = new HashMap<>();

    public enchantShardApplication(EmptySkyUtils plugin) {
        this.plugin = plugin;
        initializeShardMap();
    }

    private void initializeShardMap() {
        shardMap.put(Enchantment.LOOTING, enchantShard.lootingShard);
        shardMap.put(Enchantment.FORTUNE, enchantShard.fortuneShard);
        shardMap.put(Enchantment.SHARPNESS, enchantShard.sharpnessShard);
        shardMap.put(Enchantment.SMITE, enchantShard.smiteShard);
        shardMap.put(Enchantment.UNBREAKING, enchantShard.unbreakingShard);
        shardMap.put(Enchantment.BANE_OF_ARTHROPODS, enchantShard.baneShard);
    }

    @EventHandler
    public void onEnchantShardClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) e.getWhoClicked();
        ItemStack cursorItem = e.getCursor();
        ItemStack currentItem = e.getCurrentItem();

        if (cursorItem == null || currentItem == null || cursorItem.getType().isAir() || currentItem.getType().isAir()) {
            return;
        }

        Enchantment enchantmentToApply = null;
        for (Map.Entry<Enchantment, ItemStack> entry : shardMap.entrySet()) {
            if (cursorItem.isSimilar(entry.getValue())) {
                enchantmentToApply = entry.getKey();
                break;
            }
        }

        if (enchantmentToApply != null) {
            ItemMeta targetMeta = currentItem.getItemMeta();
            if (targetMeta != null && enchantmentToApply.canEnchantItem(currentItem)) {
                int currentLevel = targetMeta.getEnchantLevel(enchantmentToApply);
                int newLevel = Math.min(currentLevel + 1, enchantmentToApply.getMaxLevel());

                if (newLevel > currentLevel) {
                    targetMeta.addEnchant(enchantmentToApply, newLevel, true);
                    currentItem.setItemMeta(targetMeta);

                    e.setCursor(null);
                    e.setCancelled(true);
                    e.setCurrentItem(currentItem);

                    player.sendMessage("§f§l[§bempty§7SkyUtils§f§l] Enchantment level " + newLevel + " applied successfully!");
                } else {
                    player.sendMessage("§f§l[§bempty§7SkyUtils§f§l] This item is already at the maximum enchantment level for " + enchantmentToApply.getKey().getKey() + "!");
                }
            } else {
                player.sendMessage("§f§l[§bempty§7SkyUtils§f§l] This enchantment cannot be applied to this item!");
            }
        }
    }
}