package com.github.chicoferreira.goldnation.terrains.plugin;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.bank.Bank;
import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutor;
import com.github.chicoferreira.goldnation.terrains.command.recorder.CommandRecorder;
import com.github.chicoferreira.goldnation.terrains.database.provider.DatabaseProvider;
import com.github.chicoferreira.goldnation.terrains.scheduler.Scheduler;
import com.github.chicoferreira.goldnation.terrains.terrain.TerrainStorage;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainController;
import com.github.chicoferreira.goldnation.terrains.terrain.limit.UserTerrainLimitProvider;
import com.github.chicoferreira.goldnation.terrains.user.UserStorage;

import java.util.logging.Logger;

public interface TerrainsPlugin {

    String getName();

    void enable();

    void disable();

    Scheduler getScheduler();

    UserStorage getUserStorage();

    CommandExecutor getCommandExecutor();

    CommandRecorder getCommandRecorder();

    Constants getConstants();

    Logger getLogger();

    TerrainStorage getTerrainStorage();

    TerrainController getTerrainController();

    Bank getBank();

    DatabaseProvider getDatabaseProvider();

    UserTerrainLimitProvider getUserTerrainLimitProvider();
}
