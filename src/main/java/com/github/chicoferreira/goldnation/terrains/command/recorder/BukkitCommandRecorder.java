package com.github.chicoferreira.goldnation.terrains.command.recorder;

import com.github.chicoferreira.goldnation.terrains.command.Command;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

public class BukkitCommandRecorder implements CommandRecorder {

    private TerrainsPlugin plugin;

    public BukkitCommandRecorder(TerrainsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void register(Command command) {
        getCommandMap().register(plugin.getName(), new org.bukkit.command.Command(command.getName()) {
            @Override
            public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                if (sender instanceof Player) {
                    return plugin.getCommandExecutor().execute(command, (Player) sender, args);
                }
                sender.sendMessage(plugin.getConstants().commandNotAPlayer);
                return false;
            }
        });
    }

    private CommandMap getCommandMap() {
        CommandMap commandMap = null;

        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                f.setAccessible(true);

                commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            Bukkit.getLogger().warning("Couldn't get bukkit command map. Commands should not work.");
        }

        return commandMap;
    }

}
