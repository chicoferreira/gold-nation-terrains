package com.github.chicoferreira.goldnation.terrains.command.variable;

import com.github.chicoferreira.goldnation.terrains.command.variable.parse.ParseResult;

public interface VariableType<T> {

    ParseResult<T> parse(String string);

}
