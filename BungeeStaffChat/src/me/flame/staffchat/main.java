package me.flame.staffchat;

import me.flame.staffchat.commands.StaffChat;
import me.flame.staffchat.events.JoinGame;
import me.flame.staffchat.events.onChat;
import net.md_5.bungee.api.plugin.Plugin;

public class main extends Plugin {

    public void onEnable(){

        // Events
        getProxy().getPluginManager().registerListener(this, new JoinGame());
        getProxy().getPluginManager().registerListener(this, new onChat());

        // Commands
        getProxy().getPluginManager().registerCommand(this, new StaffChat());
    }


}