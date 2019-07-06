package com.github.chicoferreira.goldnation.terrains.command;

import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;

public abstract class AbstractCommand implements Command {

    private Parameter[] parameters;

    public AbstractCommand(Parameter... parameters) {
        this.parameters = parameters;
    }

    @Override
    public final Parameter[] getParameters() {
        return parameters;
    }

}
