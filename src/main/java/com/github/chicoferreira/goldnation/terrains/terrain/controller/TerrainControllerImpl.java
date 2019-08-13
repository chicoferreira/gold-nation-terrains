package com.github.chicoferreira.goldnation.terrains.terrain.controller;

import com.github.chicoferreira.goldnation.terrains.bank.Bank;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Area2D;
import com.github.chicoferreira.goldnation.terrains.wall.BlockWallPlacer;
import com.github.chicoferreira.goldnation.terrains.wall.WallPlacer;
import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.bukkit.Material;

import java.math.BigDecimal;
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
    public boolean buy(User user, Location location, int size) {
        Area2D area2D = new Area2D(location.getBlockX(), location.getBlockZ(), size);
        Terrain terrain = new Terrain(UUID.randomUUID(), user.getName(), size, area2D, location, simplify(location), false, Lists.newArrayList());

        if (!hasNearbyTerrains(location, size)) {
            create(terrain);
            placeWalls(terrain);
            return true;
        }

        return false;
    }

    @Override
    public boolean expand(Terrain terrain, int sizeToExpand) {
        Location middleLocation = terrain.getMiddleLocation();
        if (canExpand(terrain, sizeToExpand)) {
            terrain.setTerrainSize(sizeToExpand);
            terrain.setArea(new Area2D(middleLocation.getBlockX(), middleLocation.getBlockZ(), sizeToExpand));
            plugin.getTerrainStorage().put(terrain);
            placeWalls(terrain);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasNearbyTerrains(Location location, int size) {
        return plugin.getTerrainStorage().hasNearbyTerrains(location, size);
    }

    @Override
    public boolean canExpand(Terrain terrain, int size) {
        return !plugin.getTerrainStorage().hasNearbyTerrainsExcept(terrain, terrain.getMiddleLocation(), size);
    }

    @Override
    public double calculatePrice(int size) {
        return plugin.getConstants().terrainPricePerBlock * size * size;
    }

    @Override
    public double calculateExpansionPrice(Terrain terrain, int newSize) {
        return (newSize * newSize - (terrain.getArea().calculateArea())) * plugin.getConstants().terrainPricePerBlock;
    }

    @Override
    public void create(Terrain terrain) {
        plugin.getTerrainStorage().put(terrain);
        plugin.getUserStorage().addTerrain(terrain.getOwner(), terrain);
    }

    @Override
    public void putUpForSale(Terrain terrain, BigDecimal bigDecimal) {
        terrain.setOnSale(true);
        terrain.setSellPrice(bigDecimal.abs());
    }

    @Override
    public void removeFromSale(Terrain terrain) {
        terrain.setSellPrice(BigDecimal.ZERO);
        terrain.setOnSale(false);
    }

    @Override
    public void acquire(User user, Terrain terrain) {
        if (!terrain.isOnSale() && terrain.getOwner().equals(user.getName())) {
            return;
        }

        Bank bank = plugin.getBank();
        double sellPrice = terrain.getSellPrice().doubleValue();

        if (bank.get(user) < sellPrice) {
            return;
        }

        User terrainOwnerUser = plugin.getUserStorage().get(terrain.getOwner()).join();

        bank.remove(user, sellPrice);
        bank.add(terrainOwnerUser, sellPrice);

        plugin.getUserStorage().removeTerrain(terrain.getOwner(), terrain);
        terrain.setOwner(user.getName());
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

    private Location simplify(Location location) {
        return new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
