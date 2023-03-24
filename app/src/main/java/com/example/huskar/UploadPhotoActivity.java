package com.example.huskar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class UploadPhotoActivity extends AppCompatActivity {

    private ImageView ShowImage;
    private StorageReference mStorageRef;
    private Uri uploadUri;
    private EditText edName, edSecName, edEmail;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
        init();
    }

    public void init() {
        ShowImage = findViewById(R.id.ShowImage);
        mStorageRef = FirebaseStorage.getInstance().getReference("Image_User");
        edName = findViewById(R.id.edName);
        edSecName = findViewById(R.id.edPassword);
        edEmail = findViewById(R.id.edEmail);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

    }

    public void Choose(View view) {
        getImage();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && data.getData() != null) {
            if (resultCode == RESULT_OK) {
                Log.d("MyLog", "Image URI" + data.getData());
                ShowImage.setImageURI(data.getData());


            }


        }

    }

    private void uploadImage()
    {
        Bitmap bitmap = ((BitmapDrawable) ShowImage.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos);
        byte[] byteArray = baos.toByteArray();
        final StorageReference mRef = mStorageRef.child(System.currentTimeMillis() + "my image");
        UploadTask up = mRef.putBytes(byteArray);
        Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return mRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                uploadUri = task.getResult();
                Toast.makeText(UploadPhotoActivity.this,"Картинка успешно загружена",Toast.LENGTH_SHORT).show();
                SaveUser();

            }
        });

    }


    private void getImage(){
        Intent Choose = new Intent();
        Choose.setType("image/*");
        Choose.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Choose,1);

    }
    public void onClickSave(View view){
        uploadImage();


    }

    public void onClickRead(View view){
        Intent i = new Intent(this, ReadActivity.class);
        startActivity(i);

    }
    private void SaveUser(){
        String id = mDataBase.push().getKey();
        String name = edName.getText().toString();
        String sec_name = edSecName.getText().toString();
        String email = edEmail.getText().toString();
        User newUser = new User(id,name,sec_name,email,uploadUri.toString());
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sec_name) && !TextUtils.isEmpty(email)) {

            if(id != null)mDataBase.child(id).setValue(newUser);

            Toast.makeText(this,"Сохранено",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Пустое поле",Toast.LENGTH_SHORT).show();
        }
    }
}