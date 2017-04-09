package com.chattr.neonardo.chattr;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class CancerClient {
    private ObjectOutputStream oos;
    private Listener listener;

    public void connect(Socket s) {
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            listener = new Listener(s, this);
            new Thread(listener).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String s) {
        try {
            oos.writeObject(new Message(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void onMessageIncoming(String msg);

    public void disconnect(){
        try {
            oos.close();
            listener.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
