package com.github.chicoferreira.goldnation.terrains;

import com.github.chicoferreira.goldnation.terrains.bank.Bank;
import com.github.chicoferreira.goldnation.terrains.bank.VaultBank;
import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutor;
import com.github.chicoferreira.goldnation.terrains.command.executor.CommandExecutorImpl;
import com.github.chicoferreira.goldnation.terrains.command.recorder.BukkitCommandRecorder;
import com.github.chicoferreira.goldnation.terrains.command.recorder.CommandRecorder;
import com.github.chicoferreira.goldnation.terrains.commands.TerrainCommand;
import com.github.chicoferreira.goldnation.terrains.config.Configuration;
import com.github.chicoferreira.goldnation.terrains.config.bukkit.BukkitConfiguration;
import com.github.chicoferreira.goldnation.terrains.database.credentials.DatabaseCredentials;
import com.github.chicoferreira.goldnation.terrains.database.mongo.MongoDatabaseProvider;
import com.github.chicoferreira.goldnation.terrains.database.provider.DatabaseProvider;
import com.github.chicoferreira.goldnation.terrains.inventory.bridge.MenuBridge;
import com.github.chicoferreira.goldnation.terrains.inventory.bridge.impl.BukkitMenuBridge;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPluginBukkit;
import com.github.chicoferreira.goldnation.terrains.scheduler.BukkitScheduler;
import com.github.chicoferreira.goldnation.terrains.scheduler.Scheduler;
import com.github.chicoferreira.goldnation.terrains.terrain.TerrainStorage;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainController;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainControllerImpl;
import com.github.chicoferreira.goldnation.terrains.terrain.limit.BukkitUserTerrainLimitProvider;
import com.github.chicoferreira.goldnation.terrains.terrain.limit.UserTerrainLimitProvider;
import com.github.chicoferreira.goldnation.terrains.terrain.listener.TerrainListener;
import com.github.chicoferreira.goldnation.terrains.user.UserStorage;
import com.github.chicoferreira.goldnation.terrains.user.listener.MenuListener;
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
    private UserTerrainLimitProvider userTerrainLimitProvider;

    @Override
    public void enable() {
        this.scheduler = new BukkitScheduler(this);

        this.databaseProvider = new MongoDatabaseProvider();

        Configuration configuration = new BukkitConfiguration(this, "config.yml");

        String host = configuration.getString("database.host");
        int port = configuration.getInt("database.port");
        String username = configuration.getString("database.username");
        String password = configuration.getString("database.password");
        String database = configuration.getString("database.database");
        this.databaseProvider.connect(DatabaseCredentials.with(host, port, username, password, database));

        this.userTerrainLimitProvider = new BukkitUserTerrainLimitProvider(configuration.getString("settings.permission"), "<amount>");

        this.userStorage = new UserStorage(this);
        registerListener(new UserListener(this));
        registerListener(new MenuListener(getUserStorage()));

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
        this.constants = new Constants(configuration);

        registerCommand(new TerrainCommand(this));
        registerListener(new TerrainListener(this));
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

    @Override
    public UserTerrainLimitProvider getUserTerrainLimitProvider() {
        return userTerrainLimitProvider;
    }

    @Override
    public MenuBridge getMenuBridge() {
        return new BukkitMenuBridge();
    }
}
