package com.github.chicoferreira.goldnation.terrains.inventory;

import com.github.chicoferreira.goldnation.terrains.inventory.impl.BasicMenu;

import java.util.HashMap;
import java.util.Map;

public class MenuBuilder {

    private String title;
    private int size;
    private Map<Integer, Item> items;

    public MenuBuilder(String title, int size) {
        this.title = title;
        this.size = size;
        this.items = new HashMap<>();
    }

    public static MenuBuilder newBuilder(String title, int size) {
        return new MenuBuilder(title, size);
    }

    public MenuBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MenuBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public MenuBuilder withItem(Item item) {
        items.put(item.getSlot(), item);
        return this;
    }

    public Menu create() {
        BasicMenu basicMenu = new BasicMenu(this.title, this.size);
        for (Item item : this.items.values()) {
            basicMenu.addItem(item);
        }
        return basicMenu;
    }
}
