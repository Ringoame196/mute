package com.github.ringoame196.mute.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class unmute implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.isOp()) {
            //opのみ実行
            commandSender.sendMessage(ChatColor.RED+"あなたは権限を持っていません");
            return true;
        }
        if (commandSender instanceof BlockCommandSender) {
            //コマンドブロックからの実行を禁止
            commandSender.sendMessage("コマンドブロックからの実行は禁止されています。");
            return true;
        }
        if(strings.length > 0){
            //arg-1に入力されたプレイヤーを取得
            Player targetplayer = Bukkit.getPlayer(strings[0]);
            if(targetplayer==null)
            {
                //プレイヤーが見当たらなかったときの処理
                commandSender.sendMessage(ChatColor.RED+"プレイヤーが見つかりませんでした");
                return true;
            }

            if(!targetplayer.getScoreboardTags().contains("mute"))
            {
                //ミュートされていない場合
                commandSender.sendMessage(ChatColor.RED+targetplayer.getName()+"はミュートになっていません");
                return true;
            }

            targetplayer.removeScoreboardTag("mute");
            commandSender.sendMessage(ChatColor.GREEN+targetplayer.getName()+"をミュート解除しました");

            //op全員に通知
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.isOp()&&!player.getName().equals(commandSender.getName())){
                    player.sendMessage(ChatColor.GRAY+targetplayer.getName() + "をミュート解除しました by "+commandSender.getName());
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1)
        {
            List<String> suggestions = new ArrayList<>();
            //みゅーとされているプレイヤーをtabに出す
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.getScoreboardTags().contains("mute"))
                {
                    suggestions.add(player.getName());
                }
            }
            return suggestions;
        }
        else
        {
            List<String> suggestions = new ArrayList<>();
            return suggestions;
        }
    }
}
