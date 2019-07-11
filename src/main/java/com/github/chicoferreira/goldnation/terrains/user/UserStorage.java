package com.github.chicoferreira.goldnation.terrains.user;

import com.github.chicoferreira.goldnation.terrains.database.Dao;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.user.database.UserMapper;
import com.github.chicoferreira.goldnation.terrains.user.database.UserPojo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

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
        return runAsync(() -> {
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
        runAsync(() -> userDao.saveEntity(user));
    }

    public <T> CompletableFuture<T> runAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, plugin.getScheduler().async());
    }

    public void runAsync(Runnable runnable) {
        CompletableFuture.runAsync(runnable, plugin.getScheduler().async());
    }

}
