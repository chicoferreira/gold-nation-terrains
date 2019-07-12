package com.github.chicoferreira.goldnation.terrains.terrain.limit;

import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.entity.Player;

public class BukkitUserTerrainLimitProvider implements UserTerrainLimitProvider {

    private String permission;
    private String placeholder;

    public BukkitUserTerrainLimitProvider(String permission, String placeholder) {
        this.permission = permission;
        this.placeholder = placeholder;
    }

    @Override
    public int get(User user) {
        Player player = user.getPlayer();
        if (player.isOp()) {
            return 100;
        }
        for (int i = 0; i < 100; i++) {
            if (player.hasPermission(permission.replace(placeholder, Integer.toString(i)))) {
                return i;
            }
        }
        return 0;
    }

}
