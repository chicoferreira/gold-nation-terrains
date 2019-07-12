package com.github.chicoferreira.goldnation.terrains.terrain;

import com.github.chicoferreira.goldnation.terrains.database.Dao;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.database.TerrainPojo;
import com.github.chicoferreira.goldnation.terrains.terrain.database.mapper.TerrainMapper;
import com.github.chicoferreira.goldnation.terrains.util.Area2D;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

import java.util.*;
import java.util.stream.Collectors;

public class TerrainStorage {

    private TerrainsPlugin plugin;
    private Map<Position2D, Terrain> map;
    private Dao<Terrain> dao;

    public TerrainStorage(TerrainsPlugin plugin) {
        this.plugin = plugin;
        this.map = new TreeMap<>();
        this.dao = plugin.getDatabaseProvider().generateDao(TerrainPojo.class, new TerrainMapper());
    }

    public void create(Terrain terrain) {
        putPositions(terrain);
        this.plugin.getScheduler().makeAsync(() -> dao.saveEntity(terrain));
    }

    private void putPositions(Terrain terrain) {
        for (Position2D position2D : terrain.getArea()) {
            map.put(position2D, terrain);
        }
    }

    public void remove(Terrain terrain) {
        Collection<Terrain> values = map.values();
        while (values.contains(terrain)) {
            values.remove(terrain);
        }

        this.plugin.getScheduler().makeAsync(() -> dao.removeEntity(terrain));
    }

    public Terrain get(Position2D position2D) {
        return map.get(position2D);
    }

    public Terrain get(Location location) {
        return get(new Position2D(location.getBlockX(), location.getBlockZ()));
    }

    public Terrain get(UUID uuid) {
        for (Terrain value : map.values()) {
            if (value.getUuid().equals(uuid)) {
                return value;
            }
        }
        return null;
    }

    public boolean contains(Position2D position2D) {
        return map.containsKey(position2D);
    }

    public boolean hasNearbyTerrains(Location location, int radius) {
        Area2D area2D = new Area2D(location.getBlockX(), location.getBlockZ(), radius);
        for (Position2D position2D : area2D) {
            Terrain terrain = get(position2D);
            if (terrain != null) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNearbyTerrainsExcept(Terrain terrainToIgnore, Location location, int radius) {
        Area2D area2D = new Area2D(location.getBlockX(), location.getBlockZ(), radius);
        for (Position2D position2D : area2D) {
            Terrain terrain = get(position2D);
            if (terrain != null && !terrain.equals(terrainToIgnore)) {
                System.out.println("found terrain: " + terrain.toString());
                return true;
            }
        }
        return false;
    }

    public Map<Position2D, Terrain> getMap() {
        return map;
    }

    public int loadAll() {
        List<Terrain> all = dao.getAll();

        for (Terrain terrain : all) {
            putPositions(terrain);
        }

        return all.size();
    }

    public int saveAll() {
        List<Terrain> terrainList = getMap().values().stream().distinct().collect(Collectors.toList());

        for (Terrain terrain : terrainList) {
            dao.saveEntity(terrain);
        }

        return terrainList.size();
    }
}
