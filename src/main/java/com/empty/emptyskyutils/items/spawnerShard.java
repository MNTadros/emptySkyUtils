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

    public static ItemStack rabbitSpawnerShard;
    public static ItemStack chickenSpawnerShard;
    public static ItemStack cowSpawnerShard;
    public static ItemStack spiderSpawnerShard;
    public static ItemStack zombieSpawnerShard;
    public static ItemStack skeletonSpawnerShard;

    public static void init() {
        rabbitSpawnerShard = createSpawnerShard("Rabbit");
        chickenSpawnerShard = createSpawnerShard("Chicken");
        cowSpawnerShard = createSpawnerShard("Cow");
        spiderSpawnerShard = createSpawnerShard("Spider");
        zombieSpawnerShard = createSpawnerShard("Zombie");
        skeletonSpawnerShard = createSpawnerShard("Skeleton");
    }

    private static ItemStack createSpawnerShard(String entityType) {
        List<Component> shardLoreList = new ArrayList<>();
        shardLoreList.add(Component.text("§7Combine with §7§l§n9§f §f§lSpawner Shards"));
        shardLoreList.add(Component.text("§f§l(§7" + entityType + "§f§l) §7to create a new"));
        shardLoreList.add(Component.text("§f§lMob Spawner (§7" + entityType + "§f§l)"));

        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§lSpawner Shard (§7" + entityType + "§f§l)");
        meta.addEnchant(Enchantment.SHARPNESS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.lore(shardLoreList);
        item.setItemMeta(meta);

        return item;
    }
}
