package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class mobBoxEvents implements Listener {
    private final EmptySkyUtils plugin;
    private final Random random = new Random();
    private final Logger logger;

    public mobBoxEvents(EmptySkyUtils plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.logger.info("mobBoxEvents initialized successfully.");
    }

    @EventHandler
    public void onBoxUse(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (isMobBox(itemInHand)) {
            String displayName = itemInHand.getItemMeta().getDisplayName();
            String tier = getTierFromDisplayName(displayName);

            if (tier == null) {
                logger.warning("Invalid mob box tier: " + displayName);
                return;
            }

            FileConfiguration mobBoxConfig = plugin.getMobBoxConfig();
            if (mobBoxConfig == null) {
                logger.severe("mobBoxConfig is null. Check if the configuration file is loaded correctly.");
                return;
            }

            ConfigurationSection lootTableSection = mobBoxConfig.getConfigurationSection("mobBoxLootTable." + tier);
            if (lootTableSection == null) {
                logger.warning("No loot table found for tier: " + tier);
                return;
            }

            ConfigurationSection itemsSection = lootTableSection.getConfigurationSection("items");
            if (itemsSection == null) {
                logger.warning("No items section found for tier: " + tier);
                return;
            }
            List<ItemStack> itemsToGive = new ArrayList<>();
            for (String key : itemsSection.getKeys(false)) {
                Material material = Material.getMaterial(itemsSection.getString(key + ".material"));
                int amount = itemsSection.getInt(key + ".amount", 1);
                double chance = itemsSection.getDouble(key + ".chance", 0.0);

                if (material != null) {
                    if (random.nextDouble() <= chance) {
                        ItemStack item = new ItemStack(material, amount);
                        itemsToGive.add(item);
                        logger.info("Item rolled: " + item.getType() + " with chance: " + chance);
                    }
                } else {
                    logger.warning("Invalid material in config: " + itemsSection.getString(key + ".material"));
                }
            }

            if (itemsToGive.isEmpty()) {
                logger.info("No items rolled for tier: " + tier);
            } else {
                for (ItemStack item : itemsToGive) {
                    player.getInventory().addItem(item);
                }
                logger.info("Given items to player: " + itemsToGive);
            }

            removeMobBoxFromInventory(player, itemInHand);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack itemInHand = event.getItemInHand();
        if (isMobBox(itemInHand)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You cannot place Mob Boxes!");
            logger.info("Attempted to place a Mob Box and it was cancelled.");
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

    private String getTierFromDisplayName(String displayName) {
        if (displayName.contains("Common")) return "tier1";
        if (displayName.contains("Uncommon")) return "tier2";
        if (displayName.contains("Elite")) return "tier3";
        if (displayName.contains("Rare")) return "tier4";
        if (displayName.contains("Legendary")) return "tier5";
        if (displayName.contains("Mythic")) return "tier6";
        if (displayName.contains("Resource")) return "resource";
        if (displayName.contains("Boss")) return "boss";
        return null;
    }

    private void removeMobBoxFromInventory(Player player, ItemStack mobBoxItem) {
        if (mobBoxItem.getAmount() > 1) {
            mobBoxItem.setAmount(mobBoxItem.getAmount() - 1);
            player.getInventory().setItemInMainHand(mobBoxItem);
        } else {
            player.getInventory().remove(mobBoxItem);
        }
        logger.info("Removed one mob box from player inventory.");
    }
}
