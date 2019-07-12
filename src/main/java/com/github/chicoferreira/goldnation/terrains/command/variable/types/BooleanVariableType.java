package com.github.chicoferreira.goldnation.terrains.command.variable.types;

import com.github.chicoferreira.goldnation.terrains.command.variable.VariableType;
import com.github.chicoferreira.goldnation.terrains.util.Result;

import java.util.Arrays;
import java.util.List;

public class BooleanVariableType implements VariableType<Boolean> {

    private static final String FAILURE_MESSAGE = "§cNão foi possível encontrar o booleano em: %s";

    private List<String> trueStrings;
    private List<String> falseStrings;

    public BooleanVariableType() {
        this.trueStrings = Arrays.asList("true", "sim", "verdadeiro", "ativar", "ativo");
        this.falseStrings = Arrays.asList("false", "não", "nao", "falso", "desativar", "desativo");
    }

    @Override
    public Result<Boolean> parse(String string) {
        if (trueStrings.contains(string.toLowerCase()))
            return Result.ofSuccess(true);
        if (falseStrings.contains(string.toLowerCase()))
            return Result.ofSuccess(false);
        return Result.ofFailure(user -> user.sendMessage(FAILURE_MESSAGE, string));
    }

}
