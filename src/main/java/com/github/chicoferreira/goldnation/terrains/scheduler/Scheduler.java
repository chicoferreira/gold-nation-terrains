package com.github.chicoferreira.goldnation.terrains.scheduler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

public interface Scheduler {

    Executor sync();

    Executor async();

    default <T> CompletableFuture<T> makeAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, async());
    }

    default CompletableFuture<Void> makeAsync(Runnable runnable) {
        return CompletableFuture.runAsync(runnable, async());
    }

}
