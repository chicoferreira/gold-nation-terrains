package com.github.chicoferreira.goldnation.terrains.command.executor;

import com.github.chicoferreira.goldnation.terrains.command.Command;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContext;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.parse.ParseResult;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.google.common.collect.Iterables;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommandExecutorImpl implements CommandExecutor {

    private TerrainsPlugin plugin;

    public CommandExecutorImpl(TerrainsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(Command command, Player player, String[] args) {
        User user = plugin.getUserStorage().get(player.getName()).join();

        List<Command> commandList = new ArrayList<>(Collections.singletonList(command));

        int index = 0;
        while (index < args.length) {
            String arg = args[index];

            Command subcommand = command.getSubcommand(arg);
            if (subcommand != null) {
                commandList.add(command = subcommand);
            } else {
                break;
            }

            index++;
        }

        args = Arrays.copyOfRange(args, index, args.length);

        List<CommandContext> commandContexts = new ArrayList<>();

        Parameter[] parameters = command.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];

            if (args.length > i) {
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

        if (commandContexts.size() >= getMinimumArguments(command)) {
            return command.execute(user, CommandContexts.with(commandContexts));
        }
        user.sendMessage(plugin.getConstants().commandUsage.replace("<usage>", buildCommandUsage(commandList)));
        return false;
    }

    private String buildCommandUsage(List<Command> commands) {
        return "/" +
                commands.stream()
                        .map(Command::getName)
                        .collect(Collectors.joining(" "))
                + " " +
                Arrays.stream(Iterables.getLast(commands).getParameters())
                        .map(Parameter::stringValue)
                        .collect(Collectors.joining(" "));
    }

    private int getMinimumArguments(Command command) {
        return (int) Arrays.stream(command.getParameters())
                .filter(Parameter::isMandatory)
                .count();
    }

}
