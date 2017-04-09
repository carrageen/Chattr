package com.chattr.neonardo.chattr;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class ChatMain extends AppCompatActivity {

    TextView chat;
    EditText message;
    boolean doubleBackToExitPressedOnce = false;
    Socket socket;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);

        chat = (TextView) findViewById(R.id.chat);
        chat.setMovementMethod(new ScrollingMovementMethod());
        message = (EditText) findViewById(R.id.message);

        new AsyncTask<ChatMain, Void, Void>() {
            @Override
            protected Void doInBackground(ChatMain... cm) {
                try {
                    Log.d("test", "TEST CONNECTION1111");
                    Looper.prepare();
                    socket = new Socket("80.139.144.147", 4269);
                    Log.d("test", "TEST CONNECTION");
                    client = new Client(cm);
                    client.connect(socket);
                    Log.d("test", "TEST CONNECTION222222");
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute() {
                chat.append("\n" + getString(R.string.connection_established));

            }
        }.execute(this);
    }

    public void sendMessage(View v) {

        new Thread() {
            @Override
            public void run() {
                client.send(message.getText().toString());
            }
        }.start();


        //chat.append("\n" + getString(R.string.you) + message.getText().toString());

        final int scrollAmount = chat.getLayout().getLineTop(chat.getLineCount()) - chat.getHeight();
        if (scrollAmount > 0)
            chat.scrollTo(0, scrollAmount);
        else
            chat.scrollTo(0, 0);
    }

    public void displayMessage(String msg) {
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
                client.disconnect();
            }
        }, 2000);
    }
}
