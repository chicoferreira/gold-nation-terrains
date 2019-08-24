package com.github.chicoferreira.goldnation.terrains.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.bank.Bank;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.types.VariableTypes;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainController;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.NumberUtils;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

public class ExpandCommand extends AbstractCommand {

    private TerrainController terrainController;

    public ExpandCommand(TerrainsPlugin plugin) {
        super(plugin, "expandir", "Expande um terreno.");
        this.terrainController = plugin.getTerrainController();
        setPermission("goldnation.terrains.expand");

        setParameters(Parameter.ofMandatory("tamanho", VariableTypes.INTEGER));
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Location location = user.getPlayer().getLocation();

        Terrain terrain = getPlugin().getTerrainStorage().get(new Position2D(location.getBlockX(), location.getBlockZ()));
        Constants constants = getPlugin().getConstants();

        int sizeToExpand = (int) commandContexts.get("tamanho").getValue();

        if (terrain != null) {
            if (terrain.getOwner().equals(user.getName())) {

                int newSize = terrain.getSize() + sizeToExpand;

                if (newSize >= constants.minTerrainSize) {
                    if (newSize <= constants.maxTerrainSize) {
                        double price = terrainController.calculateExpansionPrice(terrain, newSize);
                        Bank bank = getPlugin().getBank();

                        if (bank.get(user) >= price) {
                            if (terrainController.canExpand(terrain, newSize)) {
                                if (bank.remove(user, price)) {
                                    boolean successful = terrainController.expand(terrain, newSize);
                                    if (successful) {
                                        user.sendMessage(constants.commandExpandSuccessful
                                                .replace("<price>", NumberUtils.formatNumber(price))
                                                .replace("<sizeToExpand>", Integer.toString(sizeToExpand))
                                                .replace("<newSize>", Integer.toString(newSize)));
                                    } else {
                                        user.sendMessage(constants.commandErrorOccured);
                                    }
                                    return successful;
                                } else {
                                    user.sendMessage(constants.commandCouldntModifyMoney);
                                }
                            } else {
                                user.sendMessage(constants.commandExpandNearbyTerrains);
                            }
                        } else {
                            user.sendMessage(constants.commandExpandNotEnoughMoney
                                    .replace("<price>", NumberUtils.formatNumber(price))
                                    .replace("<size>", Integer.toString(sizeToExpand)));
                        }
                    } else {
                        user.sendMessage(constants.commandSizeHigherThanMax);
                    }
                } else {
                    user.sendMessage(constants.commandSizeLowerThanMin);
                }
            } else {
                user.sendMessage(constants.commandNotOwner);
            }
        } else {
            user.sendMessage(constants.commandNotInTerrain);
        }
        return false;
    }
}
