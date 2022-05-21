package com.cookandroid.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Add_Post extends AppCompatActivity {
    private final int GET_GALLERY_IMAGE = 200;
    TextView tv_date;
    Button btn_ok;
    CardView cv_add_photo;
    EditText et_content;
    ImageView img;
    String content;
    Uri selectedImageUri;
    String currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__post);
        tv_date=findViewById(R.id.tv_date);
        btn_ok=findViewById(R.id.btn_ok);
        cv_add_photo=findViewById(R.id.cv_add_photo);
        et_content=findViewById(R.id.et_content);
        img=findViewById(R.id.img);
        //오늘 날짜
        Date today= new Date();
        currentDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
        tv_date.setText(currentDate);

        //사진 추가하기
        cv_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
        //게시물 등록
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content=et_content.getText().toString();
                Intent intent_res = new Intent();
                intent_res.putExtra("date", currentDate);
                intent_res.putExtra("Uri", selectedImageUri);
                intent_res.putExtra("content", content);
                setResult(RESULT_OK,intent_res);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            img.setImageURI(selectedImageUri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}