package com.github.chicoferreira.goldnation.terrains.terrain.listener;

import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.terrain.TerrainStorage;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TerrainListener implements Listener {

    private TerrainsPlugin plugin;

    public TerrainListener(TerrainsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        Terrain terrain = plugin.getTerrainStorage().get(location);
        if (terrain != null && !terrain.isAllowed(player.getName())) {
            event.setCancelled(true);
            if (plugin.getConstants().terrainCantBreakBlock != null) {
                player.sendMessage(plugin.getConstants().terrainCantBreakBlock.replace("<owner>", terrain.getOwner()));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        Terrain terrain = plugin.getTerrainStorage().get(location);
        if (terrain != null && !terrain.isAllowed(player.getName())) {
            event.setCancelled(true);
            if (plugin.getConstants().terrainCantPlaceBlock != null) {
                player.sendMessage(plugin.getConstants().terrainCantPlaceBlock.replace("<owner>", terrain.getOwner()));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPvp(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player player = (Player) event.getEntity();

            TerrainStorage terrainStorage = plugin.getTerrainStorage();

            Terrain terrain = terrainStorage.get(damager.getLocation());
            Terrain terrain2 = terrainStorage.get(player.getLocation());

            if (terrain != null && terrain2 != null) {
                event.setCancelled(!terrain.isPvpEnabled() && !terrain2.isPvpEnabled());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.hasBlock()) {
            Location location = event.getClickedBlock().getLocation();

            Terrain terrain = plugin.getTerrainStorage().get(location);
            if (terrain != null && !terrain.isAllowed(player.getName())) {
                event.setCancelled(true);
            }
        }
    }
}
