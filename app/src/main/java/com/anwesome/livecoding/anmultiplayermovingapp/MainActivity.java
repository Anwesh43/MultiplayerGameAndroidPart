package com.anwesome.livecoding.anmultiplayermovingapp;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.eventbusmodule.BusUtil;
import com.anwesome.ui.gameviewmodul.GameObject;
import com.anwesome.ui.gameviewmodul.GameView;
import com.anwesome.ui.socketiomodule.SocketIOUtil;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;
    private Bus bus;
    private Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bus = BusUtil.getBus();
        bus.register(this);
        mSocket = SocketIOUtil.getIOSocket(AppConstants.HOST_ADDRESS);//Do it later
    }
    private void listenForGameObjects() {
        if(mSocket!=null) {
            mSocket.on(AppConstants.RECEIVE_GAME_OBJECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    if(args.length>0) {
                        String gameObjectJson = (String)args[0];
                        GameObject gameObject = GsonUtil.gameObjectFromJson(gameObjectJson);
                        //Now we have got the new gameobject we need to send to gameview.

                    }
                }
            });
        }
    }
    @Subscribe
    public void getGameObject(GameObject gameObject) {

        String gameObjectJson = GsonUtil.gameObjectToJson(gameObject);
        //we have got the json now we will send it to server
        if(mSocket != null) {
            mSocket.emit(AppConstants.SEND_GAME_OBJECT, gameObjectJson);
        }
    }//Converting to json will be much better to communicate with server
    public void onPause() {
        super.onPause();
        gameView.pause();
    }
    public void onResume() {
        super.onResume();
        gameView.resume();
    }
}
