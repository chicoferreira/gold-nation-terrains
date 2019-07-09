package com.github.chicoferreira.goldnation.terrains.command;

import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.user.User;

import java.util.List;

public interface Command {

    String getName();

    String getDescription();

    Parameter[] getParameters();

    boolean hasParent();

    Command getParent();

    void setParent(Command command);

    List<Command> getSubcommands();

    default Command getSubcommand(String name) {
        return getSubcommands().stream()
                .filter(subcommand -> subcommand.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    boolean execute(User user, CommandContexts commandContexts);

}
