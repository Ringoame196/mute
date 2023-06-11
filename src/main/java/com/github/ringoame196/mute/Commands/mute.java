package com.github.ringoame196.mute.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class mute implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.isOp()) {
            //opのみ実行させる
            commandSender.sendMessage(ChatColor.RED+"あなたは権限を持っていません");
            return true;
        }
        if (commandSender instanceof BlockCommandSender) {
            //コマンドブロックからの実行を禁止する
            commandSender.sendMessage("コマンドブロックからの実行は禁止されています");
            return true;
        }
        if(strings.length > 0) {
            //arg-1に入力されたプレイヤーを取得
            Player targetplayer = Bukkit.getPlayer(strings[0]);
            if (targetplayer == null) {
                //プレイヤーが見当たらなかったときの処理
                commandSender.sendMessage(ChatColor.RED + "プレイヤーが見つかりませんでした");
                return true;
            }

            if (targetplayer.getScoreboardTags().contains("mute")) {
                //既にミュートにされている場合
                commandSender.sendMessage(ChatColor.RED + targetplayer.getName() + "は既にミュートになっています");
                return true;
            }

            targetplayer.addScoreboardTag("mute");
            commandSender.sendMessage(ChatColor.RED + targetplayer.getName() + "をミュートにしました");

            //op全員に通知
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.isOp()&&!player.getName().equals(commandSender.getName())){
                    player.sendMessage(ChatColor.GRAY+targetplayer.getName() + "をミュートにしました by "+commandSender.getName());
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
            //ミュートされないいないプレイヤーをtabに出す
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(!player.getScoreboardTags().contains("mute"))
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
