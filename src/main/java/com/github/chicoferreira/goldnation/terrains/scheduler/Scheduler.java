package com.github.chicoferreira.goldnation.terrains.scheduler;

import java.util.concurrent.Executor;

public interface Scheduler {

    Executor sync();

    Executor async();

}
