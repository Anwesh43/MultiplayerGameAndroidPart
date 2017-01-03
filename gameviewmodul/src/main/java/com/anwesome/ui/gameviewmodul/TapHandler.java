package com.anwesome.ui.gameviewmodul;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 04/01/17.
 */
public class TapHandler {
    private int count = 0;
    private boolean isShown = false;
    private float x, y;
    public void tap(float x,float y) {
        count = 0;
        isShown = true;
        this.x = x;
        this.y = y;
    }
    public void draw(Canvas canvas, Paint paint) {
        if(isShown) {
            paint.setColor(Color.parseColor("#88FFAB40"));
            canvas.drawCircle(x,y,30,paint);
            count++;
            if(count == 12) {
                isShown = false;
            }
        }
    }
}
