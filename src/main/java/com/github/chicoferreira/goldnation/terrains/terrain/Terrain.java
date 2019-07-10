package com.github.chicoferreira.goldnation.terrains.terrain;

import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Area2D;
import org.bukkit.Location;

import java.util.List;

public class Terrain {

    private User owner;

    private int terrainSize;
    private Area2D area;

    private Location spawnLocation;
    private boolean pvpEnabled;
    private List<User> trustedUsers;

    public Terrain(User owner, int terrainSize, Area2D area2D, Location spawnLocation, boolean pvpEnabled, List<User> trustedUsers) {
        this.owner = owner;
        this.terrainSize = terrainSize;
        this.area = area2D;
        this.spawnLocation = spawnLocation;
        this.pvpEnabled = pvpEnabled;
        this.trustedUsers = trustedUsers;
    }

    public User getOwner() {
        return owner;
    }

    public int getTerrainSize() {
        return terrainSize;
    }

    public Area2D getArea() {
        return area;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public boolean isPvpEnabled() {
        return pvpEnabled;
    }

    public List<User> getTrustedUsers() {
        return trustedUsers;
    }
}
