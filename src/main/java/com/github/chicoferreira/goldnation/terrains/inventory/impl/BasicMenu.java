package com.github.chicoferreira.goldnation.terrains.inventory.impl;

public class BasicMenu extends AbstractMenu {

    private String title;
    private int size;

    public BasicMenu(String title, int size) {
        this.title = title;
        this.size = size;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getSize() {
        return size;
    }
}
