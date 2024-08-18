package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.items.spawnerShard;
import com.empty.emptyskyutils.utils.originType;
import com.empty.emptyskyutils.utils.playerData;
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

public class shardEntityDeath implements Listener {

    private final EmptySkyUtils plugin;

    public shardEntityDeath(EmptySkyUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (!plugin.getConfig().getBoolean("spawnerShards", true)) {
            return;
        }

        EntityType entity = e.getEntityType();
        Player player = e.getEntity().getKiller();
        if (player == null) return;

        String uuid = player.getUniqueId().toString();

        if (!plugin.getConfig().getBoolean("mobSpawnerShards." + entity.getName().toLowerCase(), false)) {
            return;
        }

        Map<EntityType, ItemStack> shardMap = new EnumMap<>(EntityType.class);

        shardMap.put(EntityType.RABBIT, spawnerShard.rabbitSpawnerShard);
        shardMap.put(EntityType.CHICKEN, spawnerShard.chickenSpawnerShard);
        shardMap.put(EntityType.COW, spawnerShard.cowSpawnerShard);
        shardMap.put(EntityType.SPIDER, spawnerShard.spiderSpawnerShard);
        shardMap.put(EntityType.ZOMBIE, spawnerShard.zombieSpawnerShard);
        shardMap.put(EntityType.SKELETON, spawnerShard.skeletonSpawnerShard);

        if (shardMap.containsKey(entity)) {
            String configKey = "mobSpawnerShards." + entity.name().toLowerCase() + "percent";
            double baseDropChance = plugin.getConfig().getDouble(configKey, 0.0);
            double dropChance = baseDropChance;

            if (plugin.getOriginEvents().getPlayerOrigin(uuid) == originType.TRADER) {
                dropChance *= 1.3;
            }

            plugin.getLogger().info("Entity: " + entity.name() +
                    ", Player: " + player.getName() +
                    ", Origin: " + plugin.getOriginEvents().getPlayerOrigin(uuid) +
                    ", Base Drop Chance: " + (baseDropChance * 100) + "%" +
                    ", Modified Drop Chance: " + (dropChance * 100) + "%");

            if (new Random().nextDouble() <= dropChance) {
                player.getInventory().addItem(shardMap.get(entity));
                if (plugin.getConfig().getBoolean("sendSpawnerMessage")) {
                    player.sendMessage("§f§l[§bempty§7SkyUtils§f§l] You have received a " + entity.name() + " Spawner Shard!");
                }
            }
        }
    }
}
