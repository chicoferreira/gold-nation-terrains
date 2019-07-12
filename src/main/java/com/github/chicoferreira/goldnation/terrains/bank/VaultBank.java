package com.github.chicoferreira.goldnation.terrains.bank;

import com.github.chicoferreira.goldnation.terrains.user.User;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultBank implements Bank {

    private Economy vaultService;

    @Override
    public boolean init() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            vaultService = rsp.getProvider();
            return vaultService != null;
        }
        return false;
    }

    @Override
    public double get(User user) {
        return vaultService.bankBalance(user.getName()).balance;
    }

    @Override
    public boolean add(User user, double amount) {
        EconomyResponse economyResponse = vaultService.bankDeposit(user.getName(), amount);
        return economyResponse.transactionSuccess();
    }

    @Override
    public boolean remove(User user, double amount) {
        EconomyResponse economyResponse = vaultService.bankWithdraw(user.getName(), amount);
        return economyResponse.transactionSuccess();
    }
}
