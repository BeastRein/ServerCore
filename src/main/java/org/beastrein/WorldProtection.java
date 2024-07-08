package org.beastrein;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class WorldProtection implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.getBlock().getLocation().getWorld().getName().equals("world")) {
            return;
        }
        if (event.getPlayer().isOp()) {
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) {
            return;
        }
        if (!event.getClickedBlock().getLocation().getWorld().getName().equals("world")) {
            return;
        }
        if (event.getPlayer().isOp()) {
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.getBlock().getLocation().getWorld().getName().equals("world")) {
            return;
        }
        if (event.getPlayer().isOp()) {
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().isOp()) {
            event.getPlayer().setDisplayName("["+ChatColor.RED + "Admin" + ChatColor.WHITE + "] " + event.getPlayer().getName());
            event.getPlayer().setPlayerListName("["+ChatColor.RED + "Admin" + ChatColor.WHITE + "] " + event.getPlayer().getName());
            event.getPlayer().setCustomName("["+ChatColor.RED + "Admin" + ChatColor.WHITE + "] " + event.getPlayer().getName());
            event.getPlayer().setCustomNameVisible(true);
        }
        if (event.getPlayer().hasPlayedBefore()) {
            return;
        }
        event.getPlayer().sendMessage("Please Enter The portal at the end of the path at spawn to begin\nuse /discord to join our discord server");

    }
}
