package com.empty.emptyskyutils.events;

import com.empty.emptyskyutils.EmptySkyUtils;
import com.empty.emptyskyutils.inventories.originSelection;
import com.empty.emptyskyutils.utils.effectHandler;
import com.empty.emptyskyutils.utils.originEventsUtils;
import com.empty.emptyskyutils.utils.originType;
import com.empty.emptyskyutils.utils.playerData;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        this.dataFile = new File(plugin.getDataFolder(), "player_data.json");
        this.playerDataMap = loadPlayerData();
    }

    @EventHandler
    public void onSelection(InventoryClickEvent event) {
        if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT ||
                event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT ||
                event.getClick() == ClickType.MIDDLE) {

            if (event.getInventory() == null || event.getCurrentItem() == null) {
                return;
            }

            if (event.getClickedInventory().getHolder() instanceof originSelection) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();
                ItemStack clickedItem = event.getCurrentItem();
                String itemName = clickedItem.getItemMeta().getDisplayName();

                originType selectedOrigin = getSelectedOrigin(itemName);

                if (selectedOrigin != null) {
                    if (!checkPlayerOrigin(player)) {
                        effectHandler.applyOriginEffects(player, selectedOrigin);
                        savePlayerOrigin(player, selectedOrigin);
                        player.sendMessage("§6[!] You have selected the " + itemName + "§6 [!]");
                    } else {
                        player.sendMessage("§6[!] You have already selected an origin! [!]");
                    }
                } else if (itemName.equals("§c§lClose selection")) {
                    player.sendMessage("§6[!] You have closed the selection! [!]");
                    player.closeInventory();
                }
            }
        }
    }

    private originType getSelectedOrigin(String itemName) {
        switch (itemName) {
            case "§2§lFarmer Origin":
                return originType.FARMER;
            case "§b§lMiner Origin":
                return originType.MINER;
            case "§d§lRancher Origin":
                return originType.RANCHER;
            case "§e§lTrader Origin":
                return originType.TRADER;
            default:
                return null;
        }
    }

    private boolean checkPlayerOrigin(Player player) {
        String uuid = player.getUniqueId().toString();
        return playerDataMap.containsKey(uuid);
    }

    private void savePlayerOrigin(Player player, originType origin) {
        String uuid = player.getUniqueId().toString();
        playerDataMap.put(uuid, new playerData(uuid, origin, 1));
        savePlayerData();
    }

    private Map<String, playerData> loadPlayerData() {
        if (!dataFile.exists()) {
            return new HashMap<>();
        }
        try (FileReader reader = new FileReader(dataFile)) {
            JSONParser parser = new JSONParser();
            JSONArray playerArray = (JSONArray) parser.parse(reader);
            Map<String, playerData> dataMap = new HashMap<>();

            for (Object obj : playerArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String uuid = (String) jsonObject.get("uuid");
                originType origin = originType.valueOf((String) jsonObject.get("origin"));
                int level = safeGetInt(jsonObject.get("origin_level"));

                dataMap.put(uuid, new playerData(uuid, origin, level));
            }
            return dataMap;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void savePlayerData() {
        JSONArray playerArray = new JSONArray();

        for (Map.Entry<String, playerData> entry : playerDataMap.entrySet()) {
            playerData data = entry.getValue();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uuid", data.getUuid());
            jsonObject.put("origin", data.getOrigin().name());
            jsonObject.put("origin_level", data.getOriginLevel());

            playerArray.add(jsonObject);
        }

        try (FileWriter writer = new FileWriter(dataFile)) {
            writer.write(playerArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int safeGetInt(Object obj) {
        if (obj instanceof Long) {
            return ((Long) obj).intValue();
        }
        return (int) obj;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) { //TODO not workin -->
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        player.sendMessage("§6Player respawn event triggered for UUID: " + uuid + "§6");

        if (playerDataMap.containsKey(uuid)) {
            playerData data = playerDataMap.get(uuid);
            originType origin = data.getOrigin();
            player.sendMessage("§6Reapplying effects for origin: " + origin.name() + "§6");
            effectHandler.applyOriginEffects(player, origin);
        } else {
            player.sendMessage("§cNo origin data found for player with UUID: " + uuid + "§c");
        }
    }                                               //TODO not workin <--


    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        String uuid = player.getUniqueId().toString();

        if (playerDataMap.containsKey(uuid)) {
            playerData data = playerDataMap.get(uuid);
            originType origin = data.getOrigin();

            if (origin == originType.TRADER) {
                double finalHealth = player.getHealth() - event.getFinalDamage();

                if (finalHealth <= 1.0) {
                    if (data.isCooldownExpired()) {
                        event.setCancelled(true);

                        player.setHealth(1.0);
                        player.setFireTicks(0);
                        player.setFallDistance(0);

                        player.setHealth(20);
                        data.resetCooldown();
                        originEventsUtils.applyPhoenixEffects(player);
                    } else {
                        player.sendMessage("§cYour §c§lPHOENIX§c ability is on cooldown!");
                        player.sendTitle("§c§lCooldown!", "§b§lPHOENIX ability is not ready yet!", 10, 70, 20);
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c§lPHOENIX ability is on cooldown!"));
                    }
                }
            }
        }
    }

}
