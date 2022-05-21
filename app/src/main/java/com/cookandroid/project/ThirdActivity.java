package com.cookandroid.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
        String tmp_str;
        Integer tmp_int;
        Button b1,b2,b3,btn_back;
        TextView t1,t2;
        EditText e1,e2;
        //Car 클래스
        public class Car{
            String color;
            int speed;
            Car(String color, int speed){
                this.color=color;
                this.speed=speed;
            }

            void upSpeed(int value) {
                if (speed + value >= 200)
                    speed = 200;
                else
                    speed = speed + value;
            }
            void downSpeed(int value) {
                if (speed - value <= 0)
                    speed = 0;
                else
                    speed = speed - value;
            }
            int getSpeed() {
                return speed;
            }
            String getColor() {
                return color;
            }
        }
        Car c1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        setTitle("세번째 엑티비티");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        //위젯 연결
        btn_back=(Button)findViewById(R.id.btn_back);
        b1 = (Button) findViewById(R.id.btn_create); //객체생성버튼
        b2 = (Button) findViewById(R.id.btn1); //가속버튼
        b3 = (Button) findViewById(R.id.btn2); //감속버튼
        t1 = (TextView) findViewById(R.id.txv1); //결과창1
        t2 = (TextView) findViewById(R.id.txv2); //결과창2
        e1=(EditText)findViewById(R.id.ed_color);
        e2=(EditText)findViewById(R.id.ed_speed);

        //객체 생성 버튼이 눌렸을때 객체 생성
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tmp_str=e1.getText().toString();
                tmp_int=Integer.parseInt(e2.getText().toString());
                c1=new Car(tmp_str,tmp_int);
                t1.setText("객체생성\n차량1의 색상은"+c1.getColor()+" 이며, 속도는"+c1.speed+"입니다.");
                t2.setText("객체생성\n차량1의 색상은"+c1.getColor()+" 이며, 속도는"+c1.speed+"입니다.");

            }
        });
        // 가속 버튼
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.upSpeed(50);
                t1.setText("가속버튼 클릭\n차량1의 색상은"+c1.getColor()+" 이며, 속도는"+c1.speed+"입니다.");
            }
        });
        //감속 버튼
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.downSpeed(50);
                t2.setText("감속버튼 클릭\n차량1의 색상은"+c1.getColor()+" 이며, 속도는"+c1.speed+"입니다.");
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
