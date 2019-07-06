package com.github.chicoferreira.goldnation.terrains.command.parameter;

import com.github.chicoferreira.goldnation.terrains.command.variable.VariableType;

public class Parameter<T> {

    private String name;
    private VariableType<T> type;
    private boolean mandatory;

    private Parameter(String name, VariableType<T> type, boolean mandatory) {
        this.name = name;
        this.type = type;
        this.mandatory = mandatory;
    }

    public static <T> Parameter<T> ofMandatory(String name, VariableType<T> type) {
        return new Parameter<>(name, type, true);
    }

    public static <T> Parameter<T> of(String name, VariableType<T> type) {
        return new Parameter<>(name, type, false);
    }

    public String getName() {
        return name;
    }

    public VariableType<T> getType() {
        return type;
    }

    public boolean isMandatory() {
        return mandatory;
    }
}
