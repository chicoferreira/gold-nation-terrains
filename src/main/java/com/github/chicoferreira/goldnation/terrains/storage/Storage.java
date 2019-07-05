package com.github.chicoferreira.goldnation.terrains.storage;

public interface Storage<T, G> {

    T get(G getter);

    void put(G getter, T type);

    void save(T type);

}
