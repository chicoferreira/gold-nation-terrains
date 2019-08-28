package com.github.chicoferreira.goldnation.terrains.inventory.defaults;

import com.github.chicoferreira.goldnation.terrains.inventory.Item;
import com.github.chicoferreira.goldnation.terrains.inventory.action.Actions;
import com.github.chicoferreira.goldnation.terrains.inventory.impl.BasicMenu;
import com.github.chicoferreira.goldnation.terrains.inventory.util.Slot;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.terrain.Terrain;
import com.github.chicoferreira.goldnation.terrains.terrain.controller.TerrainController;
import com.github.chicoferreira.goldnation.terrains.user.User;
import com.github.chicoferreira.goldnation.terrains.util.NumberUtils;
import com.github.chicoferreira.goldnation.terrains.util.itembuilder.StackBuilder;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TerrainMenu extends BasicMenu {

    public TerrainMenu(User user, Terrain terrain, TerrainsPlugin plugin) {
        super("Informações do terreno:", 27);

        init(user, terrain, plugin);
    }

    private void init(User user, Terrain terrain, TerrainsPlugin plugin) {
        int terrainSize = terrain.getSize();

        TerrainController terrainController = plugin.getTerrainController();

        double price = terrainController.calculatePrice(terrainSize);
        String formattedPrice = NumberUtils.formatNumber(price);

        addItem(new Item(
                Slot.of(2, 2),
                StackBuilder.newBuilder(Material.GOLD_INGOT)
                        .withDisplayName("§eCusto do terreno: §7" + formattedPrice)
                        .create(),
                Actions.NULL_ACTION
        ));

        int terrainIndex = 0;

        List<UUID> terrains = user.getTerrains();
        for (int i = 0; i < terrains.size(); i++) {
            UUID uuid = terrains.get(i);
            if (uuid.equals(terrain.getUuid())) {
                terrainIndex = i;
                break;
            }
        }

        addItem(new Item(
                Slot.of(4, 2),
                StackBuilder.newBuilder(Material.SIGN)
                        .withDisplayName("§eÍndice do terreno: §7" + (terrainIndex + 1))
                        .create(),
                Actions.NULL_ACTION
        ));

        List<String> withPermission = new ArrayList<>();

        String prefix = "§7- ";
        withPermission.add(prefix + terrain.getOwner() + " (Dono)");
        for (String trustedUser : terrain.getTrustedUsers()) {
            withPermission.add(prefix + trustedUser);
        }

        addItem(new Item(
                Slot.of(6, 2),
                StackBuilder.newBuilder(Material.IRON_HELMET)
                        .withDisplayName("§eCom permissão:")
                        .withLore(withPermission)
                        .create(),
                Actions.NULL_ACTION
        ));

        addItem(new Item(
                Slot.of(8, 2),
                StackBuilder.newBuilder(Material.BOOKSHELF)
                        .withDisplayName("§eTamanho do terreno: §7" + terrainSize + "x" + terrainSize)
                        .create(),
                Actions.NULL_ACTION
        ));
    }

}
