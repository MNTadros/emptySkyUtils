package com.empty.emptyskyutils.utils;

import org.bukkit.entity.Player;

public class effectHandler {

    public void applyOriginEffects(Player player, originType origin) { //TODO not workin -->
        if (origin == null) {
            throw new IllegalArgumentException("Origin cannot be null");
        }
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        player.sendMessage("§6Applying effects for origin: " + origin.getName() + "§6");

        if (origin.getEffects() == null || origin.getEffects().length == 0) {
            player.sendMessage("§cNo effects to apply for origin: " + origin.getName());
            return;
        }

        for (originEffect effect : origin.getEffects()) {
            if (effect == null) {
                player.sendMessage("§cFound a null effect in origin: " + origin.getName());
                continue;
            }

            effect.applyEffect(player);
            player.sendMessage("§6Applied effect: " + effect.getClass().getSimpleName() + "§6");
        }
    } //TODO not workin -->
}
