package com.github.chicoferreira.goldnation.terrains.inventory.util;

import org.apache.commons.lang.Validate;

public class Slot {

    public static int of(int row, int column) {
        Validate.isTrue(row > 0 && column > 0, "Both row and column needs to be higher than 0.");
        return (row - 1) + (column - 1) * 9;
    }

}
