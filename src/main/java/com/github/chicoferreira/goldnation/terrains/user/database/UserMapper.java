package com.github.chicoferreira.goldnation.terrains.user.database;

import com.github.chicoferreira.goldnation.terrains.database.mapper.Mapper;
import com.github.chicoferreira.goldnation.terrains.user.User;

import java.util.UUID;
import java.util.stream.Collectors;

public class UserMapper implements Mapper<User, UserPojo> {

    @Override
    public User from(UserPojo userPojo) {
        return new User(userPojo.getName(), userPojo.getTerrainList().stream()
                .map(UUID::fromString)
                .collect(Collectors.toList())
        );
    }

    @Override
    public UserPojo to(User user) {
        UserPojo userPojo = new UserPojo();

        userPojo.setName(user.getName());
        userPojo.setTerrainList(user.getTerrainList().stream()
                .map(UUID::toString)
                .collect(Collectors.toList())
        );

        return userPojo;
    }
}
