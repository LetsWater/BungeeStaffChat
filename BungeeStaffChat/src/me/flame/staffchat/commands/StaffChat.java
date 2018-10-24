package me.flame.staffchat.commands;

import me.flame.staffchat.events.ChatUtils;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.caching.MetaData;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;

public class StaffChat extends Command {

    public static ArrayList<String> StaffMute = new ArrayList<>();

    public StaffChat() {
        super("staffchat", "staffchat.use", "sc");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;

            LuckPermsApi api = LuckPerms.getApi();

            User playerUser = api.getUser(p.getUniqueId());
            Contexts playerContexts = api.getContextManager().getApplicableContexts(p);
            MetaData playerMetaData = playerUser.getCachedData().getMetaData(playerContexts);
            String playerPrefix = playerMetaData.getPrefix();

            if (p.hasPermission("staffchat.use")) {
                if (args.length == 0) {

                    p.sendMessage(ChatUtils.format("&8[&6&lStaffChat&8] &7/staffchat <message>"));
                    p.sendMessage(ChatUtils.format("&8[&6&lStaffChat&8] &7/staffchat mute"));
                    return;
                }
                if (args[0].equalsIgnoreCase("mute")) {
                    if (!StaffMute.contains(p.getName())) {

                        p.sendMessage(ChatUtils.format("&8[&6&lStaffChat&8] &7You have &4muted &7the staffchat!"));

                        StaffMute.add(p.getName());
                    } else {

                        p.sendMessage(ChatUtils.format("&8[&6&lStaffChat&8] &7You have &aunmuted &7the staffchat!"));

                        StaffMute.remove(p.getName());
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
                                    staff.sendMessage(ChatUtils.format("&8[&6&lStaffChat&8] &e(" + p.getServer().getInfo().getName() + ") " + playerPrefix + " &7" + sender.getName() + "&8» &f" + message));
                                } else {

                                }
                            }
                        }
                    } else {
                        p.sendMessage(ChatUtils.format("&8[&6&lStaffChat&8] &7Your staffchat is &4muted&7! Use &a/staffchat &amute &7to unmute it!"));
                    }
                }
            } else {
                p.sendMessage(ChatUtils.format("&cGeen permissions"));
            }
        }
    }
}