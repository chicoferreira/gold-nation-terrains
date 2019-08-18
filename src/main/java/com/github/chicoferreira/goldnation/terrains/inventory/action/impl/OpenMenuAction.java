package com.github.chicoferreira.goldnation.terrains.inventory.action.impl;

import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.inventory.action.Action;
import com.github.chicoferreira.goldnation.terrains.inventory.action.event.ActionEvent;
import com.github.chicoferreira.goldnation.terrains.user.User;

public class OpenMenuAction implements Action {

    private Menu menu;

    public OpenMenuAction(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void run(ActionEvent actionEvent) {
        User user = actionEvent.getUser();
        user.openMenu(menu);
    }
}
