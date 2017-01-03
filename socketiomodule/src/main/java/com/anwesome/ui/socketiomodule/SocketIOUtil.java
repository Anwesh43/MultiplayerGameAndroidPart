package com.anwesome.ui.socketiomodule;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
public class SocketIOUtil {
    public static Socket getIOSocket(String address) {
        try {
            Socket socket = IO.socket(address);
            return socket.connect();
        }
        catch(Exception ex) {

        }
        return null;
    }
}
