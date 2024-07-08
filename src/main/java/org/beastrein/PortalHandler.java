package org.beastrein;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPortalEvent;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class PortalHandler implements Listener {
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (event.getFrom().getWorld().getName().equals("world")) {
            event.getPlayer().teleport(Bukkit.getWorld("Survival").getSpawnLocation());
            event.setCancelled(true);

        } else if (event.getFrom().getWorld().getName().equals("world_nether")) {
            Location to = event.getTo();
            to.setWorld(Bukkit.getWorld("Survival"));
            event.setTo(to);
            event.setCanCreatePortal(true);
        }
    }
}
