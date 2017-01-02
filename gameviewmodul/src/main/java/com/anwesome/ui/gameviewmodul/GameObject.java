package com.anwesome.ui.gameviewmodul;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 02/01/17.
 */
public class GameObject {
    private float x=0,y=0,r=GameConstants.initial_radius,sx=0,sy=0,finalX,finalY,dimen = 0;
    private int color;
    private GameObject(float x,float y) {
        this.x = x;
        this.y = y;
        this.finalX = x;
        this.finalY = y;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public static GameObject newInstance(float x,float y) {
        return new GameObject(x,y);
    } //Creating a singleton
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawCircle(x,y,r,paint);
    }
    public void move() {
        this.x+=sx;
        this.y+=sy;
        if(this.x == this.finalX && dimen == 0) {
            sx = 0;
            sy = 0;
            y = finalY;
        }
        else if(this.y == this.finalY && dimen == 1) {
            sx = 0;
            sy = 0;
            x = finalX;
        }
    }
    public void setSpeed(float x,float y) {
        if(x != this.x || y!=this.y) {
            if(Math.abs(x-this.x)>=Math.abs(y-this.y)) {
                sx = 20 * (x - this.x) / Math.abs(x - this.x);
                sy = sx * (y - this.y) / (x - this.x);
                dimen = 0;
            }
            else {
                sy = 20 * (y - this.y) / Math.abs(y - this.y);
                sx = sy * (x - this.x) / (y - this.y);
                dimen = 1;
            }
        }
        this.x -= this.x%20;
        this.y -= this.y%20;
        finalX = x-x%20;
        finalY = y-y%20;
    }
}
