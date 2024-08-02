package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.items.enchantShard;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class enchantShardApplication implements Listener {

    private final EmptySkyUtils plugin;
    private final Map<Enchantment, ItemStack> shardMap = new HashMap<>();
    private final Map<Enchantment, Set<Material>> applicableMaterialsMap = new HashMap<>();

    public enchantShardApplication(EmptySkyUtils plugin) {
        this.plugin = plugin;
        initializeShardMap();
        initializeApplicableMaterialsMap();
    }

    private void initializeShardMap() {
        shardMap.put(Enchantment.LOOTING, enchantShard.lootingShard);
        shardMap.put(Enchantment.FORTUNE, enchantShard.fortuneShard);
        shardMap.put(Enchantment.SHARPNESS, enchantShard.sharpnessShard);
        shardMap.put(Enchantment.SMITE, enchantShard.smiteShard);
        shardMap.put(Enchantment.UNBREAKING, enchantShard.unbreakingShard);
        shardMap.put(Enchantment.BANE_OF_ARTHROPODS, enchantShard.baneShard);
    }

    private void initializeApplicableMaterialsMap() {
        applicableMaterialsMap.put(Enchantment.LOOTING, Set.of(
                Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD,
                Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
                Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE,
                Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE
        ));
        applicableMaterialsMap.put(Enchantment.FORTUNE, Set.of(
                Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE,
                Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE
        ));
        applicableMaterialsMap.put(Enchantment.SHARPNESS, Set.of(
                Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD,
                Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
                Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE,
                Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE
        ));
        applicableMaterialsMap.put(Enchantment.SMITE, Set.of(
                Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD,
                Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
                Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE,
                Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE
        ));
        applicableMaterialsMap.put(Enchantment.UNBREAKING, Set.of(
                Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD,
                Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
                Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE,
                Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE,
                Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE,
                Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE,
                Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL,
                Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL,
                Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE,
                Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE,
                Material.BOW, Material.CROSSBOW, Material.TRIDENT
        ));
        applicableMaterialsMap.put(Enchantment.BANE_OF_ARTHROPODS, Set.of(
                Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD,
                Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
                Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE,
                Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE
        ));
    }

    @EventHandler
    public void onEnchantShardClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        HumanEntity player = e.getWhoClicked();
        ItemStack cursorItem = e.getCursor();
        ItemStack currentItem = e.getCurrentItem();
        Enchantment enchantmentToApply = null;

        if (cursorItem == null || currentItem == null || cursorItem.getType().isAir() || currentItem.getType().isAir()) {
            return;
        }

        if (plugin.getConfig().getBoolean("enchantShards") && (cursorItem != null && shardMap.containsValue(cursorItem)) || (currentItem != null && shardMap.containsValue(currentItem))) {

            for (Map.Entry<Enchantment, ItemStack> entry : shardMap.entrySet()) {
                if (cursorItem.isSimilar(entry.getValue())) {
                    enchantmentToApply = entry.getKey();
                    break;
                }
            }

            if (enchantmentToApply == null) {
                return;
            }

            Set<Material> applicableMaterials = applicableMaterialsMap.get(enchantmentToApply);
            if (applicableMaterials == null || !applicableMaterials.contains(currentItem.getType())) {
                if (plugin.getConfig().getBoolean("sendEnchantMessage")) {
                    player.sendMessage("§f§l[§bempty§7SkyUtils§f§l] This enchant doesn't apply to this item!");
                    return;
                } else {
                    return;
                }
            }

            int currentLevel = currentItem.getEnchantmentLevel(enchantmentToApply);
            int maxLevel = enchantmentToApply.getMaxLevel() + 1;

            if (currentLevel >= maxLevel) {
                if (plugin.getConfig().getBoolean("sendEnchantMessage")) {
                    player.sendMessage("§f§l[§bempty§7SkyUtils§f§l] This item already has this level enchant!");
                    return;
                } else {
                    return;
                }
            }

            currentItem.addUnsafeEnchantment(enchantmentToApply, maxLevel);
            cursorItem.setAmount(cursorItem.getAmount() - 1);

            if (cursorItem.getAmount() <= 0) {
                player.setItemOnCursor(null);
            } else {
                player.setItemOnCursor(cursorItem);
            }

            e.setCurrentItem(currentItem);
            e.setCancelled(true);
        }

        else {
            if (plugin.getConfig().getBoolean("sendEnchantMessage")) {
                player.sendMessage("§f§l[§bempty§7SkyUtils§f§l] Enchant Shards are disabled!");
            }
        }
    }
}
