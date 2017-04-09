package com.chattr.neonardo.chattr;

import android.support.v7.app.AppCompatActivity;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends AppCompatActivity {

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket socket;
    private String server;
    private int port;

    private Client(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public boolean start() {
        try {
            socket = new Socket(server, port);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        String msg = getString(R.string.connection_established) + socket.getInetAddress() + ":" + socket.getPort();
        send(msg);

        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            send(getString(R.string.error_stream));
            return false;
        }

        return true;
    }

    private String send(String msg) {
        return msg;
    }


}
