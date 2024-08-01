package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.items.spawnerShard;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class onEntityDeath implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        EntityType entity = e.getEntityType();
        Entity killerEntity = e.getEntity().getKiller();

        if (killerEntity != null && killerEntity instanceof Player) {
            Player killer = (Player) killerEntity;
            if (entity == EntityType.ZOMBIE) {
                Random dropChance = new Random();
                if (dropChance.nextDouble() <= 0.30) {
                    killer.sendMessage("You have received a Zombie Spawner Shard!");
                    killer.getInventory().addItem(spawnerShard.zombieSpawnerShard);
                }
            }
            if (entity == EntityType.SKELETON) {
                Random dropChance = new Random();
                if (dropChance.nextDouble() <= 0.30) {
                    killer.sendMessage("You have received a Skeleton Spawner Shard!");
                    killer.getInventory().addItem(spawnerShard.skeletonSpawnerShard);
                }
            }
        }
    }
}
