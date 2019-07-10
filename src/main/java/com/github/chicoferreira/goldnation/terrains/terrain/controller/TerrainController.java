package com.github.chicoferreira.goldnation.terrains.terrain.controller;

import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.Location;

public interface TerrainController {

    boolean buy(User user, Location location, int size);

    boolean hasNearbyTerrains(Location location, int radius);


}
