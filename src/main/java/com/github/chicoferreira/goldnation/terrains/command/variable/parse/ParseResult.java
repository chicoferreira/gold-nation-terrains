package com.github.chicoferreira.goldnation.terrains.command.variable.parse;

import com.github.chicoferreira.goldnation.terrains.user.User;

import java.util.function.Consumer;

public interface ParseResult<T> {

    static <T> ParseResult<T> ofSuccess(T value) {
        return new ParseResult<T>() {
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

    static <T> ParseResult<T> ofFailure(Consumer<User> consumer) {
        return new ParseResult<T>() {
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
