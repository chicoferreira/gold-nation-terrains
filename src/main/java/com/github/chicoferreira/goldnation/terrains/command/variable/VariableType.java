package com.github.chicoferreira.goldnation.terrains.command.variable;

import com.github.chicoferreira.goldnation.terrains.util.Result;

public interface VariableType<T> {

    Result<T> parse(String string);

}
