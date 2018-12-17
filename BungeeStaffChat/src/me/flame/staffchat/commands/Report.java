package me.flame.staffchat.commands;

import me.flame.staffchat.events.ChatUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Report extends Command {


    public Report(){
        super("report", "report.use", "rep");
    }

    public void execute(CommandSender sender, String[] args){
        if(sender instanceof ProxiedPlayer){

            ProxiedPlayer p = (ProxiedPlayer) sender;


            if(args.length == 0){
                p.sendMessage(ChatUtils.format("&8[&3Hyrax&bMC&8] &7Usage: &7/report &b<name> &7<reason>"));
                return;
            }

            ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);

            if (target == null) {
                p.sendMessage(ChatUtils.format("&8[&3Hyrax&bMC&8] &7Player &b'" + args[0] + "' &7has not been found!"));
                return;
            } if(target.getName() == p.getName()) {
                p.sendMessage(ChatUtils.format("&8[&3Hyrax&bMC&8] &7You are not allowed to report yourself.!"));
                return;
            } if(target.hasPermission("report.see")){
                p.sendMessage(ChatUtils.format("&8[&3Hyrax&bMC&8] &7You are not allowed to report this player!"));
                p.sendMessage(ChatUtils.format("&8[&3Hyrax&bMC&8] &7You can report this player on the forums!"));
                return;
            }
            if(args.length == 1){

                p.sendMessage(ChatUtils.format("&8[&3Hyrax&bMC&8] &7/report &7" + target.getName() + " &b<reden>"));
                return;

            } if(args.length >= 2){

                String reason = "";

                for (int i = 1; i < args.length; i++){
                    String arg = (args[i] + " ");
                    String argcolor = ChatColor.GRAY + arg;
                    reason = (reason + argcolor);
                }

                for(ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()){
                    if(staff.hasPermission("report.see")){
                        staff.sendMessage(ChatUtils.format("&3[Report] &b" + p.getName() + " &7has reported " + target.getName() + " &b&o(" + target.getServer().getInfo().getName() + ")"));
                        staff.sendMessage(ChatUtils.format("  &3Reason: " + reason));
                    }
                }

                p.sendMessage(ChatUtils.format("&8[&3Hyrax&bMC&8] &7You have reported &e" + target.getName() + " &7for &e" + reason));
                return;
            }
        }
    }

}