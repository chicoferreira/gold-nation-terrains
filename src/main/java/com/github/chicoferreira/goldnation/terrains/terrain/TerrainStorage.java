package com.github.chicoferreira.goldnation.terrains.terrain;

import com.github.chicoferreira.goldnation.terrains.storage.AbstractAsyncMapStorage;
import com.github.chicoferreira.goldnation.terrains.user.User;
import org.apache.commons.lang.NotImplementedException;

import java.util.concurrent.Executor;

public class TerrainStorage extends AbstractAsyncMapStorage<Terrain, User> {

    public TerrainStorage(Executor executor) {
        super(executor);
    }

    @Override
    public void save(Terrain type) {
        throw new NotImplementedException();
    }
}
