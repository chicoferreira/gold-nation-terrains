package com.github.chicoferreira.goldnation.terrains.user;

import com.github.chicoferreira.goldnation.terrains.storage.AbstractAsyncMapStorage;
import org.apache.commons.lang.NotImplementedException;

import java.util.concurrent.Executor;

public class UserStorage extends AbstractAsyncMapStorage<User, String> {

    public UserStorage(Executor executor) {
        super(executor);
    }

    @Override
    public void save(User type) {
        throw new NotImplementedException();
    }

}
