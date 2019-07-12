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

public class FriendRemoveCommand extends AbstractCommand {

    public FriendRemoveCommand(TerrainsPlugin plugin) {
        super(plugin, "removeramigo", "Remove um amigo do terreno.");
        setPermission("goldnation.terrains.removefriend");
        setParameters(Parameter.ofMandatory("amigo", VariableTypes.STRING));
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Location location = user.getPlayer().getLocation();

        Terrain terrain = getPlugin().getTerrainStorage().get(new Position2D(location.getBlockX(), location.getBlockZ()));
        Constants constants = getPlugin().getConstants();

        String friendName = (String) commandContexts.get("amigo").getValue();

        if (terrain != null) {
            if (terrain.getOwner().equals(user.getName())) {
                if (terrain.getTrustedUsers().contains(friendName)) {
                    terrain.getTrustedUsers().remove(friendName);
                    user.sendMessage(constants.commandFriendRemoved.replace("<friend>", friendName));
                } else {
                    user.sendMessage(constants.commandFriendNotFound.replace("<friend>", friendName));
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
