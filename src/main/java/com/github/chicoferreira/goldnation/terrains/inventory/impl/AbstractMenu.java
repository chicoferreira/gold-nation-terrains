package com.github.chicoferreira.goldnation.terrains.inventory.impl;

import com.github.chicoferreira.goldnation.terrains.inventory.Item;
import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.inventory.action.Actions;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMenu implements Menu {

    private Map<Integer, Item> itemMap;

    protected AbstractMenu() {
        this.itemMap = new HashMap<>();
    }

    @Override
    public Item getItem(int slot) {
        return itemMap.get(slot);
    }

    @Override
    public List<Item> getItems() {
        return new ArrayList<>(itemMap.values());
    }

    @Override
    public void addItem(Item item) {
        itemMap.put(item.getSlot(), item);
    }

    @Override
    public void addItem(int slot, ItemStack itemStack) {
        itemMap.put(slot, new Item(slot, itemStack, Actions.NULL_ACTION));
    }

    @Override
    public void removeItem(Item item) {
        for (Map.Entry<Integer, Item> entry : itemMap.entrySet()) {
            if (entry.getValue().equals(item)) {
                itemMap.remove(entry.getKey());
            }
        }
    }

    @Override
    public void removeItem(int slot) {
        itemMap.remove(slot);
    }
}
