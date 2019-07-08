package com.github.chicoferreira.goldnation.terrains.command.executor;

import com.github.chicoferreira.goldnation.terrains.command.Command;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContext;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.parse.ParseResult;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandExecutorImpl implements CommandExecutor {

    private TerrainsPlugin plugin;

    @Override
    public boolean execute(Command command, Player player, String[] args) {
        User user = plugin.getUserStorage().get(player.getName());

        Command commandToExecute = command;

        int index = 0;
        while (index < args.length) {
            String arg = args[index];

            Command subcommand = commandToExecute.getSubcommand(arg);
            if (subcommand != null) {
                commandToExecute = subcommand;
            } else {
                break;
            }

            index++;
        }

        args = Arrays.copyOfRange(args, index, args.length);

        List<CommandContext> commandContexts = new ArrayList<>();

        if (args.length >= getMinimumArguments(commandToExecute)) {

            Parameter[] parameters = command.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];

                if (args.length >= i) {
                    String arg = args[i];

                    ParseResult parse = parameter.getType().parse(arg);
                    if (parse.wasSuccessful()) {
                        commandContexts.add(new CommandContext(parse.get(), parameter.getName()));
                    } else {
                        parse.runFallback(user);
                        return false;
                    }
                } else {
                    break;
                }
            }
        }

        return commandToExecute.execute(user, CommandContexts.with(commandContexts));
    }

    private int getMinimumArguments(Command command) {
        return (int) Arrays.stream(command.getParameters())
                .filter(Parameter::isMandatory)
                .count();
    }

}
