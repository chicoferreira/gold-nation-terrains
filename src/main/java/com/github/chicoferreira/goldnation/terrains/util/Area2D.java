package com.github.chicoferreira.goldnation.terrains.util;

import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class Area2D implements Iterable<Position2D> {

    private static final int MAX_Y = 256;

    private int startX;
    private int endX;
    private int startZ;
    private int endZ;

    public Area2D(int startX, int endX, int startZ, int endZ) {
        this.startX = Math.max(startX, endX);
        this.endX = Math.min(startX, endX);
        this.startZ = Math.max(startZ, endZ);
        this.endZ = Math.min(startZ, endZ);
    }

    public Area2D(int blockX, int blockZ, int radius) {
        this(blockX + radius, blockX - radius, blockZ + radius, blockZ - radius);
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

    @Nonnull
    @Override
    public Iterator<Position2D> iterator() {
        return new AreaIterator(startX, startZ, endX, endZ);
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
