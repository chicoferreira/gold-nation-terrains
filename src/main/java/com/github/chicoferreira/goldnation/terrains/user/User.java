package com.github.chicoferreira.goldnation.terrains.user;

import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class User {

    private String name;
    private List<UUID> terrainList;

    private transient Player player;

    public User(String name) {
        this(name, Lists.newArrayList());
    }

    public User(String name, List<UUID> terrainList) {
        this.name = name;
        this.terrainList = terrainList;
    }

    public String getName() {
        return name;
    }

    public List<UUID> getTerrainList() {
        return terrainList;
    }

    public Player getPlayer() {
        if (this.player == null || !this.player.isOnline()) {
            updatePlayer();
        }
        return player;
    }

    public void updatePlayer() {
        this.player = Bukkit.getPlayerExact(name);
    }

    public void sendMessage(String message, Object... objects) {
        Player player = getPlayer();
        if (player != null) {
            player.sendMessage(String.format(message, objects));
        }
    }

    public void addTerrain(Terrain terrain) {
        this.terrainList.add(terrain.getUuid());
    }
}
