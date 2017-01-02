package com.anwesome.ui.gameviewmodul;

import android.graphics.Color;

/**
 * Created by anweshmishra on 02/01/17.
 */
public class GameConstants {
    public static final long delay = 50l;
    public static final float initial_radius = 50;
    private static int getColorFromHexCode(String colorCode) {
        return Color.parseColor(colorCode);
    }
    public static final int colors[] = {getColorFromHexCode("#4DB6AC"),getColorFromHexCode("#F4511E"),getColorFromHexCode("#1976D2"),getColorFromHexCode("#F9A825"),getColorFromHexCode("#7E57C2"),getColorFromHexCode("#ef5350"),getColorFromHexCode("#76FF03")};
}
