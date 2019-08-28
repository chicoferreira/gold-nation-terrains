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
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class FriendAddCommand extends AbstractCommand {

    public FriendAddCommand(TerrainsPlugin plugin) {
        super(plugin, "addamigo", "Adiciona um amigo ao terreno.");
        setPermission("goldnation.terrains.addfriend");
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
                if (!friendName.equalsIgnoreCase(user.getName())) {
                    if (Bukkit.getPlayerExact(friendName) != null) {
                        if (!terrain.getTrustedUsers().contains(friendName)) {
                            List<String> commandFriendAddConfirmation = new ArrayList<>(constants.commandFriendAddConfirmation);
                            commandFriendAddConfirmation.replaceAll(s -> s
                                    .replace("<friend>", friendName)
                            );

                            Action action = actionEvent -> {
                                terrain.getTrustedUsers().add(friendName);
                                user.sendMessage(constants.commandFriendAdded.replace("<friend>", friendName));
                            };

                            Menu menu = new ConfirmationMenu(action, user, commandFriendAddConfirmation);
                            user.openMenu(menu, plugin.getMenuBridge());
                        } else {
                            user.sendMessage(constants.commandFriendAlreadyAdded.replace("<friend>", friendName));
                        }
                    } else {
                        user.sendMessage(constants.commandPlayerOffline);
                    }
                } else {
                    user.sendMessage(constants.commandFriendSelfAdd);
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
