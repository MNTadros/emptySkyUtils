package com.empty.emptyskyutils.items;

import com.empty.emptyskyutils.EmptySkyUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class mobBox {
    private EmptySkyUtils plugin;

    public mobBox(EmptySkyUtils plugin) {
        this.plugin = plugin;
    }

    public static ItemStack tier1mobBox;
    public static ItemStack tier2mobBox;
    public static ItemStack tier3mobBox;
    public static ItemStack tier4mobBox;
    public static ItemStack tier5mobBox;
    public static ItemStack tier6mobBox;
    public static ItemStack resourcemobBox;
    public static ItemStack bossmobBox;

    public static void init() {
        tier1mobBox = createMobBox("Common","§7");
        tier2mobBox = createMobBox("Uncommon","§a");
        tier3mobBox = createMobBox("Elite","§b");
        tier4mobBox = createMobBox("Rare","§e");
        tier5mobBox = createMobBox("Legendary","§6");
        tier6mobBox = createMobBox("Mythic","§9");
        resourcemobBox = createMobBox("Resource","§2");
        bossmobBox = createMobBox("Boss","§d");
    }

    private static ItemStack createMobBox(String name, String color) {
        List<Component> mobBoxLoreList = new ArrayList<>();
        mobBoxLoreList.add(Component.text("§7Contains " + color + name + " §7items dropped"));
        mobBoxLoreList.add(Component.text("§7by mobs upon defeat."));
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§lMob Box (" + color + "§l" + name + "§f§l)");
        meta.addEnchant(Enchantment.SHARPNESS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.lore(mobBoxLoreList);
        item.setItemMeta(meta);
        return item;
    }

}
