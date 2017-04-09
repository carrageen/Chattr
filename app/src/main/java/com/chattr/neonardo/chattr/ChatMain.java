package com.chattr.neonardo.chattr;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChatMain extends AppCompatActivity {

    TextView chat;
    EditText message;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);

        chat = (TextView) findViewById(R.id.chat);
        chat.setMovementMethod(new ScrollingMovementMethod());
        message = (EditText) findViewById(R.id.message);
    }

    public void sendMessage(View v) {
        chat.append("\n" + getString(R.string.you) + message.getText().toString());

        final int scrollAmount = chat.getLayout().getLineTop(chat.getLineCount()) - chat.getHeight();
        if (scrollAmount > 0)
            chat.scrollTo(0, scrollAmount);
        else
            chat.scrollTo(0, 0);
    }

    public void send(View v, String msg){
        chat.append("\n" + getString(R.string.you) + msg);
    }

    public String getMessage(){
        return message.getText().toString();
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
