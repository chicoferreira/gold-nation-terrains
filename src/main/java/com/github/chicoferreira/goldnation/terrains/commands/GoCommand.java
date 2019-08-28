package com.github.chicoferreira.goldnation.terrains.commands;

import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.command.parameter.Parameter;
import com.github.chicoferreira.goldnation.terrains.command.variable.types.VariableTypes;
import com.github.chicoferreira.goldnation.terrains.inventory.defaults.ListTerrainsMenu;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;

import java.util.List;
import java.util.UUID;

public class GoCommand extends AbstractCommand {

    public GoCommand(TerrainsPlugin plugin) {
        super(plugin, "ir", "Vai para um terreno.");
        setPermission("goldnation.terrains.go");
        setParameters(Parameter.of("indice", VariableTypes.INTEGER));
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Object objectIndex = commandContexts.get("indice").getValue();
        if (objectIndex == null) {
            ListTerrainsMenu terrainsMenu = new ListTerrainsMenu(user, plugin);
            user.openMenu(terrainsMenu, plugin.getMenuBridge());
            return true;
        }
        int index = (int) objectIndex;

        List<UUID> terrainList = user.getTerrains();
        if (index - 1 < terrainList.size() && index - 1 >= 0) {
            UUID uuid = terrainList.get(index - 1);
            Terrain terrain = getPlugin().getTerrainStorage().get(uuid);
            if (terrain != null) {
                user.getPlayer().teleport(terrain.getSpawnLocation());
                user.sendMessage(getPlugin().getConstants().commandGoSuccess.replace("<index>", Integer.toString(index)));
            } else {
                user.sendMessage(getPlugin().getConstants().commandErrorOccured);
            }
        } else {
            user.sendMessage(getPlugin().getConstants().commandGoNotFoundIndex.replace("<index>", Integer.toString(index)));
        }
        return false;
    }
}
