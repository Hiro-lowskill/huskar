package com.example.huskar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";
    private List<User>  listTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getDataFromDB();
        setOnClickItem();
    }

    private void init() {
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    private void getDataFromDB() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (listData.size() > 0) listData.clear();
                if (listTemp.size() > 0) listTemp.clear();
                for (DataSnapshot ds : datasnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    assert user != null;
                    listData.add(user.name);
                    listTemp.add(user);


                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseerror) {

            }

        };
        mDataBase.addValueEventListener(vListener);
    }
    private void setOnClickItem(){
        listView.setOnItemClickListener(((parent, view, position, id) -> {
            User user = listTemp.get(position);
            Intent i = new Intent(MainActivity.this,ShowActivity.class);
            i.putExtra(Constant.USER_NAME,user.name);
            i.putExtra(Constant.USER_SEC_NAME,user.subject);
            i.putExtra(Constant.USER_EMAIL,user.email);
            i.putExtra(Constant.USER_IMAGE_ID,user.image_id);
            startActivity(i);
        }));
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