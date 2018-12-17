package me.flame.staffchat.events;

import me.flame.staffchat.commands.StaffChat;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinGame implements Listener {




    @EventHandler
    public void onJoin(ServerConnectEvent e){

        ProxiedPlayer p = e.getPlayer();

        if(StaffChat.StaffMute.contains(p.getName())){
            p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7You staffchat is still &4muted&7!"));
            p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7Unmute the staffchat by using &b/sc mute"));
            return;
        } else {
            return;
        }


    }
}
