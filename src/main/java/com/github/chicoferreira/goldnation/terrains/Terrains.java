package com.github.chicoferreira.goldnation.terrains;

import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutor;
import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutorImpl;
import com.github.chicoferreira.goldnation.terrains.command.recorder.BukkitCommandRecorder;
import com.github.chicoferreira.goldnation.terrains.command.recorder.CommandRecorder;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPluginBukkit;
import com.github.chicoferreira.goldnation.terrains.scheduler.BukkitScheduler;
import com.github.chicoferreira.goldnation.terrains.scheduler.Scheduler;
import com.github.chicoferreira.goldnation.terrains.user.UserStorage;

public class Terrains extends TerrainsPluginBukkit {

    private Scheduler scheduler;
    private UserStorage userStorage;
    private CommandExecutor commandExecutor;
    private CommandRecorder commandRecorder;
    private Constants constants;

    @Override
    public void enable() {
        this.scheduler = new BukkitScheduler(this);

        this.userStorage = new UserStorage(this.getScheduler().async());
        this.commandExecutor = new CommandExecutorImpl();
        this.commandRecorder = new BukkitCommandRecorder(this);
        this.constants = new Constants();
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
}
