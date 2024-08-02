package com.empty.emptyskyutils.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class enchantShard {

    public static ItemStack lootingShard;
    public static ItemStack fortuneShard;
    public static ItemStack sharpnessShard;
    public static ItemStack smiteShard;
    public static ItemStack unbreakingShard;
    public static ItemStack baneShard;
    public static ItemStack protectionShard;
    public static ItemStack efficiencyShard;

    public static void init() {
        lootingShard = createEnchantShard("Looting");
        fortuneShard = createEnchantShard("Fortune");
        sharpnessShard = createEnchantShard("Sharpness");
        smiteShard = createEnchantShard("Smite");
        unbreakingShard = createEnchantShard("Unbreaking");
        baneShard = createEnchantShard("Bane_of_arthropods");
        protectionShard = createEnchantShard("Protection");
        efficiencyShard = createEnchantShard("Efficiency");

    }

    private static ItemStack createEnchantShard(String enchant) {
        List<Component> shardLoreList = new ArrayList<>();
        shardLoreList.add(Component.text("§7Click on the §7§l§nrelevant§7 tool"));
        shardLoreList.add(Component.text("§7to add " + enchant + " to the item."));
        ItemStack item = new ItemStack(Material.PRISMARINE_CRYSTALS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§lEnchant Shard (§7" + enchant + "§f§l)");
        meta.addEnchant(Enchantment.SHARPNESS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.lore(shardLoreList);
        item.setItemMeta(meta);

        return item;
    }
}
