package org.beastrein.commands.home;

import org.beastrein.DBManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class openInv implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Console/Command Block Not Supported");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("Please specify a player");
            return true;
        }
        if (player.isOp()) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage("Could not find player");
                return true;
            }
            player.openInventory(target.getInventory());
        }
        return true;
    }
}