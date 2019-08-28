package com.github.chicoferreira.goldnation.terrains.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.types.VariableTypes;
import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.inventory.action.Action;
import com.github.chicoferreira.goldnation.terrains.inventory.defaults.ConfirmationMenu;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

import java.util.List;

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
                    List<String> commandFriendRemoveConfirmation = constants.commandFriendRemoveConfirmation;
                    commandFriendRemoveConfirmation.replaceAll(s -> s
                            .replace("<friend>", friendName)
                    );

                    Action action = actionEvent -> {
                        terrain.getTrustedUsers().remove(friendName);
                        user.sendMessage(constants.commandFriendRemoved.replace("<friend>", friendName));
                    };

                    Menu menu = new ConfirmationMenu(action, user, commandFriendRemoveConfirmation);
                    user.openMenu(menu, plugin.getMenuBridge());
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
