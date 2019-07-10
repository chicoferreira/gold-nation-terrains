package com.github.chicoferreira.goldnation.terrains.terrain;

import com.github.chicoferreira.goldnation.terrains.util.Area2D;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

import java.util.*;

public class TerrainStorage {

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

    public Set<Terrain> getNearbyTerrains(Location location, int radius) {
        Set<Terrain> terrains = new HashSet<>();

        Area2D area2D = new Area2D(location.getBlockX(), location.getBlockZ(), radius);
        for (Position2D position2D : area2D) {
            Terrain terrain = get(position2D);
            if (terrain != null) {
                terrains.add(terrain);
            }
        }

        return terrains;
    }

}
