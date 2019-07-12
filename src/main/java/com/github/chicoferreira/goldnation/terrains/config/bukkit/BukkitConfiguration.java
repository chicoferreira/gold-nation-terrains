package com.github.chicoferreira.goldnation.terrains.config.bukkit;

import com.github.chicoferreira.goldnation.terrains.config.Configuration;
import com.google.common.base.Charsets;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.logging.Level;

public class BukkitConfiguration implements Configuration {

    private final String configName;
    private final JavaPlugin plugin;

    private FileConfiguration config;
    private File file;

    public BukkitConfiguration(JavaPlugin plugin, String configName) {
        this.configName = configName;
        this.plugin = plugin;

        createFiles();
    }

    public void reloadCustomConfig() {
        if (file == null) {
            file = new File(plugin.getDataFolder(), configName);
        }
        config = YamlConfiguration.loadConfiguration(file);

        Reader defConfigStream = new InputStreamReader(plugin.getResource(configName), Charsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        config.setDefaults(defConfig);
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            reloadCustomConfig();
        }
        return config;
    }

    public void createFiles() {
        if (file == null) {
            file = new File(plugin.getDataFolder(), configName);
        }
        if (!file.exists()) {
            plugin.saveResource(configName, false);
        }
    }

    @Override
    public void save() {
        if (config != null && file != null) {
            try {
                getConfig().save(file);
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Could not save configuration to " + file, ex);
            }
        }
    }

    @Override
    public Object get(String path) {
        return getConfig().get(path);
    }

    @Override
    public String getString(String path) {
        return getConfig().getString(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return getConfig().getStringList(path);
    }

    @Override
    public int getInt(String path) {
        return getConfig().getInt(path);
    }

    @Override
    public double getDouble(String path) {
        return getConfig().getDouble(path);
    }

    @Override
    public float getFloat(String path) {
        Object object = getConfig().get(path);
        if (object instanceof Float) {
            return ((Float) object);
        } else if (object instanceof String) {
            try {
                return Float.valueOf((String) object);
            } catch (Exception ignored) {
            }
        } else if (object instanceof Character) {
            return (float) (Character) object;
        } else if (object instanceof Number) {
            return ((Number) object).floatValue();
        }
        return Float.NaN;
    }

    @Override
    public long getLong(String path) {
        return getConfig().getLong(path);
    }

    @Override
    public void set(String path, Object value) {
        this.getConfig().set(path, value);
    }
}
