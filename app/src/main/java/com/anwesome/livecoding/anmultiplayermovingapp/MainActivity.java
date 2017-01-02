package com.anwesome.livecoding.anmultiplayermovingapp;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.gameviewmodul.GameView;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public void onPause() {
        super.onPause();
        gameView.pause();
    }
    public void onResume() {
        super.onResume();
        gameView.resume();
    }
}
