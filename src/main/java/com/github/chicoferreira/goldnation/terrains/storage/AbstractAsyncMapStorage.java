package com.github.chicoferreira.goldnation.terrains.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public abstract class AbstractAsyncMapStorage<T, G> extends AbstractAsyncStorage<T, G> {

    private Map<G, T> map;

    public AbstractAsyncMapStorage(Executor executor) {
        super(executor);
        this.map = new HashMap<>();
    }

    @Override
    public T get(G getter) {
        return map.get(getter);
    }

    @Override
    public void put(G getter, T type) {
        map.put(getter, type);
    }
}
