package com.github.ringoame196.mute;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class Events implements Listener {
    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        if(player.getScoreboardTags().contains("mute"))
        {
           e.setCancelled(true);
           player.sendMessage(ChatColor.RED+"<"+player.getDisplayName()+"> あなたはミュートされています");
        }
    }
    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage();
        if(command.contains("/tag")&&command.contains("mute"))
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED+"muteタグをいじることは禁止されています");
        }
        else if (command.contains("/w")||command.contains("/msg")||command.contains("/tell"))
        {
            if(e.getPlayer().getScoreboardTags().contains("mute"))
            {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED+"あなたはミュートされています");
            }
        }
    }
    @EventHandler
    public void onServerCommandEvent(ServerCommandEvent e) {
        String command = e.getCommand();
        if(command.contains("tag")&&command.contains("mute"))
        {
            e.setCancelled(true);
            e.getSender().sendMessage(ChatColor.RED+"muteタグをいじることは禁止されています");
        }
    }
}
