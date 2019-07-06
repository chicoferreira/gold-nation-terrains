package com.github.chicoferreira.goldnation.terrains.command.commands;

import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.types.VariableTypes;
import com.github.chicoferreira.goldnation.terrains.user.User;

public class BuyCommand extends AbstractCommand {

    public BuyCommand() {
        super(Parameter.ofMandatory("tamanho", VariableTypes.INTEGER));
    }

    @Override
    public void execute(User user, CommandContexts contexts) {
        int size = (int) contexts.get("tamanho").getValue();
    }
}
