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

import java.util.Random;
import java.util.logging.Logger;

public class mobBoxEntityDeath implements Listener {

    private final EmptySkyUtils plugin;
    private final Logger logger;
    private final Random random = new Random();

    public mobBoxEntityDeath(EmptySkyUtils plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!isMobBoxesEnabled()) {
            return;
        }

        Entity entity = event.getEntity();
        Player killer = getKiller(entity);

        if (killer != null) {
            String tier = getMobBoxTier(entity.getType());

            if (tier == null) {
                logNoConfiguration(entity.getType());
                return;
            }

            if (isMobBoxDropEnabled(tier) && shouldDropMobBox(tier)) {
                giveMobBoxToPlayer(killer, tier);
            }
        }
    }

    private boolean isMobBoxesEnabled() {
        return getConfigBoolean("mobBoxes", true);
    }

    private Player getKiller(Entity entity) {
        if (entity instanceof Player) {
            return (Player) entity;
        }

        return null;
    }

    private String getMobBoxTier(EntityType entityType) {
        ConfigurationSection section = getConfigSection("mobBoxDrops");
        if (section == null) {
            return null;
        }

        for (String tier : section.getKeys(false)) {
            ConfigurationSection tierSection = section.getConfigurationSection(tier);
            if (tierSection != null && tierSection.getStringList("entities").contains(entityType.name())) {
                return tier;
            }
        }
        return null;
    }

    private void logNoConfiguration(EntityType entityType) {
        logger.info("No mob box configuration found for entity " + entityType.name());
    }

    private boolean isMobBoxDropEnabled(String tier) {
        return getConfigBoolean("mobBoxDrops." + tier + ".enabled", false);
    }

    private boolean shouldDropMobBox(String tier) {
        double dropChance = getConfigDouble("mobBoxDrops." + tier + ".chance", 0.0);
        return random.nextDouble() <= dropChance;
    }

    private void giveMobBoxToPlayer(Player player, String tier) {
        ItemStack mobBoxItem = getMobBoxItem(tier);
        if (mobBoxItem != null) {
            player.getInventory().addItem(mobBoxItem);
            if (getConfigBoolean("sendMobBoxMessage", false)) {
                sendMobBoxMessage(player, tier);
            }
        }
    }

    private void sendMobBoxMessage(Player player, String tier) {
        player.sendMessage("§f§l[§bempty§7SkyUtils§f§l] You have received a " + tier + " Mob Box!");
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

    private boolean getConfigBoolean(String path, boolean defaultValue) {
        return plugin.getMobBoxConfig().getBoolean(path, defaultValue);
    }

    private double getConfigDouble(String path, double defaultValue) {
        return plugin.getMobBoxConfig().getDouble(path, defaultValue);
    }

    private ConfigurationSection getConfigSection(String path) {
        return plugin.getMobBoxConfig().getConfigurationSection(path);
    }
}
