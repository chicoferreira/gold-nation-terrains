package com.github.chicoferreira.goldnation.terrains.command.commands;

import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.types.VariableTypes;
import com.github.chicoferreira.goldnation.terrains.user.User;

public class BuyCommand extends AbstractCommand {

    public BuyCommand() {
        super("buy");
        setParameters(Parameter.ofMandatory("tamanho", VariableTypes.INTEGER));
    }

    @Override
    public boolean execute(User user, CommandContexts contexts) {
        int size = (int) contexts.get("tamanho").getValue();
        return true;
    }
}
