package com.cookandroid.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class five extends AppCompatActivity {
    Button btn_write,btn_read;
    EditText et_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        btn_read=findViewById(R.id.btn_read);
        btn_write=findViewById(R.id.btn_write);
        et_input=findViewById(R.id.et_input);

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream outFs = openFileOutput("file.txt", Context.MODE_PRIVATE);
                    String data;
                    data=et_input.getText().toString();
                    outFs.write(data.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), "file.txt가 생성됨", Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "file.txt가 생성되지 않음", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileInputStream inFs = openFileInput("file.txt");
                    byte buf[] = new byte[30];
                    inFs.read(buf);
                    String str = new String(buf);
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    inFs.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "파일 읽기 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}