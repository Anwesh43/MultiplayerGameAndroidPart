package com.anwesome.ui.gameviewmodul;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.*;

import com.anwesome.ui.eventbusmodule.BusUtil;
import com.squareup.otto.Bus;

/**
 * Created by anweshmishra on 02/01/17.
 */
public class GameView extends SurfaceView {
    SurfaceHolder surfaceHolder;
    private GameRunner gameRunner;
    private Thread gameThread;
    private Bus bus = BusUtil.getBus();
    private Activity mActivity;
    public GameView(Context context) {
        super(context);
        this.mActivity = (Activity)context;
        surfaceHolder = getHolder();
        gameRunner = new GameRunner(surfaceHolder,this);
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
    public void addGameObject(GameObject gameObject) {
        gameRunner.addGameObject(gameObject);
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
    public void postGameObject(final GameObject currentObject) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bus.post(currentObject);
            }
        });

    }
}
