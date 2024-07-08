package org.beastrein.commands.home;

import org.beastrein.DBManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class tabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        try {
            List<String> res = new ArrayList<>();
            List<String> homes = DBManager.getHomesByUUID(player.getUniqueId().toString());
            for (String name: homes) {
                if (name.contains(strings[0])) {
                    res.add(name);
                }
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> res = new ArrayList<>();
            res.add("Error Loading List");
            return res;
        }
    }
}
