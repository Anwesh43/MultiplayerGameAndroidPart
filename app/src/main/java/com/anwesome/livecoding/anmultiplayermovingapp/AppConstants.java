package com.anwesome.livecoding.anmultiplayermovingapp;

/**
 * Created by anweshmishra on 03/01/17.
 */
public class AppConstants {
    public static final String HOST_ADDRESS = "http://192.168.0.4:8000/game";//this will be my laptop address with port and room name lemme do a ipconfig

    public static final String SEND_GAME_OBJECT = "SEND_GAME_OBJECT";//this is the event to send game object to the socket.io server

    public static final String RECEIVE_GAME_OBJECT = "RECEIVE_GAME_OBJECT";//this is the event to receive game object from server

}
