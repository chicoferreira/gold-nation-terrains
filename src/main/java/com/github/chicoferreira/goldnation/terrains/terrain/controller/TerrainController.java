package com.github.chicoferreira.goldnation.terrains.terrain.controller;

import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.Location;

import java.math.BigDecimal;

public interface TerrainController {

    boolean buy(User user, Location location, int size);

    boolean expand(Terrain terrain, int newSize);

    boolean hasNearbyTerrains(Location location, int size);

    boolean canExpand(Terrain terrain, int size);

    double calculatePrice(int radius);

    double calculateExpansionPrice(Terrain terrain, int newSize);

    void remove(Terrain terrain);

    void create(Terrain terrain);

    void putUpForSale(Terrain terrain, BigDecimal bigDecimal);

    void removeFromSale(Terrain terrain);

    void acquire(User user, Terrain terrain);

    double abandon(User user, Terrain terrain);

}
