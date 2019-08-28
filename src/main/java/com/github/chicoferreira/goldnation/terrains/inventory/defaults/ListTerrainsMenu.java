package com.github.chicoferreira.goldnation.terrains.inventory.defaults;


import com.github.chicoferreira.goldnation.terrains.inventory.Item;
import com.github.chicoferreira.goldnation.terrains.inventory.impl.BasicMenu;
import com.github.chicoferreira.goldnation.terrains.inventory.util.Slot;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.itembuilder.StackBuilder;
import org.bukkit.Material;

import java.util.List;
import java.util.UUID;

public class ListTerrainsMenu extends BasicMenu {

    private static final int START_SLOT = Slot.of(2, 2);
    private static final int SKIP_BETWEEN = 1;

    public ListTerrainsMenu(User user, TerrainsPlugin plugin) {
        super("Terrenos:", 27);

        init(user, plugin);
    }

    private void init(User user, TerrainsPlugin plugin) {
        List<UUID> terrains = user.getTerrains();
        for (int index = 0; index < terrains.size(); index++) {
            UUID uuid = terrains.get(index);

            Terrain terrain = plugin.getTerrainStorage().get(uuid);
            if (terrain == null) continue;

            int slot = START_SLOT + (index * SKIP_BETWEEN) + index;
            int finalIndex = index + 1;
            addItem(new Item(slot, StackBuilder.newBuilder(Material.GRASS)
                    .consume($ -> {
                                $.withDisplayName("§eTerreno #" + finalIndex);
                                $.withLore("§7Clique para ir até ao seu terreno!");
                            }
                    ).create(),
                    actionEvent -> {
                        user.getPlayer().teleport(terrain.getSpawnLocation());
                        user.sendMessage(plugin.getConstants().commandGoSuccess.replace("<index>", Integer.toString(finalIndex)));
                        user.closeMenu();
                    }
            ));
        }
    }

}
