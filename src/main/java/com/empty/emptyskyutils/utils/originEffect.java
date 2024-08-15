package com.empty.emptyskyutils.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class originEffect {
    private final effectType type;
    private final double value;

    public originEffect(effectType type, double value) {
        this.type = type;
        this.value = value;
    }

    public effectType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public void applyEffect(Player player) {
        switch (type) {
            case PERMANENT_HASTE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, Integer.MAX_VALUE, (int) value - 1, true, false));
                break;
            case MINING_SPEED:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, (int) (value * 10)));
                break;
            case PERMANENT_LUCK:
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, (int) value - 1, true, false));
                break;
            default:
                throw new IllegalArgumentException("Unsupported effect type: " + type);
        }
    }
}
