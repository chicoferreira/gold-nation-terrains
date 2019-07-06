package com.github.chicoferreira.goldnation.terrains.user;

import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class User {

    private String name;
    private List<Terrain> terrainList;

    private transient Player player;

    public User(String name) {
        this(name, Lists.newArrayList());
    }

    public User(String name, List<Terrain> terrainList) {
        this.name = name;
        this.terrainList = terrainList;
    }

    public String getName() {
        return name;
    }

    public List<Terrain> getTerrainList() {
        return terrainList;
    }

    public Player getPlayer() {
        if (this.player == null || !this.player.isOnline()) {
            this.player = Bukkit.getPlayerExact(name);
        }
        return player;
    }

    public void sendMessage(String message, Object... objects) {
        Player player = getPlayer();
        if (player != null) {
            player.sendMessage(String.format(message, objects));
        }
    }
}
