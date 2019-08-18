package com.github.chicoferreira.goldnation.terrains.inventory;

import com.github.chicoferreira.goldnation.terrains.inventory.action.impl.OpenMenuAction;
import com.github.chicoferreira.goldnation.terrains.inventory.bridge.MenuBridge;
import com.github.chicoferreira.goldnation.terrains.inventory.bridge.impl.BukkitMenuBridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MenuBuilder {

    private MenuBridge menuBridge;
    private Map<Integer, Item> items;
    private List<Menu> subMenus;

    public MenuBuilder(MenuBridge menuBridge) {
        this.menuBridge = menuBridge;
        this.items = new HashMap<>();
        this.subMenus = new ArrayList<>();
    }

    public static MenuBuilder newBuilder() {
        return new MenuBuilder(new BukkitMenuBridge());
    }

    public MenuBuilder addSubMenu(Consumer<SubMenuBuilder> consumer) {
        SubMenuBuilder newBuilder = new SubMenuBuilder();
        consumer.accept(newBuilder);
        return addSubMenu(newBuilder.menu);
    }

    public MenuBuilder addSubMenu(Menu menu) {
        if (menu != null) {
            subMenus.add(menu);
        }
        return this;
    }

    public MenuBuilder addItem(Item item) {
        items.put(item.getSlot(), item);
        return this;
    }

    public Menu create() {
        return null;
    }

    private class SubMenuBuilder {

        private Menu menu;

        public MenuBuilder assignMenuToItem(Item item) {
            item.setAction(new OpenMenuAction(menu));
            return addItem(item);
        }
    }

}
