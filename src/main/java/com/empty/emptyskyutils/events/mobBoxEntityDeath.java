package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.items.mobBox;
import com.empty.emptyskyutils.items.spawnerShard;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class mobBoxEntityDeath implements Listener {

    private EmptySkyUtils plugin;

    public mobBoxEntityDeath(EmptySkyUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (!plugin.getConfig().getBoolean("mobBoxes", true)) {
            return;
        }

        EntityType entity = e.getEntityType();
        Entity killerEntity = e.getEntity().getKiller();

        if (!plugin.getConfig().getBoolean("mobBoxes." + entity.getName().toLowerCase(), false)) {
            return;
        }
        Map<EntityType, ItemStack> shardMap = new EnumMap<>(EntityType.class);

        shardMap.put(EntityType.RABBIT, mobBox.tier1mobBox);
        shardMap.put(EntityType.CHICKEN, mobBox.tier1mobBox);
        shardMap.put(EntityType.COW, mobBox.tier1mobBox);
        shardMap.put(EntityType.SPIDER, mobBox.tier1mobBox);
        shardMap.put(EntityType.ZOMBIE, mobBox.tier1mobBox);
        shardMap.put(EntityType.SKELETON, mobBox.tier1mobBox);

        if (killerEntity != null && killerEntity instanceof Player) {
            if (shardMap.containsKey(entity)) {
                String configKey = "mobSpawnerShards." + entity.name().toLowerCase() + "percent";
                double dropChance = plugin.getConfig().getDouble(configKey, 0.0);
                if (new Random().nextDouble() <= dropChance) {
                    ((Player) killerEntity).getInventory().addItem(shardMap.get(entity));
                    if (plugin.getConfig().getBoolean("sendMobBoxMessage")) {
                        killerEntity.sendMessage("§f§l[§bempty§7SkyUtils§f§l] You have received a Mob Box!");
                    }
                }
            }
        }
    }
}
