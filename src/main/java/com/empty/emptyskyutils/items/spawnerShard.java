package com.empty.emptyskyutils.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class spawnerShard {

    public static ItemStack zombieSpawnerShard;
    public static ItemStack skeletonSpawnerShard;

    public static void init() {
        createZombieShard();
        createSkeletonShard();
    }

    public static void createZombieShard() {
        List<Component> zombieShardLoreList = new ArrayList<>();
        zombieShardLoreList.add(Component.text("§7Combine with §7§l§n9§f §f§lSpawner Shards"));
        zombieShardLoreList.add(Component.text("§f§l(§7Zombie§f§l) §7to create a new"));
        zombieShardLoreList.add(Component.text("§f§lMob Spawner (§7Zombie§f§l)"));
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§lSpawner Shard (§7Zombie§f§l)");
        meta.addEnchant(Enchantment.SHARPNESS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.lore(zombieShardLoreList);
        item.setItemMeta(meta);
        zombieSpawnerShard = item;
    }

    public static void createSkeletonShard() {
        List<Component> skeletonShardLoreList = new ArrayList<>();
        skeletonShardLoreList.add(Component.text("§7Combine with §7§l§n9§f §f§lSpawner Shards"));
        skeletonShardLoreList.add(Component.text("§f§l(§7Skeleton§f§l) §7to create a new"));
        skeletonShardLoreList.add(Component.text("§f§lMob Spawner (§7Skeleton§f§l)"));
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§lSpawner Shard (§7Skeleton§f§l)");
        meta.addEnchant(Enchantment.SHARPNESS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.lore(skeletonShardLoreList);
        item.setItemMeta(meta);
        skeletonSpawnerShard = item;
    }
}
