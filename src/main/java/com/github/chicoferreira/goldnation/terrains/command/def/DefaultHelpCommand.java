package com.github.chicoferreira.goldnation.terrains.command.def;

import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.Command;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.user.User;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DefaultHelpCommand extends AbstractCommand {

    private Command parent;

    public DefaultHelpCommand(Command parent, TerrainsPlugin plugin) {
        super(plugin, plugin.getConstants().helpCommandName, "Ajuda sobre este comando.");
        this.parent = parent;
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        for (String s : getPlugin().getConstants().commandHelp) {
            if (s.equalsIgnoreCase("<commands>")) {
                String command = buildCommandSyntax(this.parent);

                user.sendMessage(getPlugin().getConstants().commandHelpSyntax
                        .replace("<command>", command + buildParameterSyntax(this.parent))
                        .replace("<description>", this.parent.getDescription()));
                for (Command subcommand : this.parent.getSubcommands()) {
                    user.sendMessage(getPlugin().getConstants().commandHelpSyntax
                            .replace("<command>", command + " " + subcommand.getName() + buildParameterSyntax(subcommand))
                            .replace("<description>", subcommand.getDescription()));
                }

            } else {
                user.sendMessage(s.replace("<commandName>", this.parent.getName().toUpperCase()));
            }
        }
        return true;
    }

    public String buildParameterSyntax(Command command) {
        return command.getParameters().length != 0 ? " " + Arrays.stream(command.getParameters())
                .map(Parameter::stringValue)
                .collect(Collectors.joining(" ")) : "";
    }

    public String buildCommandSyntax(Command command) {
        StringBuilder stringBuilder = new StringBuilder(command.getName());

        while (command.hasParent()) {
            command = command.getParent();
            stringBuilder.append(command.getName());
        }

        return stringBuilder.toString();
    }
}
