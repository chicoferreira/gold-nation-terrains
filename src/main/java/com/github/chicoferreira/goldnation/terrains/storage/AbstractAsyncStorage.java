package com.github.chicoferreira.goldnation.terrains.storage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

public abstract class AbstractAsyncStorage<T, G> implements AsyncStorage<T, G> {

    private Executor executor;

    public AbstractAsyncStorage(Executor executor) {
        this.executor = executor;
    }

    @Override
    public CompletableFuture<T> getFuture(G getter) {
        return makeFuture(() -> get(getter));
    }

    protected <R> CompletableFuture<R> makeFuture(Supplier<R> callable) {
        return CompletableFuture.supplyAsync(callable, this.executor);
    }
}
