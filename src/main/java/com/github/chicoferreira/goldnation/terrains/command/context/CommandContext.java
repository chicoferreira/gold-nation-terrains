package com.github.chicoferreira.goldnation.terrains.command.context;

public class CommandContext<T> {

    private String name;
    private T value;

    public CommandContext(T value, String name) {
        this.name = name;
        this.value = value;
    }

    public T getValue() {
        return getOrElse(null);
    }

    public T getOrElse(T fallbackValue) {
        return value != null ? value : fallbackValue;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
