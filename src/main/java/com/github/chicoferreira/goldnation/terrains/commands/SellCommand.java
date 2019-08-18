package com.github.chicoferreira.goldnation.terrains.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.types.VariableTypes;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainController;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import com.github.chicoferreira.goldnation.terrains.util.Result;
import org.bukkit.Location;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class SellCommand extends AbstractCommand {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");

    public SellCommand(TerrainsPlugin plugin) {
        super(plugin, "vender", "Coloca à venda o seu terreno.");
        setPermission("goldnation.terrains.sell");
        setParameters(Parameter.ofMandatory("retirar/preço", VariableTypes.BIG_DECIMAL));
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

        if (!terrain.getOwner().equals(user.getName())) {
            user.sendMessage(constants.commandNotOwner);
            return false;
        }

        String value = (String) commandContexts.get("retirar/preço").getValue();

        TerrainController terrainController = getPlugin().getTerrainController();

        if (value.equalsIgnoreCase("retirar")) {
            if (terrain.isOnSale()) {
                terrainController.removeFromSale(terrain);
                user.sendMessage(constants.commandSellRemovedFromSale);
                return true;
            }

            user.sendMessage(constants.commandSellNotInSale);
            return true;
        }

        Result<BigDecimal> parse = VariableTypes.BIG_DECIMAL.parse(value);
        if (!parse.wasSuccessful()) {
            parse.runFallback(user);
            return false;
        }

        BigDecimal bigDecimal = parse.get().abs();


        BigDecimal sellMaxPrice = constants.commandSellMaxPrice;
        if (bigDecimal.compareTo(sellMaxPrice) < 0) {

            user.sendMessage(constants.commandSellMaxPriceExceeded
                    .replace("<max price>", DECIMAL_FORMAT.format(sellMaxPrice)));
            return false;
        }

        terrainController.putUpForSale(terrain, bigDecimal);
        String formattedBigDecimal = DECIMAL_FORMAT.format(bigDecimal);

        user.sendMessage(constants.commandSellPutUp.replace("<price>", formattedBigDecimal));

        return true;
    }
}
