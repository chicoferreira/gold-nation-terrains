package com.github.chicoferreira.goldnation.terrains.command;

import com.github.chicoferreira.goldnation.terrains.command.def.DefaultHelpCommand;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCommand implements Command {

    protected final TerrainsPlugin plugin;

    private final String name;
    private final String description;
    private String permission;
    private Command parent;
    private List<Command> subcommands;
    private Parameter[] parameters;

    public AbstractCommand(TerrainsPlugin plugin, String name, String description) {
        this.plugin = plugin;
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
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public final Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter... parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean hasParent() {
        return getParent() != null;
    }

    @Override
    public Command getParent() {
        return parent;
    }

    @Override
    public void setParent(Command parent) {
        this.parent = parent;
    }

    @Override
    public final List<Command> getSubcommands() {
        return subcommands;
    }

    protected final void setSubcommands(List<Command> subcommands) {
        (this.subcommands = new ArrayList<>(Collections.singletonList(new DefaultHelpCommand(this, getPlugin())))).addAll(subcommands);
        this.subcommands.forEach(command -> command.setParent(this));
    }

    protected final void setSubcommands(Command... subcommands) {
        this.setSubcommands(Arrays.asList(subcommands));
    }

    public TerrainsPlugin getPlugin() {
        return plugin;
    }
}
