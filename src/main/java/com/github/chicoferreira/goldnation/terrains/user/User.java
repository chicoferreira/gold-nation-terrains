package com.github.chicoferreira.goldnation.terrains.user;

import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.inventory.bridge.MenuBridge;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.UUID;

public class User {

    private String name;
    private List<UUID> terrainList;

    private Menu openedMenu;

    private Player player;

    public User(String name) {
        this(name, Lists.newArrayList());
    }

    public User(String name, List<UUID> terrainList) {
        this.name = name;
        this.terrainList = terrainList;
    }

    public String getName() {
        return name;
    }

    public List<UUID> getTerrains() {
        return terrainList;
    }

    public Player getPlayer() {
        if (this.player == null) {
            updatePlayer();
        }
        return player;
    }

    public void updatePlayer() {
        this.player = Bukkit.getPlayerExact(name);
    }

    public void sendMessage(String message, Object... objects) {
        Player player = getPlayer();
        if (player != null && message != null) {
            player.sendMessage(String.format(message, objects));
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public Menu getOpenedMenu() {
        return openedMenu;
    }

    public void closeMenu() {
        getPlayer().closeInventory();
        removeMenu();
    }

    public void removeMenu() {
        this.openedMenu = null;
    }

    public void openMenu(Menu menu, MenuBridge<Inventory> bridge) {
        getPlayer().openInventory(bridge.create(menu));
        this.openedMenu = menu;
    }
}
