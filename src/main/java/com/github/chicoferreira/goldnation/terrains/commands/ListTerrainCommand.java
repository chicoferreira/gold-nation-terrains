package com.github.chicoferreira.goldnation.terrains.commands;

import com.github.chicoferreira.goldnation.terrains.Constants;
import com.github.chicoferreira.goldnation.terrains.command.AbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;
import java.util.UUID;

public class ListTerrainCommand extends AbstractCommand {

    public ListTerrainCommand(TerrainsPlugin plugin) {
        super(plugin, "listar", "Lista todos os terrenos comprados.");
        setPermission("goldnation.terrains.list");
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Constants constants = getPlugin().getConstants();
        if (!user.getTerrainList().isEmpty()) {
            for (String string : constants.commandList) {
                if (!string.equalsIgnoreCase("<terrains>")) {
                    user.sendMessage(string);
                } else {
                    List<UUID> terrainList = user.getTerrainList();
                    for (int i = 0; i < terrainList.size(); i++) {
                        UUID uuid = terrainList.get(i);
                        Terrain terrain = getPlugin().getTerrainStorage().get(uuid);
                        if (terrain != null && constants.commandListFormat != null) {
                            TextComponent textComponent = new TextComponent(TextComponent.fromLegacyText(constants.commandListFormat
                                    .replace("<index>", Integer.toString(i + 1))
                                    .replace("<owner>", terrain.getOwner())
                                    .replace("<size>", Integer.toString(terrain.getSize()))
                                    .replace("<areaStartX>", Integer.toString(terrain.getArea().getStartX()))
                                    .replace("<areaStartZ>", Integer.toString(terrain.getArea().getStartZ()))
                                    .replace("<areaEndX>", Integer.toString(terrain.getArea().getEndX()))
                                    .replace("<areaEndZ>", Integer.toString(terrain.getArea().getEndZ()))
                                    .replace("<spawnX>", Integer.toString(terrain.getSpawnLocation().getBlockX()))
                                    .replace("<spawnY>", Integer.toString(terrain.getSpawnLocation().getBlockY()))
                                    .replace("<spawnZ>", Integer.toString(terrain.getSpawnLocation().getBlockZ()))
                                    .replace("<spawnYaw>", Float.toString(terrain.getSpawnLocation().getYaw()))
                                    .replace("<spawnPitch>", Float.toString(terrain.getSpawnLocation().getPitch()))
                                    .replace("<x>", Integer.toString(terrain.getMiddleLocation().getBlockX()))
                                    .replace("<y>", Integer.toString(terrain.getMiddleLocation().getBlockY()))
                                    .replace("<z>", Integer.toString(terrain.getMiddleLocation().getBlockZ()))
                                    .replace("<yaw>", Float.toString(terrain.getMiddleLocation().getYaw()))
                                    .replace("<pitch>", Float.toString(terrain.getMiddleLocation().getPitch()))
                                    .replace("<translatedPvpState>", terrain.isPvpEnabled() ? constants.activated : constants.disactivated)
                                    .replace("<friends>", terrain.getTrustedUsers().isEmpty() ?
                                            constants.emptyString : String.join(", ", terrain.getTrustedUsers()))));
                            if (constants.commandListFormatHold != null) {
                                textComponent.setHoverEvent(new HoverEvent(
                                                HoverEvent.Action.SHOW_TEXT,
                                                TextComponent.fromLegacyText(constants.commandListFormatHold)
                                        )
                                );
                            }

                            String commandListFormatClickCommand = constants.commandListFormatClickCommand;
                            if (commandListFormatClickCommand != null) {
                                textComponent.setClickEvent(new ClickEvent(
                                                ClickEvent.Action.SUGGEST_COMMAND,
                                                commandListFormatClickCommand
                                                        .replace("<index>", Integer.toString(i + 1))
                                        )
                                );
                            }

                            user.getPlayer().spigot().sendMessage(textComponent);

                        }
                    }
                }
            }
        } else {
            user.sendMessage(constants.commandListEmptyTerrains);
        }
        return false;
    }
}
