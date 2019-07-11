package com.github.chicoferreira.goldnation.terrains;

import com.github.chicoferreira.goldnation.terrains.bank.Bank;
import com.github.chicoferreira.goldnation.terrains.command.commands.TerrainCommand;
import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutor;
import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutorImpl;
import com.github.chicoferreira.goldnation.terrains.command.recorder.BukkitCommandRecorder;
import com.github.chicoferreira.goldnation.terrains.command.recorder.CommandRecorder;
import com.github.chicoferreira.goldnation.terrains.database.credentials.DatabaseCredentials;
import com.github.chicoferreira.goldnation.terrains.database.mongo.MongoDatabaseProvider;
import com.github.chicoferreira.goldnation.terrains.database.provider.DatabaseProvider;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPluginBukkit;
import com.github.chicoferreira.goldnation.terrains.scheduler.BukkitScheduler;
import com.github.chicoferreira.goldnation.terrains.scheduler.Scheduler;
import com.github.chicoferreira.goldnation.terrains.terrain.TerrainStorage;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainController;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainControllerImpl;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.user.UserStorage;
import com.github.chicoferreira.goldnation.terrains.user.listener.UserListener;

public class Terrains extends TerrainsPluginBukkit {

    private Scheduler scheduler;
    private UserStorage userStorage;
    private CommandExecutor commandExecutor;
    private CommandRecorder commandRecorder;
    private Constants constants;

    private TerrainStorage terrainStorage;
    private TerrainController terrainController;

    private Bank bank;

    private DatabaseProvider databaseProvider;

    @Override
    public void enable() {
        this.scheduler = new BukkitScheduler(this);

        this.databaseProvider = new MongoDatabaseProvider();

        this.databaseProvider.connect(DatabaseCredentials.with("localhost", 27017, "admin", "admin", "admin"));

        // TODO: do this configurable

        this.userStorage = new UserStorage(this);
        registerListener(new UserListener(this));

        // TODO: add vault implementation

        this.bank = new Bank() {
            @Override
            public double get(User user) {
                return Double.MAX_VALUE;
            }

            @Override
            public boolean add(User user, double amount) {
                return true;
            }

            @Override
            public boolean remove(User user, double amount) {
                return true;
            }
        };

        this.terrainStorage = new TerrainStorage();
        this.terrainController = new TerrainControllerImpl(this);

        this.commandExecutor = new CommandExecutorImpl(this);
        this.commandRecorder = new BukkitCommandRecorder(this);
        this.constants = new Constants();

        registerCommand(new TerrainCommand(this));
    }

    @Override
    public void disable() {
        databaseProvider.close();
        // save all users and terrains
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
    public TerrainStorage getTerrainStorage() {
        return terrainStorage;
    }

    @Override
    public TerrainController getTerrainController() {
        return terrainController;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public DatabaseProvider getDatabaseProvider() {
        return databaseProvider;
    }
}
