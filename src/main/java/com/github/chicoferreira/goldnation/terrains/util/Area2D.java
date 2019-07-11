package com.github.chicoferreira.goldnation.terrains.util;

import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Area2D implements Iterable<Position2D> {

    private static final int MAX_Y = 256;

    private int startX;
    private int endX;
    private int startZ;
    private int endZ;

    public Area2D(int startX, int endX, int startZ, int endZ) {
        this.startX = Math.min(startX, endX);
        this.endX = Math.max(startX, endX);
        this.startZ = Math.min(startZ, endZ);
        this.endZ = Math.max(startZ, endZ);
    }

    public Area2D(int blockX, int blockZ, int radius) {
        this(
                (int) (blockX + Math.ceil(radius / 2D)),
                blockX - Math.floorDiv(radius, 2) + 1,
                (int) (blockZ + Math.ceil(radius / 2D)),
                blockZ - Math.floorDiv(radius, 2) + 1);
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartZ() {
        return startZ;
    }

    public void setStartZ(int startZ) {
        this.startZ = startZ;
    }

    public int getEndZ() {
        return endZ;
    }

    public void setEndZ(int endZ) {
        this.endZ = endZ;
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

    public List<Position2D> getCorners() {
        List<Position2D> result = new ArrayList<>();

        int sizeZ = Math.abs(endZ - startZ);

        result.add(new Position2D(startX, startZ));
        result.add(new Position2D(startX, startZ + sizeZ));
        result.add(new Position2D(endX, endZ));
        result.add(new Position2D(endX, endZ - sizeZ));

        return result;
    }

    @Nonnull
    @Override
    public Iterator<Position2D> iterator() {
        return new AreaIterator(startX, startZ, endX, endZ);
    }

    public List<Position2D> getBorders() {
        List<Position2D> result = new ArrayList<>();

        for (Position2D position2D : this) {
            if (position2D.getX() == startX ||
                    position2D.getX() == endX ||
                    position2D.getZ() == startZ ||
                    position2D.getZ() == endZ) {
                result.add(position2D);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "Area2D{" +
                "startX=" + startX +
                ", endX=" + endX +
                ", startZ=" + startZ +
                ", endZ=" + endZ +
                '}';
    }

    public class AreaIterator implements Iterator<Position2D> {

        private int baseX, baseZ;
        private int x, z;
        private int sizeX, sizeZ;

        public AreaIterator(int x1, int z1, int x2, int z2) {
            this.baseX = x1;
            this.baseZ = z1;
            this.sizeX = Math.abs(x2 - x1) + 1;
            this.sizeZ = Math.abs(z2 - z1) + 1;
            this.x = this.z = 0;
        }

        @Override
        public boolean hasNext() {
            return this.x < this.sizeX && this.z < this.sizeZ;
        }

        @Override
        public Position2D next() {
            Position2D position2D = new Position2D(this.baseX + this.x, this.baseZ + this.z);
            if (++x >= this.sizeX) {
                this.x = 0;
                ++this.z;
            }
            return position2D;
        }
    }


}
