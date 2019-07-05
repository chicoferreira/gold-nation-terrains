package com.github.chicoferreira.goldnation.terrains.util;

import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Area {

    private static final int MAX_Y = 256;

    private int startX;
    private int endX;
    private int startZ;
    private int endZ;

    public Area(int startX, int endX, int startZ, int endZ) {
        this.startX = Math.max(startX, endX);
        this.endX = Math.min(startX, endX);
        this.startZ = Math.max(startZ, endZ);
        this.endZ = Math.min(startZ, endZ);
    }

    public int getSizeX() {
        return Math.abs(startX - endX);
    }

    public int getSizeZ() {
        return Math.abs(startZ - endZ);
    }

    public int calculateArea() {
        return getSizeX() * getSizeZ();
    }

    public int calculateVolume() {
        return calculateArea() * MAX_Y;
    }

    public boolean isInside(Location location) {
        return location.getX() >= this.startX && location.getX() <= this.endX && location.getZ() >= this.startZ && location.getZ() <= this.endZ;
    }

    public boolean isInside(Player player) {
        return isInside(player.getLocation());
    }

    public boolean isInside(User user) {
        return isInside(user.getPlayer());
    }
}
