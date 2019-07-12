package com.github.chicoferreira.goldnation.terrains.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.Executor;

public class BukkitScheduler extends AsyncScheduler {

    private Plugin plugin;

    public BukkitScheduler(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Executor sync() {
        return task -> Bukkit.getScheduler().runTask(plugin, task);
    }

}
