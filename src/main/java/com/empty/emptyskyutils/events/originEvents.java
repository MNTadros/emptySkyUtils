package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.inventories.originSelection;
import com.empty.emptyskyutils.utils.effectHandler;
import com.empty.emptyskyutils.utils.originType;
import com.empty.emptyskyutils.utils.playerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class originEvents implements Listener {
    private final EmptySkyUtils plugin;
    private final effectHandler effectHandler;
    private final File dataFile;
    private Map<String, playerData> playerDataMap;

    public originEvents(EmptySkyUtils plugin) {
        this.plugin = plugin;
        this.effectHandler = new effectHandler();
        this.dataFile = new File(plugin.getDataFolder(), "player_data.dat");
        this.playerDataMap = loadPlayerData();
    }

    @EventHandler
    public void onSelection(InventoryClickEvent event) {
        if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT ||
                event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT ||
                event.getClick() == ClickType.MIDDLE || event.getClick() == ClickType.UNKNOWN) {

            if (event.getInventory() == null || event.getCurrentItem() == null) {
                return;
            }

            if (event.getClickedInventory().getHolder() instanceof originSelection) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();
                ItemStack clickedItem = event.getCurrentItem();
                String itemName = clickedItem.getItemMeta().getDisplayName();

                originType selectedOrigin = null;

                switch (itemName) {
                    case "§2§lFarmer Origin":
                        selectedOrigin = originType.FARMER;
                        break;
                    case "§b§lMiner Origin":
                        selectedOrigin = originType.MINER;
                        break;
                    case "§d§lRancher Origin":
                        selectedOrigin = originType.RANCHER;
                        break;
                    case "§e§lTrader Origin":
                        selectedOrigin = originType.TRADER;
                        break;
                    case "§c§lClose selection":
                        player.sendMessage("§6[!] You have closed the selection! [!]");
                        player.closeInventory();
                        return;
                    default:
                        return;
                }

                if (!checkPlayerOrigin(player)) {
                    effectHandler.applyOriginEffects(player, selectedOrigin);
                    savePlayerOrigin(player, selectedOrigin);
                    player.sendMessage("§6[!] You have selected the " + itemName + " [!]");
                } else {
                    player.sendMessage("§6[!] You have already selected an origin! [!]");
                }
            }
        }
    }

    private boolean checkPlayerOrigin(Player player) {
        String uuid = player.getUniqueId().toString();
        return playerDataMap.containsKey(uuid);
    }

    private void savePlayerOrigin(Player player, originType origin) {
        String uuid = player.getUniqueId().toString();
        playerDataMap.put(uuid, new playerData(uuid, origin));
        savePlayerData();
    }

    private Map<String, playerData> loadPlayerData() {
        if (!dataFile.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            return (Map<String, playerData>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void savePlayerData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(playerDataMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
