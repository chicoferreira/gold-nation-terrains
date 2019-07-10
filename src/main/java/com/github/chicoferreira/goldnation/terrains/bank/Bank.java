package com.github.chicoferreira.goldnation.terrains.bank;

import com.github.chicoferreira.goldnation.terrains.user.User;

public interface Bank {

    double get(User user);

    boolean add(User user, double amount);

    boolean remove(User user, double amount);

}
