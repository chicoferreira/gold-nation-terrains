package com.github.chicoferreira.goldnation.terrains.terrain;

import com.github.chicoferreira.goldnation.terrains.util.Area2D;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public class Terrain {

    private UUID uuid;
    private String owner;

    private int terrainSize;
    private Area2D area;

    private Location spawnLocation;

    private Location middleLocation;
    private boolean pvpEnabled;
    private List<String> trustedUsers;

    public Terrain(UUID uuid, String owner, int terrainSize, Area2D area, Location spawnLocation, Location middleLocation, boolean pvpEnabled, List<String> trustedUsers) {
        this.uuid = uuid;
        this.owner = owner;
        this.terrainSize = terrainSize;
        this.area = area;
        this.spawnLocation = spawnLocation;
        this.middleLocation = middleLocation;
        this.pvpEnabled = pvpEnabled;
        this.trustedUsers = trustedUsers;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getOwner() {
        return owner;
    }

    public int getTerrainSize() {
        return terrainSize;
    }

    public void setTerrainSize(int terrainSize) {
        this.terrainSize = terrainSize;
    }

    public Area2D getArea() {
        return area;
    }

    public void setArea(Area2D area) {
        this.area = area;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public Location getMiddleLocation() {
        return middleLocation;
    }

    public boolean isPvpEnabled() {
        return pvpEnabled;
    }

    public void setPvpEnabled(boolean pvpEnabled) {
        this.pvpEnabled = pvpEnabled;
    }

    public List<String> getTrustedUsers() {
        return trustedUsers;
    }
}
