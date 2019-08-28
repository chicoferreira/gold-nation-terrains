package com.github.chicoferreira.goldnation.terrains.commands;

import com.github.chicoferreira.goldnation.terrains.command.HelpAbstractCommand;
import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.inventory.Menu;
import com.github.chicoferreira.goldnation.terrains.inventory.defaults.TerrainMenu;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.Position2D;
import org.bukkit.Location;

public class TerrainCommand extends HelpAbstractCommand {

    public TerrainCommand(TerrainsPlugin plugin) {
        super(plugin, "terreno", "Comando sobre terrenos.");
        setSubcommands(
                new GoCommand(getPlugin()),
                new BuyCommand(getPlugin()),
                new AbandonCommand(getPlugin()),
                new ListTerrainCommand(getPlugin()),
                new InfoCommand(getPlugin()),
                new ExpandCommand(getPlugin()),
                new PvpToggleCommand(getPlugin()),
                new FriendAddCommand(getPlugin()),
                new FriendRemoveCommand(getPlugin()),
                new SetSpawnCommand(getPlugin()),
                new AcquireCommand(getPlugin()),
                new SellCommand(getPlugin())
        );
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Location location = user.getPlayer().getLocation();

        Position2D position2D = new Position2D(location.getBlockX(), location.getBlockZ());
        Terrain terrain = getPlugin().getTerrainStorage().get(position2D);

        if (terrain == null) {
            return super.execute(user, commandContexts);
        }

        Menu menu = new TerrainMenu(user, terrain, plugin);
        user.openMenu(menu, plugin.getMenuBridge());
        return true;
    }
}
