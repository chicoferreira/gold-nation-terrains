package com.github.chicoferreira.goldnation.terrains.command.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.bank.Bank;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.types.VariableTypes;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainController;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.NumberUtils;
import org.bukkit.Location;

public class BuyCommand extends AbstractCommand {

    private TerrainController terrainController;

    public BuyCommand(TerrainsPlugin plugin) {
        super(plugin, "comprar", "Compra um terreno.");
        this.terrainController = plugin.getTerrainController();

        setParameters(Parameter.ofMandatory("tamanho", VariableTypes.INTEGER));
    }

    @Override
    public boolean execute(User user, CommandContexts contexts) {
        int size = (int) contexts.get("tamanho").getValue();

        Constants constants = getPlugin().getConstants();

        if (getPlugin().getUserTerrainLimitProvider().get(user) > user.getTerrainList().size()) {
            if (user.getPlayer().getLocation().getWorld().getName().equals(getPlugin().getConstants().allowedWorld)) {
                if (size >= constants.minTerrainSize) {
                    if (size <= constants.maxTerrainSize) {
                        double price = terrainController.calculatePrice(size);
                        Bank bank = getPlugin().getBank();

                        if (bank.get(user) >= price) {
                            Location location = user.getPlayer().getLocation();
                            if (!terrainController.hasNearbyTerrains(location, size)) {
                                if (bank.remove(user, price)) {
                                    boolean successful = terrainController.acquire(user, user.getPlayer().getLocation(), size);
                                    if (successful) {
                                        user.sendMessage(constants.commandBuySuccessful
                                                .replace("<price>", NumberUtils.formatNumber(price))
                                                .replace("<size>", Integer.toString(size)));
                                    } else {
                                        user.sendMessage(constants.commandErrorOccured);
                                    }
                                    return successful;
                                } else {
                                    user.sendMessage(constants.commandCouldntModifyMoney);
                                }
                            } else {
                                user.sendMessage(constants.commandBuyNearbyTerrains);
                            }
                        } else {
                            user.sendMessage(constants.commandBuyNotEnoughMoney
                                    .replace("<price>", NumberUtils.formatNumber(price))
                                    .replace("<size>", Integer.toString(size)));
                        }
                    } else {
                        user.sendMessage(constants.commandSizeHigherThanMax);
                    }
                } else {
                    user.sendMessage(constants.commandSizeLowerThanMin);
                }
            } else {
                user.sendMessage(constants.commandBuyNotInWorld);
            }
        } else {
            user.sendMessage(constants.commandBuyLimit);
        }

        return false;
    }
}
