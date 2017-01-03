package com.anwesome.ui.gameviewmodul;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 02/01/17.
 */
public class GameObject {
    private float x=0,y=0,r=GameConstants.initial_radius,sx=0,sy=0,finalX,finalY,dimen = 0,w,h;
    private int color;
    private GameObject(float x,float y) {
        this.x = x;
        this.y = y;
        this.finalX = x;
        this.finalY = y;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
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

    public float getSx() {
        return sx;
    }

    public void setSx(float sx) {
        this.sx = sx;
    }

    public float getSy() {
        return sy;
    }

    public void setSy(float sy) {
        this.sy = sy;
    }

    public void setSpeed(float x, float y) {
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
    //Error 1 we can't iterate a list of objects properly if the object doesn't have hashCode() method
    //So let's code that up
    public int hashCode() {
        return (int)x+(int)y+(int)r+(int)sx+color+(int)sy;
    }
}
