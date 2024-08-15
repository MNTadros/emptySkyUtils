package com.empty.emptyskyutils.utils;

import org.bukkit.entity.Player;


public class effectHandler {
    public void applyOriginEffects(Player player, originType origin) {
        if (origin == null) {
            throw new IllegalArgumentException("Origin cannot be null");
        }
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        for (originEffect effect : origin.getEffects()) {
            effect.applyEffect(player);
        }
    }
}
