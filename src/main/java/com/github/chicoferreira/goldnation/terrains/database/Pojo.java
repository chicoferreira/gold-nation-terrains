package com.github.chicoferreira.goldnation.terrains.database;

public abstract class Pojo {

    private long creationDate;

    public Pojo() {
        super();
        this.creationDate = System.currentTimeMillis();
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }
}
