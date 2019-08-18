package com.github.chicoferreira.goldnation.terrains.inventory;

import com.github.chicoferreira.goldnation.terrains.inventory.action.Action;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Item {

    private int slot;
    private ItemStack itemStack;
    private Action action;

    public Item(int slot, ItemStack itemStack, Action action) {
        this.slot = slot;
        this.itemStack = itemStack;
        this.action = action;
    }

    public Item(Item item) {
        this(item.getSlot(), item.getItemStack().clone(), item.getAction());
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return slot == item.slot &&
                Objects.equals(itemStack, item.itemStack) &&
                Objects.equals(action, item.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slot, itemStack, action);
    }
}