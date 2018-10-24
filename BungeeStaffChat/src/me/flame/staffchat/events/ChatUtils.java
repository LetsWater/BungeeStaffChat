package me.flame.staffchat.events;


import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatUtils {

    public static TextComponent format(String input){
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', input));
    }
}