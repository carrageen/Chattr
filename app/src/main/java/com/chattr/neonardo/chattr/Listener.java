package com.chattr.neonardo.chattr;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import cancerApi.Message;

public class Listener implements Runnable{

    private ObjectInputStream ois;
    private Socket socket;
    private CancerClient client;

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
            } catch (IOException | ClassNotFoundException e) {
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
