package com.github.chicoferreira.goldnation.terrains.terrain.controller;

import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.Location;

public class TerrainControllerImpl implements TerrainController {

    @Override
    public boolean buy(User user, Location location, int size) {
        return false;
    }

    @Override
    public boolean hasNearbyTerrains(Location location, int radius) {
        return false;
    }

}
