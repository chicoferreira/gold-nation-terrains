package com.github.chicoferreira.goldnation.terrains.user;

import com.github.chicoferreira.goldnation.terrains.database.Dao;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.database.UserMapper;
import com.github.chicoferreira.goldnation.terrains.user.database.UserPojo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class UserStorage {

    private TerrainsPlugin plugin;
    private Map<String, User> map;
    private Dao<User> userDao;

    public UserStorage(TerrainsPlugin plugin) {
        this.plugin = plugin;
        this.map = new HashMap<>();
        this.userDao = plugin.getDatabaseProvider().generateDao(UserPojo.class, new UserMapper());
    }

    public CompletableFuture<User> get(String userName) {
        return plugin.getScheduler().makeAsync(() -> {
            User user = map.get(userName);

            if (user == null) {
                user = put(userDao.getEntity(userName));
            }
            if (user == null) {
                user = put(new User(userName));
            }
            return user;
        });
    }

    public User put(User user) {
        if (user != null) {
            this.map.put(user.getName(), user);
        }
        return user;
    }

    public void save(User user) {
        plugin.getScheduler().makeAsync(() -> userDao.saveEntity(user));
    }

    public void addTerrain(String owner, Terrain terrain) {
        User user = get(owner).join();
        user.getTerrains().add(terrain.getUuid());
        this.save(user);
    }

    public void removeTerrain(String owner, Terrain terrain) {
        User user = get(owner).join();
        user.getTerrains().remove(terrain.getUuid());
        this.save(user);
    }

    public int saveAllLoaded() {
        Collection<User> values = this.map.values();
        for (User value : values) {
            userDao.saveEntity(value);
        }
        return values.size();
    }

}
