package com.example.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    private ImageView logo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo= findViewById(R.id.logo);
        handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent monIntent = new Intent(getApplicationContext(),Login.class);
                startActivity(monIntent);

            }
        }, 5000);
    }


}