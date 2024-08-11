package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.items.mobBox;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class mobBoxEntityDeath implements Listener {

    private EmptySkyUtils plugin;
    private Logger logger;

    public mobBoxEntityDeath(EmptySkyUtils plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (!getConfigBoolean("mobBoxes", true)) {
            return;
        }

        EntityType entityType = e.getEntityType();
        Entity killerEntity = e.getEntity().getKiller();

        if (killerEntity instanceof Player) {
            Player player = (Player) killerEntity;

            String tier = getMobBoxTier(entityType);
            if (tier == null) {
                logger.info("No mob box configuration found for entity " + entityType.name());
                return;
            }

            if (!getConfigBoolean("mobBoxDrops." + tier + ".enabled", false)) {
                return;
            }

            double dropChance = getConfigDouble("mobBoxDrops." + tier + ".chance", 0.0);
            if (new Random().nextDouble() <= dropChance) {
                ItemStack mobBoxItem = getMobBoxItem(tier);

                if (mobBoxItem != null) {
                    player.getInventory().addItem(mobBoxItem);

                    if (getConfigBoolean("sendMobBoxMessage", false)) {
                        player.sendMessage("§f§l[§bempty§7SkyUtils§f§l] You have received a " + entityType.name() + " Mob Box!");
                    }
                }
            }
        }
    }

    private String getMobBoxTier(EntityType entityType) {
        ConfigurationSection section = plugin.getMobBoxConfig().getConfigurationSection("mobBoxDrops");
        if (section == null) return null;

        for (String tier : section.getKeys(false)) {
            ConfigurationSection tierSection = section.getConfigurationSection(tier);
            if (tierSection != null) {
                List<String> entities = tierSection.getStringList("entities");
                if (entities.contains(entityType.name())) {
                    return tier;
                }
            }
        }
        return null;
    }

    private ItemStack getMobBoxItem(String tier) {
        switch (tier.toLowerCase()) {
            case "tier1": return mobBox.tier1mobBox;
            case "tier2": return mobBox.tier2mobBox;
            case "tier3": return mobBox.tier3mobBox;
            case "tier4": return mobBox.tier4mobBox;
            case "tier5": return mobBox.tier5mobBox;
            case "tier6": return mobBox.tier6mobBox;
            case "resource": return mobBox.resourcemobBox;
            case "boss": return mobBox.bossmobBox;
            default: return null;
        }
    }

    private boolean getConfigBoolean(String path, boolean def) {
        return plugin.getMobBoxConfig().getBoolean(path, def);
    }

    private double getConfigDouble(String path, double def) {
        return plugin.getMobBoxConfig().getDouble(path, def);
    }
}
