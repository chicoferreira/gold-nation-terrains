package com.github.chicoferreira.goldnation.terrains.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.function.Consumer;

public class StackBuilder<M extends ItemMeta, D extends MaterialData> {

    private ItemStack itemStack;

    public StackBuilder(Material material) {
        this(new ItemStack(material));
    }

    public StackBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static StackBuilder newBuilder() {
        return newBuilder(Material.AIR);
    }

    public static StackBuilder newBuilder(Material material) {
        return new StackBuilder(material);
    }


    public StackBuilder setMaterial(Material material) {
        itemStack.setType(material);
        return this;
    }

    public StackBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public StackBuilder setDurability(byte durability) {
        applyItemMetaChanges(itemMeta -> {
            if (itemMeta instanceof Damageable) {
                Damageable damageable = (Damageable) itemMeta;
                damageable.setDamage(durability);
            }
        });
        return this;
    }

    public StackBuilder applyData(Consumer<D> consumer) {
        consumer.accept((D) itemStack.getData());
        return this;
    }

    public StackBuilder setDisplayName(String displayName) {
        applyItemMetaChanges(itemMeta -> itemMeta.setDisplayName(displayName));
        return this;
    }

    public ItemStack create() {
        return itemStack.clone();
    }

    private void applyItemMetaChanges(Consumer<M> itemMetaConsumer) {
        M itemMeta = (M) itemStack.getItemMeta();
        itemMetaConsumer.accept(itemMeta);
        itemStack.setItemMeta(itemMeta);
    }
}
