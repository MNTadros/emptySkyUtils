package com.empty.emptyskyutils.utils;

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
        switch (effect.getType()) {
            case PERMANENT_HASTE:
                player.removePotionEffect(PotionEffectType.HASTE);
                break;
            case MINING_SPEED:
                player.removePotionEffect(PotionEffectType.SPEED);
                break;
            case PERMANENT_LUCK:
                player.removePotionEffect(PotionEffectType.LUCK);
                break;
            default:
                throw new IllegalArgumentException("Unsupported effect type: " + effect.getType());
        }
    }
}
