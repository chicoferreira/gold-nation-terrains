package com.github.chicoferreira.goldnation.terrains.util.itembuilder;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

public class WoolBuilder extends StackBuilder<WoolBuilder, ItemMeta, Wool> {

    WoolBuilder() {
        super(Material.WOOL);
    }

    @Override
    protected WoolBuilder builder() {
        return this;
    }

    @SuppressWarnings("deprecation")
    public WoolBuilder withColor(DyeColor color) {
        return withDurability(color.getData());
    }
}
