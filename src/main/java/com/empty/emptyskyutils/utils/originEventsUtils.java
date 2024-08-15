package com.empty.emptyskyutils.utils;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.md_5.bungee.api.chat.TextComponent;

public class originEventsUtils {

    public static void applyPhoenixEffects(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 3));

        player.sendTitle("§b§lTRADER", "§c§lPHOENIX has activated!", 10, 70, 20);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§b§lTRADER §c§lPHOENIX has activated!"));
    }
}
