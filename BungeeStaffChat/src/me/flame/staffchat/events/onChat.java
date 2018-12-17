package me.flame.staffchat.events;

import me.flame.staffchat.commands.StaffChat;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class onChat implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();

        if (StaffChat.StaffToggle.contains(p.getName())) {
            if(!e.getMessage().contains("/")) {

                e.setCancelled(true);

                String message = e.getMessage();

                for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                    if (staff.hasPermission("staffchat.use")) {
                        if (!StaffChat.StaffMute.contains(staff.getName())) {
                            staff.sendMessage(ChatUtils.format("&8[&3&lSC&8] &b&o(" + p.getServer().getInfo().getName() + ") &7" + p.getName() + "&8 » &b" + message));
                        } else {
                        }
                    }
                }
                return;
            } else {

            }
        } else {
            return;
        }
    }
}
