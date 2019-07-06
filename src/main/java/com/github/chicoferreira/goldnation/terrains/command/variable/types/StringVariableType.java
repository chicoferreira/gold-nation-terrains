package com.github.chicoferreira.goldnation.terrains.command.variable.types;

import com.github.chicoferreira.goldnation.terrains.command.variable.VariableType;
import com.github.chicoferreira.goldnation.terrains.command.variable.parse.ParseResult;

public class StringVariableType implements VariableType<String> {

    @Override
    public ParseResult<String> parse(String string) {
        return ParseResult.ofSuccess(string);
    }
}
