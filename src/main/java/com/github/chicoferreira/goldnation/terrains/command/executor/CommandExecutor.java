package com.github.chicoferreira.goldnation.terrains.command.executor;

import com.github.chicoferreira.goldnation.terrains.command.Command;
import org.bukkit.entity.Player;

public interface CommandExecutor {

    boolean execute(Command command, Player player, String[] args);

}
