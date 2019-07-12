package com.github.chicoferreira.goldnation.terrains.command.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.NumberUtils;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

public class SetSpawnCommand extends AbstractCommand {

    public SetSpawnCommand(TerrainsPlugin plugin) {
        super(plugin, "setspawn", "Muda a localização do spawn do terreno.");
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Location location = user.getPlayer().getLocation();

        Terrain terrain = getPlugin().getTerrainStorage().get(new Position2D(location.getBlockX(), location.getBlockZ()));
        Constants constants = getPlugin().getConstants();

        if (terrain != null) {
            if (terrain.getOwner().equals(user.getName())) {
                terrain.setSpawnLocation(location);
                if (constants.commandSetSpawnSuccess != null) {
                    user.sendMessage(constants.commandSetSpawnSuccess
                            .replace("<x>", NumberUtils.formatNumber(location.getX()))
                            .replace("<y>", NumberUtils.formatNumber(location.getY()))
                            .replace("<z>", NumberUtils.formatNumber(location.getZ()))
                            .replace("<yaw>", NumberUtils.formatNumber(location.getYaw()))
                            .replace("<pitch>", NumberUtils.formatNumber(location.getPitch())));
                }
            } else {
                user.sendMessage(constants.commandNotOwner);
            }
        } else {
            user.sendMessage(constants.commandNotInTerrain);
        }
        return false;
    }
}
