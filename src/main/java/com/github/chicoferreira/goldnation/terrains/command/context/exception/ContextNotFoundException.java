package com.github.chicoferreira.goldnation.terrains.command.context.exception;

public class ContextNotFoundException extends RuntimeException {

    public ContextNotFoundException(String contextString) {
        super("Could find context: " + contextString);
    }
}
