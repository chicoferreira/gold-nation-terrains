package com.github.chicoferreira.goldnation.terrains.database.mapper;

public interface Mapper<T, R> {

    T from(R r);

    R to(T t);

}
