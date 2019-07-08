package com.github.chicoferreira.goldnation.terrains.user;

import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class UserStorage {

    private TerrainsPlugin plugin;
    private Map<String, User> map;

    public UserStorage(TerrainsPlugin plugin) {
        this.plugin = plugin;
        this.map = new HashMap<>();
    }

    public CompletableFuture<User> get(String userName) {
        return runAsync(() -> {
            User user = map.get(userName);

            if (user == null) {
//                user = put(userDao.get(userName));
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

//    public CompletableFuture<Void> save(User user) {
//        return runAsync(() -> userDao.save(user));
//    }

    public <T> CompletableFuture<T> runAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, plugin.getScheduler().async());
    }

    public CompletableFuture<Void> runAsync(Runnable runnable) {
        return CompletableFuture.runAsync(runnable, plugin.getScheduler().async());
    }

}
