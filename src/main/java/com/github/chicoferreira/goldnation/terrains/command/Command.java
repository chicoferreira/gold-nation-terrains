package com.github.chicoferreira.goldnation.terrains.command;

import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.user.User;

public interface Command {

    Parameter[] getParameters();

    void execute(User user, CommandContexts commandContexts);

}
