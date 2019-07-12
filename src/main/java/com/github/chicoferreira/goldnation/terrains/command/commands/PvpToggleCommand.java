package com.github.chicoferreira.goldnation.terrains.command.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.types.VariableTypes;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

public class PvpToggleCommand extends AbstractCommand {

    public PvpToggleCommand(TerrainsPlugin plugin) {
        super(plugin, "pvp", "Troca o estado do pvp do terreno.");
        setParameters(Parameter.of("estado", VariableTypes.BOOLEAN));
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Location location = user.getPlayer().getLocation();

        Terrain terrain = getPlugin().getTerrainStorage().get(new Position2D(location.getBlockX(), location.getBlockZ()));
        Constants constants = getPlugin().getConstants();

        if (terrain != null) {
            if (terrain.getOwner().equals(user.getName())) {

                boolean state = (boolean) commandContexts.get("estado").getOrElse(!terrain.isPvpEnabled());
                terrain.setPvpEnabled(state);

                if (state) {
                    user.sendMessage(constants.commandTogglePvpEnabled);
                } else {
                    user.sendMessage(constants.commandTogglePvpDisabled);
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
