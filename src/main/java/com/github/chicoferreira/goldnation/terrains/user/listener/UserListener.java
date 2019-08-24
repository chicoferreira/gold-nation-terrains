package com.github.chicoferreira.goldnation.terrains.user.listener;

import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.concurrent.TimeUnit;

public class UserListener implements Listener {

    private TerrainsPlugin plugin;

    public UserListener(TerrainsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onConnect(AsyncPlayerPreLoginEvent event) {
        plugin.getUserStorage().get(event.getName()).join();
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent event) throws Exception {
        Player player = event.getPlayer();
        try {
            User user = plugin.getUserStorage().get(player.getName()).get(plugin.getConstants().joinTimeoutTime, TimeUnit.MILLISECONDS);
            user.updatePlayer();
        } catch (Exception e) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, plugin.getConstants().playerJoinTimeout);
            plugin.getLogger().severe("Couldn't grab '" + player.getName() + "' terrain user info:");
            throw e;
        }
    }
}
