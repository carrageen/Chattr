package com.chattr.neonardo.chattr;

import java.io.IOException;
import java.io.ObjectInputStream;


public class IncomingHandler implements Runnable {
    ObjectInputStream ois;

    public void run() {
        while (true) {
            try {
                String msg = ((Message) ois.readObject()).msg;
                //TODO sende Nachricht zu userinterface(chat.append)
                //System.out.println(msg);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
