package com.github.chicoferreira.goldnation.terrains.terrain;

import com.github.chicoferreira.goldnation.terrains.util.Area2D;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class TerrainStorage {

    // TODO: add terrain dao

    private Map<Position2D, Terrain> map;

    public TerrainStorage() {
        this.map = new TreeMap<>();
    }

    public void create(Terrain terrain) {
        for (Position2D position2D : terrain.getArea()) {
            map.put(position2D, terrain);
        }
    }

    public void remove(Terrain terrain) {
        Collection<Terrain> values = map.values();
        while (values.contains(terrain)) {
            values.remove(terrain);
        }
    }

    public Terrain get(Position2D position2D) {
        return map.get(position2D);
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

    public Map<Position2D, Terrain> getMap() {
        return map;
    }
}
