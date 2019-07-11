package com.github.chicoferreira.goldnation.terrains.user.database;

import com.github.chicoferreira.goldnation.terrains.database.mapper.Mapper;
import com.github.chicoferreira.goldnation.terrains.user.User;

public class UserMapper implements Mapper<User, UserPojo> {

    @Override
    public User from(UserPojo userPojo) {
        return new User(userPojo.getName(), userPojo.getTerrainList());
    }

    @Override
    public UserPojo to(User user) {
        UserPojo userPojo = new UserPojo();

        userPojo.setName(user.getName());
        userPojo.setTerrainList(user.getTerrainList());

        return userPojo;
    }
}
