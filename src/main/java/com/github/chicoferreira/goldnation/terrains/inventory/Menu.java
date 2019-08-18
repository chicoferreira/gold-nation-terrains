package com.github.chicoferreira.goldnation.terrains.inventory;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Menu {

    String getTitle();

    int getSize();

    Item getItem(int slot);

    List<Item> getItems();

    void addItem(Item item);

    void addItem(int slot, ItemStack itemStack);

    void removeItem(Item item);

    void removeItem(int slot);

}
