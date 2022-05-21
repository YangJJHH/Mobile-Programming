package com.cookandroid.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class View_Post extends AppCompatActivity {
    TextView tv_date,tv_content;
    ImageView img;
    String content;
    Uri selectedImageUri;
    String currentDate;
    String uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__post);
        tv_date=findViewById(R.id.tv_date);
        img=findViewById(R.id.img);
        tv_content=findViewById(R.id.tv_content);

        Intent intent_r=getIntent();
        if(intent_r!=null) {
            currentDate = intent_r.getStringExtra("date");
            selectedImageUri= intent_r.getParcelableExtra("Uri");
            content = intent_r.getStringExtra("content");
        }
        tv_date.setText(currentDate);
        img.setImageURI(selectedImageUri);
        tv_content.setText(content);
    }
}