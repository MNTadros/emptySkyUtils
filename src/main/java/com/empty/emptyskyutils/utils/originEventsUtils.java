package com.empty.emptyskyutils.utils;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.md_5.bungee.api.chat.TextComponent;

public class originEventsUtils {

    public static void applyPhoenixEffects(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 3));

        player.sendMessage("§cYour used your §c§lPHOENIX§c ability!");
        player.sendTitle("§c**§c§lPHOENIX§c**","§b(TRADER)", 10, 70, 20);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§e§l**PHOENIX**"));
    }
}
