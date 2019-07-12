package com.github.chicoferreira.goldnation.terrains.terrain.database;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Area2DPojo {

    private int startX;
    private int endX;
    private int startZ;
    private int endZ;

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
}
