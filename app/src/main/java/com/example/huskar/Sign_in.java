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

public class Sign_in extends AppCompatActivity {

    private EditText edEmail, edPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }
    private void init(){
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        mAuth = FirebaseAuth.getInstance();
    }
    public void OnClickSignIn(View view){
        if(!TextUtils.isEmpty(edEmail.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString()) ) {
            mAuth.signInWithEmailAndPassword(edEmail.getText().toString(),edPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Вход успешно выполнен", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Sign_in.this,ReadActivity.class);
                        startActivity(i);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"пользователь не вошел, попробуйте еще раз", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}