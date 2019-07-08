package com.github.chicoferreira.goldnation.terrains.plugin;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutor;
import com.github.chicoferreira.goldnation.terrains.command.recorder.CommandRecorder;
import com.github.chicoferreira.goldnation.terrains.scheduler.Scheduler;
import com.github.chicoferreira.goldnation.terrains.user.UserStorage;

public interface TerrainsPlugin {

    String getName();

    void enable();

    void disable();

    Scheduler getScheduler();

    UserStorage getUserStorage();

    CommandExecutor getCommandExecutor();

    CommandRecorder getCommandRecorder();

    Constants getConstants();

}
