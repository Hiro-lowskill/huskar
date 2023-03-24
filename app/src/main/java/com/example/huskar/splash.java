package com.example.huskar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main = new Intent(splash.this,Sign_Up.class);
                splash.this.startActivity(main);
                splash.this.finish();
            }
        },SPLASH_DISPLAY_LENGHT);
    }
}