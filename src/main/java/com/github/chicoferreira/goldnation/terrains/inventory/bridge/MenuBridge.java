package com.github.chicoferreira.goldnation.terrains.inventory.bridge;

import com.github.chicoferreira.goldnation.terrains.inventory.Menu;

public interface MenuBridge<T> {

    T create(Menu menu);

    void update(T target, Menu menu);

}
