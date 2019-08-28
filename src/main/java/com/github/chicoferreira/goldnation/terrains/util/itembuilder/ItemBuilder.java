package com.github.chicoferreira.goldnation.terrains.util.itembuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class ItemBuilder extends StackBuilder<ItemBuilder, ItemMeta, MaterialData> {

    ItemBuilder(Material material) {
        super(material);
    }

    ItemBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    @Override
    protected ItemBuilder builder() {
        return this;
    }

}
