package com.chattr.neonardo.chattr;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = -2870173773950597861L;
    public String text;

    public Message(String msg){
        this.text = msg;
    }

    public Message(){
        this.text = "";
    }

    public String toString(){
        return text;
    }
}