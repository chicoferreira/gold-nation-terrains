package com.github.chicoferreira.goldnation.terrains.command.recorder;

import com.github.chicoferreira.goldnation.terrains.command.Command;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;

public class BukkitCommandRecorder implements CommandRecorder {

    private TerrainsPlugin plugin;

    public BukkitCommandRecorder(TerrainsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void register(Command command) {
        ((CraftServer) Bukkit.getServer()).getCommandMap().register(plugin.getName(), new org.bukkit.command.Command(command.getName()) {
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

}
