package com.github.chicoferreira.goldnation.terrains.terrain.controller;

import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Area2D;
import com.github.chicoferreira.goldnation.terrains.wall.BlockWallPlacer;
import com.github.chicoferreira.goldnation.terrains.wall.WallPlacer;
import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.UUID;
import java.util.stream.Collectors;

public class TerrainControllerImpl implements TerrainController {

    private TerrainsPlugin plugin;
    private WallPlacer wallPlacer;

    public TerrainControllerImpl(TerrainsPlugin plugin) {
        this.plugin = plugin;
        this.wallPlacer = new BlockWallPlacer(Material.FENCE);
    }

    @Override
    public boolean acquire(User user, Location location, int size) {
        Area2D area2D = new Area2D(location.getBlockX(), location.getBlockZ(), size);
        Terrain terrain = new Terrain(UUID.randomUUID(), user.getName(), size, area2D, location, false, Lists.newArrayList());

        if (!hasNearbyTerrains(location, size)) {
            create(terrain);
            placeWalls(terrain);
            return true;
        }

        return false;
    }

    @Override
    public boolean hasNearbyTerrains(Location location, int radius) {
        return plugin.getTerrainStorage().hasNearbyTerrains(location, radius);
    }

    @Override
    public double calculatePrice(int radius) {
        return plugin.getConstants().terrainPricePerBlock * radius * radius;
    }

    @Override
    public void create(Terrain terrain) {
        plugin.getTerrainStorage().create(terrain);
        plugin.getUserStorage().addTerrain(terrain.getOwner(), terrain);
    }

    @Override
    public void remove(Terrain terrain) {
        plugin.getTerrainStorage().remove(terrain);
        plugin.getUserStorage().removeTerrain(terrain.getOwner(), terrain);
    }

    private void placeWalls(Terrain terrain) {
        wallPlacer.place(terrain.getArea().getBorders()
                .stream()
                .map(position2D -> {
                    Location spawnLocation = terrain.getSpawnLocation();
                    return new Location(spawnLocation.getWorld(), position2D.getX(), spawnLocation.getY(), position2D.getZ());
                }).collect(Collectors.toList()));
    }
}
