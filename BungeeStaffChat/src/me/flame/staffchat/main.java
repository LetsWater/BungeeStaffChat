package me.flame.staffchat;

import me.flame.staffchat.commands.StaffChat;
import net.md_5.bungee.api.plugin.Plugin;

public class main extends Plugin {

    public void onEnable(){

        getProxy().getPluginManager().registerCommand(this, new StaffChat());
    }


}