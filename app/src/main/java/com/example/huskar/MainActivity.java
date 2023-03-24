package com.example.huskar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void btn(View view){
        Intent btn = new Intent(this,Sign_in.class);
        startActivity(btn);

    }

    public void btn3(View view){
        Intent btn = new Intent(this,ReadActivity.class);
        startActivity(btn);

    }


    public void btn4(View view){
        Intent btn = new Intent(this,UploadPhotoActivity.class);
        startActivity(btn);

    }




    public void btn7(View view){
        Intent btn = new Intent(this,Sign_in.class);
        startActivity(btn);

    }

    public void btn8(View view){
        Intent btn = new Intent(this,ReadActivity.class);
        startActivity(btn);

    }
}