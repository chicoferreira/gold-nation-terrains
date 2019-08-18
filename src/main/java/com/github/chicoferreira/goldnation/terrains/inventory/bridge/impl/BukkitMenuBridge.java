package com.github.chicoferreira.goldnation.terrains.inventory.bridge.impl;

import com.github.chicoferreira.goldnation.terrains.inventory.Item;
import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.inventory.bridge.MenuBridge;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BukkitMenuBridge implements MenuBridge<Inventory> {

    @Override
    public Inventory create(Menu menu) {
        Inventory inventory = Bukkit.createInventory(null, menu.getSize(), menu.getTitle());
        menu.getItems().forEach(item -> inventory.setItem(item.getSlot(), item.getItemStack()));

        return inventory;
    }

    @Override
    public void update(Inventory target, Menu menu) {
        ItemStack[] contents = target.getContents();
        for (int slot = 0; slot < contents.length; slot++) {
            ItemStack itemStack = contents[slot];

            Item menuItem = menu.getItem(slot);
            ItemStack menuItemStack = menuItem.getItemStack();

            if (areSame(itemStack, menuItemStack)) {
                return;
            }

            ItemStack newItemStack = menuItemStack;
            if (newItemStack == null) {
                newItemStack = new ItemStack(Material.AIR);
            }

            target.setItem(slot, newItemStack);
        }
    }

    private boolean areSame(ItemStack item1, ItemStack item2) {
        return ObjectUtils.equals(item1, item2);
    }
}
