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
    public static final String colors[] = {"#4DB6AC", "#F4511E", "#1976D2", "#F9A825", "#7E57C2", "#ef5350", "#76FF03"};
}
