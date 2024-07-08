package org.beastrein.commands.tp;

import org.beastrein.DBManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpWorld implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage("Please specify a world");
                return true;
            }
            World world = Bukkit.getWorld(args[0]);
            if (world != null) {
                player.teleport(world.getSpawnLocation());
            } else {
                player.sendMessage("World not found");
            }
        }
        return true;
    }
}