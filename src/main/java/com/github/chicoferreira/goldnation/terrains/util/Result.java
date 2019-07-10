package com.github.chicoferreira.goldnation.terrains.util;

import com.github.chicoferreira.goldnation.terrains.user.User;
import org.apache.commons.lang.Validate;

import java.util.function.Consumer;

public interface Result<T> {

    static <T> Result<T> ofSuccess(T value) {
        Validate.notNull(value, "Success parse result cannot be null");
        return new Result<T>() {
            @Override
            public T get() {
                return value;
            }

            @Override
            public boolean wasSuccessful() {
                return true;
            }

            @Override
            public void runFallback(User user) {

            }
        };
    }

    static <T> Result<T> ofFailure(Consumer<User> consumer) {
        return new Result<T>() {
            @Override
            public T get() {
                throw new NullPointerException();
            }

            @Override
            public boolean wasSuccessful() {
                return false;
            }

            @Override
            public void runFallback(User user) {
                consumer.accept(user);
            }
        };
    }

    T get();

    boolean wasSuccessful();

    void runFallback(User user);

}
