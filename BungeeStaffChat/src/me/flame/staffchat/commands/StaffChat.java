package me.flame.staffchat.commands;

import me.flame.staffchat.events.ChatUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;

public class StaffChat extends Command {

    public static ArrayList<String> StaffMute = new ArrayList<>();
    public static ArrayList<String> StaffToggle = new ArrayList<>();

    public StaffChat() {
        super("staffchat", "staffchat.use", "sc");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;

            if (p.hasPermission("staffchat.use")) {
                if (args.length == 0) {

                    p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7/staffchat <message>"));
                    p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7/staffchat mute"));
                    return;
                } if(args[0].equalsIgnoreCase("toggle")){
                    if(!StaffToggle.contains(p.getName())){
                        p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7You have toggled the staffchat"));
                        p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7You can are chatting in the Staffchat now!"));

                        StaffToggle.add(p.getName());
                        return;
                    } else {
                        p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7You have toggled the staffchat"));
                        p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7You can are chatting in the public now!"));

                        StaffToggle.remove(p.getName());
                        return;
                    }
                }
                if (args[0].equalsIgnoreCase("mute")) {
                    if (!StaffMute.contains(p.getName())) {

                        p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7You have &4muted &7the staffchat!"));

                        StaffMute.add(p.getName());
                        return;
                    } else {

                        p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7You have &aunmuted &7the staffchat!"));

                        StaffMute.remove(p.getName());
                        return;
                    }
                } else {
                    if (!StaffMute.contains(p.getName())) {
                        String message = "";
                        for(String part : args) {
                            if (message != "") message += " ";
                            message += part;
                        }

                        for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                            if (staff.hasPermission("staffchat.use")) {
                                if (!StaffMute.contains(staff.getName())) {
                                    staff.sendMessage(ChatUtils.format("&8[&3&lSC&8] &b&o(" + p.getServer().getInfo().getName() + ") &7" + p.getName() + "&8 » &b" + message));
                                } else {

                                }

                            }
                        }
                    } else {
                        p.sendMessage(ChatUtils.format("&8[&3&lSC&8] &7Your staffchat is &4muted&7! Use &a/staffchat &amute &7to &7unmute it!"));
                    }
                }
            } else {
                p.sendMessage(ChatUtils.format("&cGeen permissions"));
            }
        }
    }
}