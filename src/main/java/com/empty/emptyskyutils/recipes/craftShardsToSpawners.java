package com.empty.emptyskyutils.recipes;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.items.spawnerShard;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class craftShardsToSpawners {

    private final EmptySkyUtils plugin;

    public craftShardsToSpawners(EmptySkyUtils plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerSpawnerRecipe("rabbit", spawnerShard.rabbitSpawnerShard, EntityType.RABBIT);
        registerSpawnerRecipe("chicken", spawnerShard.chickenSpawnerShard, EntityType.CHICKEN);
        registerSpawnerRecipe("cow", spawnerShard.cowSpawnerShard, EntityType.COW);
        registerSpawnerRecipe("spider", spawnerShard.spiderSpawnerShard, EntityType.SPIDER);
        registerSpawnerRecipe("zombie", spawnerShard.zombieSpawnerShard, EntityType.ZOMBIE);
        registerSpawnerRecipe("skeleton", spawnerShard.skeletonSpawnerShard, EntityType.SKELETON);
    }

    private void registerSpawnerRecipe(String entityName, ItemStack shard, EntityType entityType) {
        ItemStack spawner = new ItemStack(Material.SPAWNER);
        BlockStateMeta meta = (BlockStateMeta) spawner.getItemMeta();
        CreatureSpawner creatureSpawner = (CreatureSpawner) meta.getBlockState();
        creatureSpawner.setSpawnedType(entityType);
        meta.setBlockState(creatureSpawner);
        meta.setDisplayName(entityName + " Spawner");
        spawner.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, entityName.toLowerCase() + "_spawner");
        ShapedRecipe recipe = new ShapedRecipe(key, spawner);
        recipe.shape("SSS", "SSS", "SSS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(shard));

        Bukkit.addRecipe(recipe);
    }
}