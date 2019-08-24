package com.github.chicoferreira.goldnation.terrains.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class AbandonCommand extends AbstractCommand {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");
    private Map<String, Long> map;

    public AbandonCommand(TerrainsPlugin plugin) {
        super(plugin, "abandonar", "Abandona o terreno recebendo metade.");
        setPermission("goldnation.terrains.abandon");
        this.map = new HashMap<>();
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Location location = user.getPlayer().getLocation();

        Terrain terrain = getPlugin().getTerrainStorage().get(new Position2D(location.getBlockX(), location.getBlockZ()));
        Constants constants = getPlugin().getConstants();

        if (terrain != null) {
            if (terrain.getOwner().equals(user.getName())) {
                if (isInCooldown(user.getName())) {
                    double moneyReceived = plugin.getTerrainController().abandon(user, terrain);
                    String format = DECIMAL_FORMAT.format(moneyReceived);

                    user.sendMessage(constants.commandAbandonSuccess.replace("<received>", format));
                } else {
                    putCooldown(user.getName());
                    user.sendMessage(constants.commandAbandonVerification);
                }
            } else {
                user.sendMessage(constants.commandNotOwner);
            }
        } else {
            user.sendMessage(constants.commandNotInTerrain);
        }
        return false;
    }

    private boolean isInCooldown(String user) {
        Long l = this.map.get(user);
        return l != null && System.currentTimeMillis() < l + getPlugin().getConstants().commandAbandonVerificationTime;
    }

    private void putCooldown(String user) {
        this.map.put(user, System.currentTimeMillis());
    }
}
