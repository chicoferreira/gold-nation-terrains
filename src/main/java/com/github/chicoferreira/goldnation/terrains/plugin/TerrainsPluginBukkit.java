package com.github.chicoferreira.goldnation.terrains.plugin;

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
}
