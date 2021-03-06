package com.chattr.neonardo.chattr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMatchTap(View v) {
        Toast myToast = Toast.makeText(getApplicationContext(), R.string.searching, Toast.LENGTH_SHORT);
        Intent intent = new Intent(MainActivity.this, ChatMain.class);
        myToast.show();
        startActivity(intent);
    }
}
