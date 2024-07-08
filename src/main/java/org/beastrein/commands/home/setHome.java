package org.beastrein.commands.home;

import org.beastrein.DBManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setHome implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage("Please specify a home");
                return true;
            }
            player.sendMessage("Set home: " + args[0]);
            try {
                DBManager.addHome(player.getUniqueId() + "|" + args[0], player.getLocation());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}