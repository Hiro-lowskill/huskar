package com.example.huskar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_Up extends AppCompatActivity {

    private EditText edEmail, edSecName;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser != null){
            Toast.makeText(this,"Заполнено", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Заполните данные", Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        edEmail = findViewById(R.id.edEmail);
        edSecName = findViewById(R.id.edPassword);
        mAuth = FirebaseAuth.getInstance();
    }
    public void OnClickSignUp(View view){
        if(!TextUtils.isEmpty(edEmail.getText().toString()) && !TextUtils.isEmpty(edSecName.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(edEmail.getText().toString(),edSecName.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Пользователь зарегестрировался", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Sign_Up.this,Sign_in.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Повторите попытку еще раз", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else{
            Toast.makeText(getApplicationContext(),"Заполните все", Toast.LENGTH_SHORT).show();
        }
    }
    public void btn_signin(View view) {
        Intent i = new Intent(this, Sign_in.class);
        startActivity(i);
    }
}