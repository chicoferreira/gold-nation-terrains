package com.github.chicoferreira.goldnation.terrains.terrain.database.mapper;

import com.github.chicoferreira.goldnation.terrains.database.mapper.Mapper;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.terrain.database.Area2DPojo;
import com.github.chicoferreira.goldnation.terrains.terrain.database.LocationPojo;
import com.github.chicoferreira.goldnation.terrains.terrain.database.TerrainPojo;
import com.github.chicoferreira.goldnation.terrains.util.Area2D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;
import java.util.UUID;

public class TerrainMapper implements Mapper<Terrain, TerrainPojo> {

    @Override
    public TerrainPojo to(Terrain terrain) {
        TerrainPojo terrainPojo = new TerrainPojo();

        terrainPojo.setUuid(terrain.getUuid());

        terrainPojo.setOwnerName(terrain.getOwner());
        terrainPojo.setTerrainSize(terrain.getTerrainSize());

        Area2DPojo area2D = new Area2DPojo();
        area2D.setStartX(terrain.getArea().getStartX());
        area2D.setEndX(terrain.getArea().getEndX());
        area2D.setStartZ(terrain.getArea().getStartZ());
        area2D.setEndZ(terrain.getArea().getEndZ());
        terrainPojo.setArea2D(area2D);

        LocationPojo locationPojo = new LocationPojo();
        locationPojo.setWorldName(terrain.getSpawnLocation().getWorld().getName());
        locationPojo.setX(terrain.getSpawnLocation().getX());
        locationPojo.setY(terrain.getSpawnLocation().getY());
        locationPojo.setZ(terrain.getSpawnLocation().getZ());
        locationPojo.setYaw(terrain.getSpawnLocation().getYaw());
        locationPojo.setPitch(terrain.getSpawnLocation().getPitch());
        terrainPojo.setLocation(locationPojo);

        terrainPojo.setPvpEnabled(terrain.isPvpEnabled());
        terrainPojo.setTrustedUsers(terrain.getTrustedUsers());

        return terrainPojo;
    }

    @Override
    public Terrain from(TerrainPojo terrainPojo) {
        UUID uuid = terrainPojo.getUuid();

        String ownerName = terrainPojo.getOwnerName();
        int terrainSize = terrainPojo.getTerrainSize();

        Area2DPojo area2DPojo = terrainPojo.getArea2D();
        Area2D area2D = new Area2D(
                area2DPojo.getStartX(),
                area2DPojo.getEndX(),
                area2DPojo.getStartZ(),
                area2DPojo.getEndZ());

        LocationPojo locationPojo = terrainPojo.getLocation();
        World world = Bukkit.getWorld(locationPojo.getWorldName());

        if (world == null) {
            throw new NullPointerException("Couldn't find world " + locationPojo.getWorldName() + " this may cause the plugin not work as intentional:");
        }

        Location location = new Location(
                world,
                locationPojo.getX(),
                locationPojo.getY(),
                locationPojo.getZ(),
                locationPojo.getYaw(),
                locationPojo.getPitch());

        boolean pvpEnabled = terrainPojo.isPvpEnabled();
        List<String> trustedUsers = terrainPojo.getTrustedUsers();

        return new Terrain(uuid, ownerName, terrainSize, area2D, location, pvpEnabled, trustedUsers);
    }


}
