package com.github.chicoferreira.goldnation.terrains.command;

import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractCommand implements Command {

    private final String name;
    private final String description;
    private List<Command> subcommands;
    private Parameter[] parameters;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
        this.parameters = new Parameter[0];
        this.subcommands = new ArrayList<>();
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public final Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter... parameters) {
        this.parameters = parameters;
    }

    @Override
    public final List<Command> getSubcommands() {
        return subcommands;
    }

    public void setSubcommands(List<Command> subcommands) {
        (this.subcommands = new ArrayList<>()).addAll(subcommands);
    }

    public void setSubcommands(Command... subcommands) {
        this.setSubcommands(Arrays.asList(subcommands));
    }

}
