package com.anwesome.ui.gameviewmodul;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.anwesome.ui.eventbusmodule.BusUtil;
import com.squareup.otto.Bus;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 02/01/17.
 */
public class GameRunner implements Runnable {
    private SurfaceHolder surfaceHolder;
    private long prev_time = 0;
    private int time = 0;
    private boolean isRunning = true;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //Error number 2 we shouldn't use a list here there is a chance a gameObject can be modified concurrently
    private ConcurrentLinkedQueue<GameObject> gameObjects = new ConcurrentLinkedQueue<>();
    private GameObject currentObject;

    private GameView gameView;
    public GameRunner(SurfaceHolder surfaceHolder,GameView gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }
    public void pause() {
        isRunning = false;
    }
    public void resume() {
        isRunning = true;
    }
    private void initGameObject(int w,int h) {
        Random random = new Random();
        int x = random.nextInt(w),y = random.nextInt(h);
        int randomColorIndex = random.nextInt(GameConstants.colors.length);
        currentObject =   GameObject.newInstance(x,y);
        currentObject.setColor(GameConstants.colors[randomColorIndex]);
        currentObject.setW(w);
        currentObject.setH(h);
    }
    public void run() {
        while(isRunning) {
            if(surfaceHolder.getSurface().isValid()) {
                //Lets draw the other gameObjects
                if(prev_time == 0 || System.currentTimeMillis() -prev_time >= GameConstants.delay) {
                    Canvas canvas= surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.BLACK);
                    int w = canvas.getWidth(),h = canvas.getHeight();
                    if(time == 0) {
                        initGameObject(w,h);
                    }
                    currentObject.draw(canvas,paint);
                    currentObject.move();

                    if(currentObject.getSx()!=0 || currentObject.getSy() != 0) {
                        gameView.postGameObject(currentObject);
                    }
                    //This piece of code will give lot of errors

                    for(GameObject gameObject:gameObjects) {
                        gameObject.draw(canvas,paint);
                        gameObject.move();
                    }
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    prev_time = System.currentTimeMillis();
                    time++;
                }
            }
        }
    }
    public void handleTouch(float x,float y) {
        if(currentObject!=null) {
            currentObject.setSpeed(x,y);
        }
    }
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
