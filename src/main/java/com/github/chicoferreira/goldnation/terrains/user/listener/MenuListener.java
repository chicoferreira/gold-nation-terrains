package com.github.chicoferreira.goldnation.terrains.user.listener;

import com.github.chicoferreira.goldnation.terrains.inventory.Item;
import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.inventory.action.event.ActionEvent;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.user.UserStorage;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuListener implements Listener {

    private UserStorage userStorage;

    public MenuListener(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        // TODO: DEBUG
        Inventory inventory = event.getView().getTopInventory();
        if (inventory == null) return;

        ItemStack currentItem = event.getCurrentItem();
        if (currentItem == null) return;

        HumanEntity whoClicked = event.getWhoClicked();
        User user = userStorage.get(whoClicked.getName()).join();

        Menu menu = user.getOpenedMenu();
        if (menu == null || !menu.getTitle().equals(inventory.getTitle())) return;

        Item item = menu.getItem(event.getSlot());

        if (item == null) {
            throw new IllegalStateException("Item is null on menu but exists on inventory.");
        }

        ActionEvent actionEvent = new ActionEvent(user, menu, item);
        item.getAction().accept(actionEvent);
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event) {
        HumanEntity player = event.getPlayer();

        User user = userStorage.get(player.getName()).join();
        user.removeMenu();
    }
}
