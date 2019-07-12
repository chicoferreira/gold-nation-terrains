package com.github.chicoferreira.goldnation.terrains.terrain.database;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class LocationPojo {

    private String worldName;
    private double x, y, z;
    private float yaw, pitch;

    public static LocationPojo from(Location location) {
        LocationPojo locationPojo = new LocationPojo();

        locationPojo.setWorldName(location.getWorld().getName());
        locationPojo.setX(location.getX());
        locationPojo.setY(location.getY());
        locationPojo.setZ(location.getZ());
        locationPojo.setYaw(location.getYaw());
        locationPojo.setPitch(location.getPitch());

        return locationPojo;
    }

    public static Location to(LocationPojo locationPojo) {
        String worldName = locationPojo.getWorldName();
        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            throw new NullPointerException("Couldn't find world " + worldName + " this may cause the plugin not work as intentional:");
        }

        return new Location(
                world,
                locationPojo.getX(),
                locationPojo.getY(),
                locationPojo.getZ(),
                locationPojo.getYaw(),
                locationPojo.getPitch());
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
}
