package com.github.chicoferreira.goldnation.terrains.inventory.defaults;

import com.github.chicoferreira.goldnation.terrains.inventory.Item;
import com.github.chicoferreira.goldnation.terrains.inventory.action.Action;
import com.github.chicoferreira.goldnation.terrains.inventory.action.Actions;
import com.github.chicoferreira.goldnation.terrains.inventory.impl.BasicMenu;
import com.github.chicoferreira.goldnation.terrains.inventory.util.Slot;
import com.github.chicoferreira.goldnation.terrains.util.StackBuilder;
import org.bukkit.Material;

import java.util.function.Consumer;

public class ConfirmationMenu extends BasicMenu {

    private Consumer<Action> acceptAction;
    private Consumer<Action> declineAction;

    public ConfirmationMenu() {
        super("Confirmação:", 27);
        addItem(new Item(Slot.of(4, 2), StackBuilder.newBuilder(Material.WOOL).create(), Actions.NULL_ACTION));
    }

}
