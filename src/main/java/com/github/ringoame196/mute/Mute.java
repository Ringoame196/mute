package com.github.ringoame196.mute;

import com.github.ringoame196.mute.Commands.mute;
import com.github.ringoame196.mute.Commands.unmute;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mute extends JavaPlugin {

    private static JavaPlugin plugin;
    private Events events;
    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        //コマンド
        getCommand("mute").setExecutor(new mute());
        getCommand("unmute").setExecutor(new unmute());
        //イベント
        this.events = new Events();
        Bukkit.getPluginManager().registerEvents(this.events,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
    }
    public static JavaPlugin getPlugin(){return plugin;}
}

