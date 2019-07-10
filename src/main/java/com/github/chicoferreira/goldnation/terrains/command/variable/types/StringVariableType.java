package com.github.chicoferreira.goldnation.terrains.command.variable.types;

import com.github.chicoferreira.goldnation.terrains.command.variable.VariableType;
import com.github.chicoferreira.goldnation.terrains.util.Result;

public class StringVariableType implements VariableType<String> {

    @Override
    public Result<String> parse(String string) {
        return Result.ofSuccess(string);
    }
}
