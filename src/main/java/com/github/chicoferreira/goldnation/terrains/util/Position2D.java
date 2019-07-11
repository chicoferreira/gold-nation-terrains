package com.github.chicoferreira.goldnation.terrains.util;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Position2D implements Comparable<Position2D> {

    private int x, z;

    public Position2D(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public int compareTo(@Nonnull Position2D position2D) {
        int xComparison = Integer.compare(this.getX(), position2D.getX());
        int zComparison = Integer.compare(this.getZ(), position2D.getZ());

        return xComparison != 0 ? xComparison : zComparison;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position2D) {
            Position2D position2D = (Position2D) obj;
            return position2D.getX() == this.getX() && position2D.getZ() == this.getZ();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", z=" + z +
                '}';
    }
}
