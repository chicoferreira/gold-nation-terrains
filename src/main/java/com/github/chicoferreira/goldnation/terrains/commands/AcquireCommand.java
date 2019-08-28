package com.github.chicoferreira.goldnation.terrains.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.inventory.action.Action;
import com.github.chicoferreira.goldnation.terrains.inventory.defaults.ConfirmationMenu;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AcquireCommand extends AbstractCommand {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");

    public AcquireCommand(TerrainsPlugin plugin) {
        super(plugin, "adquirir", "Adquira um terreno à venda.");
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Location location = user.getPlayer().getLocation();

        Position2D position2D = new Position2D(location.getBlockX(), location.getBlockZ());
        Terrain terrain = getPlugin().getTerrainStorage().get(position2D);

        Constants constants = getPlugin().getConstants();

        if (terrain == null) {
            user.sendMessage(constants.commandNotInTerrain);
            return false;
        }

        if (!terrain.isOnSale()) {
            user.sendMessage(constants.commandAcquireNotInSale);
            return false;
        }

        if (terrain.getOwner().equals(user.getName())) {
            user.sendMessage(constants.commandAcquireOwned);
            return false;
        }

        BigDecimal sellPrice = terrain.getSellPrice();

        String formattedSellPrice = DECIMAL_FORMAT.format(sellPrice);

        BigDecimal userMoney = BigDecimal.valueOf(getPlugin().getBank().get(user));

        if (sellPrice.compareTo(userMoney) < 0) {
            user.sendMessage(constants.commandAcquireNotEnoughMoney.replace("<price>", formattedSellPrice));
            return false;
        }

        String lastOwner = terrain.getOwner();

        Action action = actionEvent -> {
            getPlugin().getTerrainController().acquire(user, terrain);
            user.sendMessage(constants.commandAcquireSuccess
                    .replace("<owner>", lastOwner)
                    .replace("<price>", formattedSellPrice)
            );

            Player playerExact = Bukkit.getPlayerExact(lastOwner);
            if (playerExact == null || !playerExact.isOnline()) {
                return;
            }
            playerExact.sendMessage(constants.commandAcquireSuccessBuyer
                    .replace("<buyer>", user.getName())
                    .replace("<price>", formattedSellPrice)
            );
        };

        List<String> commandAcquireConfirmation = new ArrayList<>(constants.commandAcquireConfirmation);
        commandAcquireConfirmation.replaceAll(
                s -> s.replace("<owner>", lastOwner)
                        .replace("<price>", formattedSellPrice)
                        .replace("<size>", Integer.toString(terrain.getSize())
                        )
        );

        Menu menu = new ConfirmationMenu(action, user, commandAcquireConfirmation);

        user.openMenu(menu, plugin.getMenuBridge());
        return true;
    }

}
