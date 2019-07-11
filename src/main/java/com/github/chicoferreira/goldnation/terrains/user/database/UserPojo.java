package com.github.chicoferreira.goldnation.terrains.user.database;

import com.github.chicoferreira.goldnation.terrains.database.Pojo;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.List;
import java.util.UUID;

@Entity(value = "users")
public class UserPojo extends Pojo {

    @Id
    @Indexed
    private String name;
    private List<UUID> terrainList;

    public UserPojo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getTerrainList() {
        return terrainList;
    }

    public void setTerrainList(List<UUID> terrainList) {
        this.terrainList = terrainList;
    }
}
