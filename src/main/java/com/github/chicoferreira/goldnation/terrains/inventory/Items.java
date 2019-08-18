package com.github.chicoferreira.goldnation.terrains.inventory;

import com.github.chicoferreira.goldnation.terrains.util.StackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Items {

    public static final ItemStack RETURN_ITEM = StackBuilder.newBuilder(Material.ARROW)
            .setDisplayName("§eVoltar")
            .create();

}
