package com.github.chicoferreira.goldnation.terrains.wall;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.List;

public class BlockWallPlacer implements WallPlacer {

    private Material material;

    public BlockWallPlacer(Material material) {
        this.material = material;
    }

    @Override
    public void place(List<Location> locationList) {
        for (Location location : locationList) {
            Block block = location.getBlock();
            if (block.isEmpty()) {
                block.setType(this.material);
            }
        }
    }

}
