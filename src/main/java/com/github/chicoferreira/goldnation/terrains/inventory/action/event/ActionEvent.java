package com.github.chicoferreira.goldnation.terrains.inventory.action.event;

import com.github.chicoferreira.goldnation.terrains.inventory.Item;
import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.user.User;

public class ActionEvent {

    private User user;
    private Menu menu;
    private Item item;

    public ActionEvent(User user, Menu menu, Item item) {
        this.user = user;
        this.menu = menu;
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public Menu getMenu() {
        return menu;
    }

    public Item getItem() {
        return item;
    }
}
