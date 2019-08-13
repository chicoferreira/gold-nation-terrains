package com.github.chicoferreira.goldnation.terrains.command.variable.types;

import com.github.chicoferreira.goldnation.terrains.command.variable.VariableType;
import com.github.chicoferreira.goldnation.terrains.util.Result;

import java.math.BigDecimal;

public class BigDecimalVariableType implements VariableType<BigDecimal> {

    private static final String FAILURE_MESSAGE = "§cNão foi possível encontrar o decimal em: %s";

    @Override
    public Result<BigDecimal> parse(String string) {
        try {
            BigDecimal bigDecimal = new BigDecimal(string);
            return Result.ofSuccess(bigDecimal);
        } catch (NumberFormatException e) {
            return Result.ofFailure(user -> user.sendMessage(FAILURE_MESSAGE, string));
        }
    }

}
