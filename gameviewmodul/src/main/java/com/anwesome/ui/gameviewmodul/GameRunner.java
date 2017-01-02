package com.anwesome.ui.gameviewmodul;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import java.util.*;
/**
 * Created by anweshmishra on 02/01/17.
 */
public class GameRunner implements Runnable {
    private SurfaceHolder surfaceHolder;
    private long prev_time = 0;
    private int time = 0;
    private boolean isRunning = true;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<GameObject> gameObjects = new ArrayList<>();
    private GameObject currentObject;
    public GameRunner(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;

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
    }
    public void run() {
        while(isRunning) {
            if(surfaceHolder.getSurface().isValid()) {
                if(prev_time == 0 || System.currentTimeMillis() -prev_time >= GameConstants.delay) {
                    Canvas canvas= surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.BLACK);
                    if(time == 0) {
                        initGameObject(canvas.getWidth(),canvas.getHeight());
                    }
                    currentObject.draw(canvas,paint);
                    currentObject.move();
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
}
