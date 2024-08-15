package com.empty.emptyskyutils.inventories;

import com.empty.emptyskyutils.EmptySkyUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class originSelection implements InventoryHolder {
    public Inventory origininv;
    private final EmptySkyUtils plugin;

    public originSelection(EmptySkyUtils plugin){
        this.plugin = plugin;
        origininv = Bukkit.createInventory(this,54,"§6Origin Selection");
        init();
        initSelection();
    }

    private void init(){
        ItemStack item;
        for (int i = 0; i < 9; i++) {
            item = createItem("§7", Material.LIME_STAINED_GLASS, null);
            origininv.setItem(i, item);
            origininv.setItem(45 + i, item); // last row
        }
        for (int i = 9; i < 18; i++) {
            item = createItem("§7", Material.LIME_STAINED_GLASS, null);
            origininv.setItem(i, item);
        }
        for (int i = 27; i < 45; i++) {
            item = createItem("§7", Material.LIME_STAINED_GLASS, null);
            origininv.setItem(i, item);
        }
        origininv.setItem(36, createItem("§7", Material.LIME_STAINED_GLASS, null));
        origininv.setItem(18, createItem("§7", Material.LIME_STAINED_GLASS, null));
        origininv.setItem(20, createItem("§7", Material.LIME_STAINED_GLASS, null));
        origininv.setItem(22, createItem("§7", Material.LIME_STAINED_GLASS, null));
        origininv.setItem(24, createItem("§7", Material.LIME_STAINED_GLASS, null));
        origininv.setItem(26, createItem("§7", Material.LIME_STAINED_GLASS, null));

        List<String> lore = new ArrayList<>();
        lore.add("§cClick this to close the selection");
        ItemStack closeItem = createItem("§c§lClose selection", Material.BARRIER, lore);
        origininv.setItem(49, closeItem);
    }

    void initSelection(){
        ItemStack slot1;
        List<String> slot1lore = new ArrayList<>();
        slot1lore.add("§fMaster the art of farming");
        slot1lore.add("§fand reap extra rewards!");
        slot1lore.add("");
        slot1lore.add("§2► §f§n+10%§f Yield from Harvesting");
        slot1lore.add("§2► §fGet §nCropBoxes§f from harvesting crops §e§l§n(ORIGIN EXCLUSIVE)");
        slot1lore.add("§2► §fPermanent §nHaste 2");

        slot1 = createItem("§2§lFarmer Origin", Material.WHEAT, slot1lore);
        origininv.setItem(19, slot1);

        ItemStack slot2;
        List<String> slot2lore = new ArrayList<>();
        slot2lore.add("§fMine deep and uncover");
        slot2lore.add("§friches buried in the earth!");
        slot2lore.add("");
        slot2lore.add("§b► §f§n+15%§f Chance to find Gems");
        slot2lore.add("§b► §fPermanent §nHaste 2");

        slot2 = createItem("§b§lMiner Origin", Material.DIAMOND_PICKAXE, slot2lore);
        origininv.setItem(21, slot2);

        ItemStack slot3;
        List<String> slot3lore = new ArrayList<>();
        slot3lore.add("§fHarness the power of the economy");
        slot3lore.add("§fand dominate the marketplace!");
        slot3lore.add("");
        slot3lore.add("§e► §fDrop §nSpawnerShards§f more often");
        slot3lore.add("§e► §fDrop §nMobBoxes§f more often");
        slot3lore.add("§e► §fPermanent §nSpeed 1");
        slot3lore.add("§e► §f§nPhoenix §fAbility");

        slot3 = createItem("§e§lTrader Origin", Material.EMERALD, slot3lore);
        origininv.setItem(23, slot3);

        ItemStack slot4;
        List<String> slot4lore = new ArrayList<>();
        slot4lore.add("§fCollect and breed animals");
        slot4lore.add("§fto enhance your island's growth!");
        slot4lore.add("");
        slot4lore.add("§d► §f§n+15%§f Drop rate from Mobs");
        slot4lore.add("§d► §f§n+15%§f Exp Drop rate");
        slot4lore.add("§d► §fPermanent §nSpeed 2");
        slot4lore.add("§d► §fPermanent §nLuck 1");

        slot4 = createItem("§d§lRancher Origin", Material.CARROT, slot4lore);
        origininv.setItem(25, slot4);
    }

    private ItemStack createItem(String name, Material mat, List<String> lore){
        ItemStack item = new ItemStack(mat,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return origininv;
    }
}
