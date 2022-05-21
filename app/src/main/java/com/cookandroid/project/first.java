package com.cookandroid.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;
import android.widget.Switch;
import android.widget.TextView;

public class first extends AppCompatActivity {
    Button btn1, btn2, btn3,btn4,btn_name,btn_major,btn_site,btn_res,btn_pro,btn_write,btn_diary,btn_draw;
    TextView tv,tv1;
    SlidingDrawer sd;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setTitle("메인엑티비티");
        btn1=(Button)findViewById(R.id.btn_cal);
        btn2=(Button)findViewById(R.id.btn_car);
        btn3=(Button)findViewById(R.id.btn_main);
        btn_name=findViewById(R.id.btn_name);
        btn_major=findViewById(R.id.btn_major);
        btn_site=findViewById(R.id.btn_site);
        btn4=findViewById(R.id.btn_ex);
        btn_res=findViewById(R.id.btn_res);
        tv=(TextView)findViewById(R.id.tv);
        tv1=(TextView)findViewById(R.id.tv1);
        sw=findViewById(R.id.sw);
        sd=findViewById(R.id.slidingDrawer1);
        btn_pro=findViewById(R.id.btn_project);
        btn_write=findViewById(R.id.btn_write);
        btn_diary=findViewById(R.id.btn_diary);
        btn_draw=findViewById(R.id.btn_draw);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setVisibility(View.VISIBLE);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sd.animateClose();
            }
        });
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sw.isChecked()==true){
                    tv.setVisibility(View.VISIBLE);
                    btn_major.setVisibility(View.VISIBLE);
                    btn_name.setVisibility(View.VISIBLE);
                    btn_site.setVisibility(View.VISIBLE);
                }
                else{
                    tv1.setVisibility(View.GONE);
                    tv.setVisibility(View.GONE);
                    btn_major.setVisibility(View.GONE);
                    btn_name.setVisibility(View.GONE);
                    btn_site.setVisibility(View.GONE);
                }
            }
        });

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FourthActivity.class);
                startActivity(intent);
            }
        });
        btn_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),five.class);
                startActivity(intent);
            }
        });

        btn_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),six.class);
                startActivity(intent);
            }
        });

        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),seven.class);
                startActivity(intent);
            }
        });
    }
}