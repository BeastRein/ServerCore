package org.beastrein.commands.home;

import org.beastrein.DBManager;
import org.beastrein.ServerCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Set;

public class CopyHomes implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("Copying Homes");
            try {
                DBManager.initDB();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (player.isOp()) {
                FileConfiguration config = ServerCore.getPlugin().getConfig();
                Set<String> keys = config.getKeys(true);
                for (String key: keys) {
                    player.sendMessage("Copied home: " + key.split("\\|")[1] );
                    try {
                        DBManager.addHome(key, config.getLocation(key));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }
}