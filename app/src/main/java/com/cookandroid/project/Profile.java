package com.cookandroid.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Profile extends AppCompatActivity {
    CardView cv_clear,cv_ok;
    ImageView img;
    TextView tv_photo;
    EditText et_userid;
    String user_id;
    Uri img_uri;
    private final int GET_GALLERY_IMAGE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        cv_clear=findViewById(R.id.cv_clear);
        cv_ok=findViewById(R.id.cv_ok);
        img=findViewById(R.id.img);
        tv_photo=findViewById(R.id.tv_photo);
        et_userid=findViewById(R.id.et_userid);



        cv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_id=et_userid.getText().toString();
                if(user_id!=null){
                    try {
                        FileOutputStream outFs = openFileOutput("user",
                                Context.MODE_PRIVATE);
                        String str = user_id;
                        outFs.write(str.getBytes());
                        outFs.close();
                    } catch (IOException e) {
                    }
                }
               if(img_uri!=null){
                   try {
                       FileOutputStream outFs = openFileOutput("photo",
                               Context.MODE_PRIVATE);
                       String str = img_uri.toString();
                       outFs.write(str.getBytes());
                       outFs.close();
                   } catch (IOException e) {
                   }
               }
                setResult(RESULT_OK);
                finish();
            }
        });

        cv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();;
            }
        });




        //프로필 사진 변경 버튼
        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            img_uri = data.getData();
            img.setImageURI(img_uri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}