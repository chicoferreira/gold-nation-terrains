package com.github.chicoferreira.goldnation.terrains.terrain.database;

import com.github.chicoferreira.goldnation.terrains.database.Pojo;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;
import java.util.UUID;

@Entity(value = "terrains", noClassnameStored = true)
public class TerrainPojo extends Pojo {

    @Id
    private UUID uuid;

    private String ownerName;
    private int terrainSize;

    @Embedded(value = "area")
    private Area2DPojo area2D;

    @Embedded(value = "location")
    private LocationPojo location;

    private boolean pvpEnabled;
    private List<String> trustedUsers;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getTerrainSize() {
        return terrainSize;
    }

    public void setTerrainSize(int terrainSize) {
        this.terrainSize = terrainSize;
    }

    public Area2DPojo getArea2D() {
        return area2D;
    }

    public void setArea2D(Area2DPojo area2D) {
        this.area2D = area2D;
    }

    public LocationPojo getLocation() {
        return location;
    }

    public void setLocation(LocationPojo location) {
        this.location = location;
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

    public void setTrustedUsers(List<String> trustedUsers) {
        this.trustedUsers = trustedUsers;
    }
}
