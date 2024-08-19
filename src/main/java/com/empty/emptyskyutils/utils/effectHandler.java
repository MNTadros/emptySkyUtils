package com.empty.emptyskyutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import java.util.HashMap;
import java.util.Map;

public class effectHandler {

    private final Map<Player, originType> playerOrigins = new HashMap<>();

    public void applyOriginEffects(Player player, originType origin) {
        removeOriginEffects(player);

        player.sendMessage("§6Applying effects for origin: " + origin.getName() + "§6");

        for (originEffect effect : origin.getEffects()) {
            if (effect != null) {
                effect.applyEffect(player);
                player.sendMessage("§6Applied effect: " + effect.getType() + " Level: " + effect.getModifier() + "§6");
            } else {
                player.sendMessage("§cFound a null effect in origin: " + origin.getName());
            }
        }

        playerOrigins.put(player, origin);
    }

    public void removeOriginEffects(Player player) {  // !!!
        originType currentOrigin = playerOrigins.get(player);
        if (currentOrigin != null) {
            for (originEffect effect : currentOrigin.getEffects()) {
                if (effect != null) {
                    removeEffect(player, effect);
                }
            }
            playerOrigins.remove(player);
        }
    }

    private void removeEffect(Player player, originEffect effect) {
        Bukkit.getLogger().info("Removing effect: " + effect.getType() + " for player: " + player.getName());

        switch (effect.getType()) {
            case PERMANENT_HASTE:
                if (player.hasPotionEffect(PotionEffectType.HASTE)) {
                    player.removePotionEffect(PotionEffectType.HASTE);
                    Bukkit.getLogger().info("Removed HASTE effect.");
                }
                break;
            case MINING_SPEED:
                if (player.hasPotionEffect(PotionEffectType.SPEED)) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    Bukkit.getLogger().info("Removed SPEED effect.");
                }
                break;
            case PERMANENT_LUCK:
                if (player.hasPotionEffect(PotionEffectType.LUCK)) {
                    player.removePotionEffect(PotionEffectType.LUCK);
                    Bukkit.getLogger().info("Removed LUCK effect.");
                }
                break;
            case PHOENIX:
                // Handle PHOENIX effect if necessary
                break;
            default:
                throw new IllegalArgumentException("Unsupported effect type: " + effect.getType());
        }
    }


}
