package com.chattr.neonardo.chattr;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


public class IncomingHandler implements Runnable {
    ObjectInputStream ois;
    ChatMain cm = new ChatMain();

    public IncomingHandler(Socket s) throws IOException {
        ois = new ObjectInputStream(s.getInputStream());
    }

    public void run() {
        while (true) {
            try {
                String msg = ((Message) ois.readObject()).msg;
                //TODO sende Nachricht zu userinterface(chat.append)
                cm.displayMessage(msg);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
