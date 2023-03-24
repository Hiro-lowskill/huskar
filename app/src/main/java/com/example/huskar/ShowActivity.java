package com.example.huskar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowActivity extends AppCompatActivity {

    private TextView tvSecName, tvName, tvEmail;
    private ImageView imDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();
        getIntentMain();
    }
    private void init(){
        tvName = findViewById(R.id.tvName);
        tvSecName = findViewById(R.id.tvSecName);
        tvEmail = findViewById(R.id.tvEmail);
        imDB = findViewById(R.id.img_db);
    }
    private void getIntentMain(){
        Intent i = getIntent();
        if(i != null){
            Picasso.get().load(i.getStringExtra(Constant.USER_IMAGE_ID)).into(imDB);
            tvName.setText(i.getStringExtra(Constant.USER_NAME));
            tvSecName.setText(i.getStringExtra(Constant.USER_SEC_NAME));
            tvEmail.setText(i.getStringExtra(Constant.USER_EMAIL));
        }
    }

}