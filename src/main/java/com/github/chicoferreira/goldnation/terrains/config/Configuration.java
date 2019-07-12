package com.github.chicoferreira.goldnation.terrains.config;

import java.util.List;

public interface Configuration {

    Object get(String path);

    String getString(String path);

    List<String> getStringList(String path);

    double getDouble(String path);

    int getInt(String path);

    float getFloat(String path);

    long getLong(String path);

    void set(String path, Object value);

    void save();

}