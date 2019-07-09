package com.github.chicoferreira.goldnation.terrains.command.commands;

import com.github.chicoferreira.goldnation.terrains.command.HelpAbstractCommand;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;

public class TerrainCommand extends HelpAbstractCommand {

    public TerrainCommand(TerrainsPlugin plugin) {
        super(plugin, "terrain", "Comando sobre terrenos.");
        setSubcommands(new BuyCommand(getPlugin()));
    }

}
