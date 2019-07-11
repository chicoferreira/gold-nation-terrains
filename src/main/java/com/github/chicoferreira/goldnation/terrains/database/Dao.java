package com.github.chicoferreira.goldnation.terrains.database;

import java.util.List;

public interface Dao<T> {

    T getEntity(String name);

    void saveEntity(T t);

    List<T> getAll();

}
