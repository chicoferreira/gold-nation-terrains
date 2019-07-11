package com.github.chicoferreira.goldnation.terrains.user.listener;

import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    public void onJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        try {
            User user = plugin.getUserStorage().get(player.getName()).get(200, TimeUnit.MILLISECONDS);
            user.updatePlayer();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, plugin.getConstants().playerJoinTimeout);
            plugin.getLogger().severe("Couldn't grab '" + player.getName() + "' terrain user info:");
        }
    }
}
