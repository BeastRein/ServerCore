package org.beastrein.commands.info;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class discord implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            TextComponent component = new TextComponent("https://discord.gg/SSAB9CUt");
            component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, component.getText()));
            sender.spigot().sendMessage(component);
        }
        return true;
    }
}