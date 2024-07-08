package org.beastrein.commands.home;

import org.beastrein.DBManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage("Please specify a home");
                return true;
            }
            try {
                Location location = DBManager.getHome(player.getUniqueId().toString(), args[0]);
                if (location == null) {
                    player.sendMessage("Home not found.");
                    return true;
                }
                player.teleport(location);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}