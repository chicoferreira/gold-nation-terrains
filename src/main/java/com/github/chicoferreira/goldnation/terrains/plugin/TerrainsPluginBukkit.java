package com.github.chicoferreira.goldnation.terrains.plugin;

import com.github.chicoferreira.goldnation.terrains.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class TerrainsPluginBukkit extends JavaPlugin implements TerrainsPlugin {

    @Override
    public final void onEnable() {
        enable();
    }

    @Override
    public final void onDisable() {
        disable();
    }

    @Override
    public void enable() {
    }

    @Override
    public void disable() {
    }

    public void registerCommand(Command command) {
        this.getCommandRecorder().register(command);
    }

    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
}
