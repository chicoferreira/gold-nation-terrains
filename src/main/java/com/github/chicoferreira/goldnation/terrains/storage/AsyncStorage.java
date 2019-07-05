package com.github.chicoferreira.goldnation.terrains.storage;

import java.util.concurrent.CompletableFuture;

public interface AsyncStorage<T, G> extends Storage<T, G> {

    CompletableFuture<T> getFuture(G getter);

}
