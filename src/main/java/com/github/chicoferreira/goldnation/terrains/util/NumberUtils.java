package com.github.chicoferreira.goldnation.terrains.util;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {

    public static String formatNumber(final Object number) {
        return NumberFormat.getNumberInstance(Locale.GERMANY).format(number);
    }

}
