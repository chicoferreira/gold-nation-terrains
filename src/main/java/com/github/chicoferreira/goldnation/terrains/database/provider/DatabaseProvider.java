package com.github.chicoferreira.goldnation.terrains.database.provider;

import com.github.chicoferreira.goldnation.terrains.database.Dao;
import com.github.chicoferreira.goldnation.terrains.database.credentials.DatabaseCredentials;
import com.github.chicoferreira.goldnation.terrains.database.mapper.Mapper;

import javax.annotation.Nonnull;

public interface DatabaseProvider {

    void connect(DatabaseCredentials databaseCredentials);

    void close();

    @Nonnull
    <T, R> Dao<T> generateDao(Class<R> rClass, Mapper<T, R> mapper);

}
