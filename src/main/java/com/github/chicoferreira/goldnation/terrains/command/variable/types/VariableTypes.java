package com.github.chicoferreira.goldnation.terrains.command.variable.types;

import com.github.chicoferreira.goldnation.terrains.command.variable.VariableType;

import java.math.BigDecimal;

public class VariableTypes {

    public static final VariableType<String> STRING = new StringVariableType();
    public static final VariableType<Integer> INTEGER = new IntVariableType();
    public static final VariableType<Boolean> BOOLEAN = new BooleanVariableType();
    public static final VariableType<BigDecimal> BIG_DECIMAL = new BigDecimalVariableType();

}
