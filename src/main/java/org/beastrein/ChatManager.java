package org.beastrein;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        message = message.replaceAll("(?i)nigger", "******");
        message = message.replaceAll("(?i)nigga", "*****");
        message = message.replaceAll("(?i)faggot", "******");
        event.setMessage(message);
        if (message.toLowerCase().contains("skibidi") || message.toLowerCase().contains("gyatt")) {
            Bukkit.getScheduler().runTask(ServerCore.getPlugin(), () -> event.getPlayer().kickPlayer("STFU"));
            event.setCancelled(true);
        }
    }
}
