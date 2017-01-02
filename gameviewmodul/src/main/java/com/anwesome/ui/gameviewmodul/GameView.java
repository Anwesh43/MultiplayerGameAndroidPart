package com.anwesome.ui.gameviewmodul;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.*;

/**
 * Created by anweshmishra on 02/01/17.
 */
public class GameView extends SurfaceView {
    SurfaceHolder surfaceHolder;
    private GameRunner gameRunner;
    private Thread gameThread;
    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        gameRunner = new GameRunner(surfaceHolder);
        gameThread = new Thread(gameRunner);
        gameThread.start();
    }
    public void pause() {
        gameRunner.pause();
        while(true) {
            try {
                gameThread.join();
                break;
            }
            catch(Exception ex) {

            }
        }
    }
    public void resume() {
        gameRunner.resume();
        gameThread = new Thread(gameRunner);
        gameThread.start();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()  == MotionEvent.ACTION_DOWN && gameRunner!=null) {
            gameRunner.handleTouch(event.getX(),event.getY());
        }
        return true;
    }
}
