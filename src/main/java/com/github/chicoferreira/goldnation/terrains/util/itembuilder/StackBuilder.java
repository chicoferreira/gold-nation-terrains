package com.github.chicoferreira.goldnation.terrains.util.itembuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class StackBuilder<T extends StackBuilder, M extends ItemMeta, D extends MaterialData> {

    protected ItemStack itemStack;

    protected StackBuilder(Material material) {
        this(new ItemStack(material));
    }

    protected StackBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static ItemBuilder newBuilder() {
        return newBuilder(Material.AIR);
    }

    public static ItemBuilder newBuilder(Material material) {
        return new ItemBuilder(material);
    }

    public static WoolBuilder newWoolBuilder() {
        return new WoolBuilder();
    }


    public T withMaterial(Material material) {
        itemStack.setType(material);
        return builder();
    }

    public T withAmount(int amount) {
        itemStack.setAmount(amount);
        return builder();
    }

    public T withDurability(byte durability) {
        itemStack.setDurability(durability);
        return builder();
    }

    public T applyData(Consumer<D> consumer) {
        D data = (D) itemStack.getData();
        consumer.accept(data);
        itemStack.setData(data);
        return builder();
    }

    public T withDisplayName(String displayName) {
        applyItemMetaChanges(itemMeta -> itemMeta.setDisplayName(displayName));
        return builder();
    }

    public T withLore(String... lore) {
        return withLore(Arrays.asList(lore));
    }

    public T withLore(List<String> lore) {
        applyItemMetaChanges(m -> m.setLore(lore));
        return builder();
    }

    public T consume(Consumer<T> consumer) {
        consumer.accept(builder());
        return builder();
    }

    public ItemStack create() {
        return itemStack.clone();
    }

    protected abstract T builder();

    private void applyItemMetaChanges(Consumer<M> itemMetaConsumer) {
        M itemMeta = (M) itemStack.getItemMeta();
        itemMetaConsumer.accept(itemMeta);
        itemStack.setItemMeta(itemMeta);
    }

}
