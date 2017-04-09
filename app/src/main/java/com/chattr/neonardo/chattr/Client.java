package com.chattr.neonardo.chattr;

public class Client extends CancerClient {

    ChatMain cm = new ChatMain();

    protected void onMessageIncoming(String msg){
        cm.displayMessage(msg);
    }
}
