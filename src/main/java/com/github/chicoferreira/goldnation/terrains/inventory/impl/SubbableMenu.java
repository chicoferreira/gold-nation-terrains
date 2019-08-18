package com.github.chicoferreira.goldnation.terrains.inventory.impl;

import com.github.chicoferreira.goldnation.terrains.inventory.Item;
import com.github.chicoferreira.goldnation.terrains.inventory.Items;
import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.inventory.action.impl.OpenMenuAction;

import java.util.Map;

public class SubbableMenu extends BasicMenu {

    private Map<Item, Menu> itemMenuMap;

    public SubbableMenu(String title, int size, Map<Item, Menu> itemMenuMap) {
        super(title, size);
        this.itemMenuMap = itemMenuMap;

        applyReturnItem();
    }

    private void applyReturnItem() {
        for (Map.Entry<Item, Menu> itemMenuEntry : itemMenuMap.entrySet()) {
            Item item = itemMenuEntry.getKey();
            Menu menu = itemMenuEntry.getValue();

            item.setAction(actionEvent -> {
                int returnSlot = menu.getSize() - 9;

                Item returnItem = new Item(returnSlot, Items.RETURN_ITEM, new OpenMenuAction(this));

                menu.addItem(returnItem);
                item.getAction().run(actionEvent);
            });
        }
    }

}
