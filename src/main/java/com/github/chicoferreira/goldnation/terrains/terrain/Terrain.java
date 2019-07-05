package com.github.chicoferreira.goldnation.terrains.terrain;

import com.github.chicoferreira.goldnation.terrains.user.User;
import com.google.common.collect.Lists;
import org.bukkit.Location;

import java.util.List;

public class Terrain {

    private User owner;

    private int terrainSize;
    private Location startLocation, endLocation;
    private Location terrainSpawnLocation;

    private boolean pvpEnabled;

    private List<User> trustedUsers;

    public Terrain(User owner, int terrainSize, Location startLocation, Location endLocation, Location terrainSpawnLocation) {
        this(owner, terrainSize, startLocation, endLocation, terrainSpawnLocation, false, Lists.newArrayList());
    }

    public Terrain(User owner, int terrainSize, Location startLocation, Location endLocation, Location terrainSpawnLocation, boolean pvpEnabled, List<User> trustedUsers) {
        this.owner = owner;
        this.terrainSize = terrainSize;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.terrainSpawnLocation = terrainSpawnLocation;
        this.pvpEnabled = pvpEnabled;
        this.trustedUsers = trustedUsers;
    }

    public User getOwner() {
        return owner;
    }

    public int getTerrainSize() {
        return terrainSize;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public Location getTerrainSpawnLocation() {
        return terrainSpawnLocation;
    }

    public boolean isPvpEnabled() {
        return pvpEnabled;
    }

    public List<User> getTrustedUsers() {
        return trustedUsers;
    }
}
