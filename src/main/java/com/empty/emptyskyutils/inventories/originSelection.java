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
        origininv = Bukkit.createInventory(this,54,"§6§Origin Selection");
        setOuterLayer(origininv, Material.WHITE_STAINED_GLASS_PANE);
        init();
        initSelection();
    }

    public void setOuterLayer(Inventory inventory, Material material) {
        int rows = 6;
        int columns = 9;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == columns - 1) {
                    int slot = row * columns + col;
                    inventory.setItem(slot, new ItemStack(material));
                }
            }
        }
    }

    private void init(){
        ItemStack item;
        //left
        for (int i = 0; i < 10;i++){
            item = createItem("§7", Material.LIME_STAINED_GLASS,null);
            origininv.setItem(i,item);
        }
        item = createItem("§7", Material.LIME_STAINED_GLASS,null);
        origininv.setItem(17,item);
        origininv.setItem(11,item);
        origininv.setItem(13,item);
        origininv.setItem(15,item);


        for (int i = 18; i < 27;i++){
            item = createItem("§7", Material.LIME_STAINED_GLASS,null);
            origininv.setItem(i,item);
        }
        List<String> lore = new ArrayList<>();
        lore.add("§cClick this to close the selection");
        item = createItem("§c§lClose selection", Material.BARRIER,lore);
        origininv.setItem(22,item);

    }

    void initSelection(){
        ItemStack slot1;
        List<String> slot1lore = new ArrayList<>();
        slot1lore.add("§fMaster the art of farming");
        slot1lore.add("§fand reap extra rewards!");
        slot1lore.add("");
        slot1lore.add("§2► §f§n+20%§f Crop Growth Speed");
        slot1lore.add("§2► §f§n+10%§f Yield from Harvesting");
        slot1lore.add("§2► §fPermanent Haste 2");

        slot1 = createItem("§2§lFarmer Origin", Material.WHEAT, slot1lore);
        origininv.setItem(10, slot1);

        ItemStack slot2;
        List<String> slot2lore = new ArrayList<>();
        slot2lore.add("§fMine deep and uncover");
        slot2lore.add("§friches buried in the earth!");
        slot2lore.add("");
        slot2lore.add("§b► §f§n+15%§f Chance to find Gems");
        slot2lore.add("§b► §f§n+10%§f Mining Speed");
        slot2lore.add("§b► §fExclusive §n/cave warp");

        slot2 = createItem("§b§lMiner Origin", Material.DIAMOND_PICKAXE, slot2lore);
        origininv.setItem(12, slot2);

        ItemStack slot3;
        List<String> slot3lore = new ArrayList<>();
        slot3lore.add("§fHarness the power of the economy");
        slot3lore.add("§fand dominate the marketplace!");
        slot3lore.add("");
        slot3lore.add("§e► §f§n+15%§f Sell Price on Goods");
        slot3lore.add("§e► §f§n-10%§f Buy Price at Shops");
        slot3lore.add("§e► §fExclusive §n/auction warp");

        slot3 = createItem("§e§lTrader Origin", Material.EMERALD, slot3lore);
        origininv.setItem(14, slot3);

        ItemStack slot4;
        List<String> slot4lore = new ArrayList<>();
        slot4lore.add("§fCollect and breed animals");
        slot4lore.add("§fto enhance your island's growth!");
        slot4lore.add("");
        slot4lore.add("§d► §f§n+20%§f Animal Breeding Speed");
        slot4lore.add("§d► §f§n+10%§f Drop Rate from Animals");
        slot4lore.add("§d► §fPermanent Luck 2");

        slot4 = createItem("§d§lRancher Origin", Material.CARROT, slot4lore);
        origininv.setItem(16, slot4);
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
