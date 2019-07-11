package com.github.chicoferreira.goldnation.terrains.command.commands;

import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.user.User;

public class DebugCommand extends AbstractCommand {

    public DebugCommand(TerrainsPlugin plugin) {
        super(plugin, "debug", "Debug.");
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        getPlugin().getTerrainStorage().getMap().forEach((position2D, terrain) ->
                user.sendMessage(position2D.toString() + " - " + terrain.getOwner())
        );
        return false;
    }
}
