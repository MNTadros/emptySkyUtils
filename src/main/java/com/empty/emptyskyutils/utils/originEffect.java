package com.empty.emptyskyutils.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class originEffect {
    private final effectType type;
    private final double modifier;

    public originEffect(effectType type, double modifier) {
        this.type = type;
        this.modifier = modifier;
    }

    public effectType getType() {
        return type;
    }

    public double getModifier() {
        return modifier;
    }

    public void applyEffect(Player player) {
        switch (type) {
            case PERMANENT_HASTE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, Integer.MAX_VALUE, (int) modifier, true, false));
                break;
            case MINING_SPEED:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, (int) modifier));
                break;
            case PERMANENT_LUCK:
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, (int) modifier, true, false));
                break;
            case PHOENIX:
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, (int) modifier, true, false));
                break;
            default:
                throw new IllegalArgumentException("Unsupported effect type: " + type);
        }
    }
}
