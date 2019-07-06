package com.github.chicoferreira.goldnation.terrains.command.context;

import com.github.chicoferreira.goldnation.terrains.command.context.exception.ContextNotFoundException;

import java.util.List;

public class CommandContexts {

    private List<CommandContext> contexts;

    public CommandContexts(List<CommandContext> contexts) {
        this.contexts = contexts;
    }

    public CommandContext get(int index) {
        CommandContext commandContext = contexts.get(index);
        if (commandContext != null) {
            return commandContext;
        }
        throw new ContextNotFoundException("{index=" + index + "}");
    }

    public CommandContext get(String name) {
        for (CommandContext context : contexts) {
            if (context.getName().equals(name)) {
                return context;
            }
        }
        throw new ContextNotFoundException("{name=" + name + "}");
    }

}
