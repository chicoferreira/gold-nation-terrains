package com.github.chicoferreira.goldnation.terrains.command.variable.types;

import com.github.chicoferreira.goldnation.terrains.command.variable.VariableType;
import com.github.chicoferreira.goldnation.terrains.command.variable.parse.ParseResult;

public class IntVariableType implements VariableType<Integer> {

    private static final String FAILURE_MESSAGE = "§cNão foi possível encontrar o número em: %s";

    @Override
    public ParseResult<Integer> parse(String string) {
        try {
            return ParseResult.ofSuccess(Integer.getInteger(string));
        } catch (NumberFormatException e) {
            return ParseResult.ofFailure(user -> user.sendMessage(FAILURE_MESSAGE, string));
        }
    }

}
