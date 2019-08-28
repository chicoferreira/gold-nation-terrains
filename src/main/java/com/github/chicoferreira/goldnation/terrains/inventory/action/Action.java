package com.github.chicoferreira.goldnation.terrains.inventory.action;

import com.github.chicoferreira.goldnation.terrains.inventory.action.event.ActionEvent;

import java.util.function.Consumer;

public interface Action extends Consumer<ActionEvent> {

    default Action andThen(Action after) {
        return actionEvent -> {
            accept(actionEvent);
            after.accept(actionEvent);
        };
    }
}