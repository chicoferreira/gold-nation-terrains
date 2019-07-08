package com.github.chicoferreira.goldnation.terrains.command.commands;

import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.user.User;

public class TerrainCommand extends AbstractCommand {

    public TerrainCommand() {
        super("terrain", "Comando sobre terrenos.");
        setSubcommands(new BuyCommand());
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        return false;
    }
}
