package com.github.chicoferreira.goldnation.terrains.command.variable.types;

import com.github.chicoferreira.goldnation.terrains.command.variable.VariableType;
import com.github.chicoferreira.goldnation.terrains.util.Result;

public class IntVariableType implements VariableType<Integer> {

    private static final String FAILURE_MESSAGE = "§cNão foi possível encontrar o número em: %s";

    @Override
    public Result<Integer> parse(String string) {
        try {
            int integer = Integer.parseInt(string);
            return Result.ofSuccess(integer);
        } catch (NumberFormatException e) {
            return Result.ofFailure(user -> user.sendMessage(FAILURE_MESSAGE, string));
        }
    }

}
