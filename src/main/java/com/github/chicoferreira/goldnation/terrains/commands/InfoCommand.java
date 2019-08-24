package com.github.chicoferreira.goldnation.terrains.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

import java.util.List;

public class InfoCommand extends AbstractCommand {

    public InfoCommand(TerrainsPlugin plugin) {
        super(plugin, "info", "Mostra detalhes do terreno que você está.");
        setPermission("goldnation.terrains.info");
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Location location = user.getPlayer().getLocation();

        Terrain terrain = getPlugin().getTerrainStorage().get(new Position2D(location.getBlockX(), location.getBlockZ()));
        Constants constants = getPlugin().getConstants();

        if (terrain != null) {
            List<String> trustedUsers = terrain.getTrustedUsers();
            for (String message : constants.commandInfo) {
                user.sendMessage(message
                        .replace("<owner>", terrain.getOwner())
                        .replace("<size>", Integer.toString(terrain.getSize()))
                        .replace("<areaStartX>", Integer.toString(terrain.getArea().getStartX()))
                        .replace("<areaStartZ>", Integer.toString(terrain.getArea().getStartZ()))
                        .replace("<areaEndX>", Integer.toString(terrain.getArea().getEndX()))
                        .replace("<areaEndZ>", Integer.toString(terrain.getArea().getEndZ()))
                        .replace("<spawnX>", Integer.toString(terrain.getSpawnLocation().getBlockX()))
                        .replace("<spawnY>", Integer.toString(terrain.getSpawnLocation().getBlockY()))
                        .replace("<spawnZ>", Integer.toString(terrain.getSpawnLocation().getBlockZ()))
                        .replace("<spawnYaw>", Float.toString(terrain.getSpawnLocation().getYaw()))
                        .replace("<spawnPitch>", Float.toString(terrain.getSpawnLocation().getPitch()))
                        .replace("<translatedPvpState>", terrain.isPvpEnabled() ? constants.activated : constants.disactivated)
                        .replace("<friends>", trustedUsers.isEmpty() ?
                                constants.emptyString : String.join(", ", trustedUsers))
                );
            }
        } else {
            user.sendMessage(constants.commandNotInTerrain);
        }
        return false;
    }
}
