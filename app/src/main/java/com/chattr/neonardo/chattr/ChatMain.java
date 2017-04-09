package com.chattr.neonardo.chattr;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatMain extends AppCompatActivity {

    TextView chat;
    EditText message;
    boolean doubleBackToExitPressedOnce = false;
    ObjectOutputStream oos;
    Socket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);

        chat = (TextView) findViewById(R.id.chat);
        chat.setMovementMethod(new ScrollingMovementMethod());
        message = (EditText) findViewById(R.id.message);

        try {
            socket = new Socket("80.169.156.67", 4269);
            oos = new ObjectOutputStream(socket.getOutputStream());
            new Thread(new IncomingHandler(socket)).start();
            chat.append("\n" + getString(R.string.connection_established));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(View v) {
        try {
            String msg = message.getText().toString();
            oos.writeObject(msg);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //chat.append("\n" + getString(R.string.you) + message.getText().toString());

        final int scrollAmount = chat.getLayout().getLineTop(chat.getLineCount()) - chat.getHeight();
        if (scrollAmount > 0)
            chat.scrollTo(0, scrollAmount);
        else
            chat.scrollTo(0, 0);
    }

    public void displayMessage(String msg){
        chat.append("\n" + msg);
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.backButtonTwice), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
