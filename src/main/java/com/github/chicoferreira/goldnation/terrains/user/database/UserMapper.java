package com.github.chicoferreira.goldnation.terrains.user.database;

import com.github.chicoferreira.goldnation.terrains.database.mapper.Mapper;
import com.github.chicoferreira.goldnation.terrains.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserMapper implements Mapper<User, UserPojo> {

    @Override
    public User from(UserPojo userPojo) {
        List<UUID> terrainList = userPojo.getTerrainList() != null ?
                userPojo.getTerrainList().stream().map(UUID::fromString).collect(Collectors.toList())
                : new ArrayList<>();

        return new User(userPojo.getName(), terrainList);
    }

    @Override
    public UserPojo to(User user) {
        UserPojo userPojo = new UserPojo();

        userPojo.setName(user.getName());
        List<UUID> terrainList = user.getTerrains();
        if (terrainList != null) {
            userPojo.setTerrainList(terrainList.stream()
                    .map(UUID::toString)
                    .collect(Collectors.toList())
            );
        }

        return userPojo;
    }
}
