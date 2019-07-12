package com.github.chicoferreira.goldnation.terrains.terrain.database.mapper;

import com.github.chicoferreira.goldnation.terrains.database.mapper.Mapper;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.terrain.database.Area2DPojo;
import com.github.chicoferreira.goldnation.terrains.terrain.database.LocationPojo;
import com.github.chicoferreira.goldnation.terrains.terrain.database.TerrainPojo;
import com.github.chicoferreira.goldnation.terrains.util.Area2D;
import org.bukkit.Location;

import java.util.ArrayList;
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

        terrainPojo.setSpawnLocation(LocationPojo.from(terrain.getSpawnLocation()));

        terrainPojo.setMiddleLocation(LocationPojo.from(terrain.getMiddleLocation()));

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

        Location spawnLocation = LocationPojo.to(terrainPojo.getSpawnLocation());

        Location middleLocation = LocationPojo.to(terrainPojo.getMiddleLocation());

        boolean pvpEnabled = terrainPojo.isPvpEnabled();
        List<String> trustedUsers = terrainPojo.getTrustedUsers() != null ? terrainPojo.getTrustedUsers() : new ArrayList<>();

        return new Terrain(uuid, ownerName, terrainSize, area2D, spawnLocation, middleLocation, pvpEnabled, trustedUsers);
    }


}
