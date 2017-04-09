package com.chattr.neonardo.chattr;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Listener implements Runnable{

    ObjectInputStream ois;
    Socket socket;
    CancerClient client;
    public Listener(Socket s, CancerClient c) throws IOException{
        ois = new ObjectInputStream(s.getInputStream());
        socket = s;
        client = c;
    }

    @Override
    public void run() {
        while (!socket.isInputShutdown()){
            try {
                String msg = ((Message) ois.readObject()).text;
                client.onMessageIncoming(msg);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(){
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
