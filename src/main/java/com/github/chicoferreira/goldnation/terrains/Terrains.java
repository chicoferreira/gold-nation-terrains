package com.github.chicoferreira.goldnation.terrains;

import com.github.chicoferreira.goldnation.terrains.bank.Bank;
import com.github.chicoferreira.goldnation.terrains.bank.VaultBank;
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
import com.github.chicoferreira.goldnation.terrains.user.UserStorage;
import com.github.chicoferreira.goldnation.terrains.user.listener.UserListener;

import java.util.logging.Level;

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

        this.bank = new VaultBank();
        if (!this.bank.init()) {
            getLogger().warning("Não foi possível carregar o Vault corretamente. Isto poderá levar ao mau funcionamento do plugin.");
        }

        this.terrainStorage = new TerrainStorage(this);
        int loadedTerrains = this.terrainStorage.loadAll();
        getLogger().log(Level.INFO, "Carregado {0} terrenos.", loadedTerrains);

        this.terrainController = new TerrainControllerImpl(this);

        this.commandExecutor = new CommandExecutorImpl(this);
        this.commandRecorder = new BukkitCommandRecorder(this);
        this.constants = new Constants();

        registerCommand(new TerrainCommand(this));
    }

    @Override
    public void disable() {
        int savedTerrains = this.terrainStorage.saveAll();
        getLogger().log(Level.INFO, "Guardado {0} terrenos.", savedTerrains);
        int savedUsers = this.userStorage.saveAllLoaded();
        getLogger().log(Level.INFO, "Guardado {0} users carregados.", savedUsers);

        databaseProvider.close();
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
