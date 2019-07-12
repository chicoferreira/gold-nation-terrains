package com.github.chicoferreira.goldnation.terrains.command.commands;

import com.github.chicoferreira.goldnation.terrains.command.HelpAbstractCommand;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;

public class TerrainCommand extends HelpAbstractCommand {

    public TerrainCommand(TerrainsPlugin plugin) {
        super(plugin, "terreno", "Comando sobre terrenos.");
        setSubcommands(
                new BuyCommand(getPlugin()),
                new InfoCommand(getPlugin()),
                new AbandonCommand(getPlugin()),
                new ListTerrainCommand(getPlugin()),
                new GoCommand(getPlugin()),
                new PvpToggleCommand(getPlugin()),
                new FriendAddCommand(getPlugin()),
                new FriendRemoveCommand(getPlugin()),
                new SetSpawnCommand(getPlugin())
        );
    }

}
