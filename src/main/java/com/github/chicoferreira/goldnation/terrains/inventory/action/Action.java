package com.github.chicoferreira.goldnation.terrains.inventory.action;

import com.github.chicoferreira.goldnation.terrains.inventory.action.event.ActionEvent;

import java.util.function.Consumer;

public interface Action extends Consumer<ActionEvent> {
}