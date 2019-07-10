package com.github.chicoferreira.goldnation.terrains;

import com.github.chicoferreira.goldnation.terrains.command.commands.TerrainCommand;
import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutor;
import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutorImpl;
import com.github.chicoferreira.goldnation.terrains.command.recorder.BukkitCommandRecorder;
import com.github.chicoferreira.goldnation.terrains.command.recorder.CommandRecorder;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPluginBukkit;
import com.github.chicoferreira.goldnation.terrains.scheduler.BukkitScheduler;
import com.github.chicoferreira.goldnation.terrains.scheduler.Scheduler;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainController;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainControllerImpl;
import com.github.chicoferreira.goldnation.terrains.user.UserStorage;
import com.github.chicoferreira.goldnation.terrains.user.listener.UserListener;

public class Terrains extends TerrainsPluginBukkit {

    private Scheduler scheduler;
    private UserStorage userStorage;
    private CommandExecutor commandExecutor;
    private CommandRecorder commandRecorder;
    private Constants constants;

    private TerrainController terrainController;

    @Override
    public void enable() {
        this.scheduler = new BukkitScheduler(this);

        this.userStorage = new UserStorage(this);
        registerListener(new UserListener(this));

        this.terrainController = new TerrainControllerImpl();

        this.commandExecutor = new CommandExecutorImpl(this);
        this.commandRecorder = new BukkitCommandRecorder(this);
        this.constants = new Constants();

        registerCommand(new TerrainCommand(this));
    }

    @Override
    public void disable() {
    }

    @Override
    public UserStorage getUserStorage() {
        return userStorage;
    }

    @Override
    public Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    @Override
    public CommandRecorder getCommandRecorder() {
        return commandRecorder;
    }

    @Override
    public Constants getConstants() {
        return constants;
    }

    @Override
    public TerrainController getTerrainController() {
        return terrainController;
    }
}
