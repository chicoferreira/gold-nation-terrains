package com.github.chicoferreira.goldnation.terrains.scheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class AsyncScheduler implements Scheduler {

    private Executor async;

    public AsyncScheduler() {
        this.async = Executors.newCachedThreadPool();
    }

    @Override
    public Executor async() {
        return async;
    }
}
