package com.github.chicoferreira.goldnation.terrains.inventory.defaults;

import com.github.chicoferreira.goldnation.terrains.inventory.Item;
import com.github.chicoferreira.goldnation.terrains.inventory.action.Action;
import com.github.chicoferreira.goldnation.terrains.inventory.action.Actions;
import com.github.chicoferreira.goldnation.terrains.inventory.impl.BasicMenu;
import com.github.chicoferreira.goldnation.terrains.inventory.util.Slot;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.itembuilder.StackBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationMenu extends BasicMenu {

    public ConfirmationMenu(Action acceptAction, User user, List<String> info) {
        super("Confirmação:", 27);

        init(acceptAction, user, info);
    }

    private void init(Action acceptAction, User user, List<String> info) {
        addItem(new Item(
                Slot.of(4, 2),
                StackBuilder.newWoolBuilder()
                        .withColor(DyeColor.LIME)
                        .withDisplayName("§aConfirmar")
                        .create(),
                acceptAction.andThen(actionEvent -> user.closeMenu())
        ));

        addItem(new Item(
                Slot.of(6, 2),
                StackBuilder.newWoolBuilder()
                        .withColor(DyeColor.RED)
                        .withDisplayName("§cCancelar")
                        .create(),
                actionEvent -> user.closeMenu()
        ));

        addItem(new Item(
                Slot.of(5, 2),
                StackBuilder.newBuilder(Material.SIGN)
                        .consume($ -> {
                            List<String> lore = new ArrayList<>();
                            for (int i = 0; i < info.size(); i++) {
                                String infoString = info.get(i);
                                if (i == 0) {
                                    $.withDisplayName(infoString);
                                } else {
                                    lore.add(infoString);
                                }
                            }
                            $.withLore(lore);
                        }).create(),
                Actions.NULL_ACTION
        ));
    }

}
