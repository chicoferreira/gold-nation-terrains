package com.github.chicoferreira.goldnation.terrains.util;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

// TODO: test

public class WoolStackBuilder extends StackBuilder<ItemMeta, Wool> {
    public WoolStackBuilder() {
        super(Material.WOOL);
    }

    public WoolStackBuilder setColor(DyeColor color) {
        this.applyData(wool -> wool.setColor(color));
        return this;
    }
}
