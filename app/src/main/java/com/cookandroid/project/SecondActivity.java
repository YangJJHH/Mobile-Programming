package com.cookandroid.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    EditText et1,et2;
    TextView result;
    Button btn_Add, btn_Sub, btn_Mul, btn_Div,btn_back;
    Cal cal = new Cal(); //계산기 객체 생성
    Integer num1,num2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTitle("두번째 엑티비티");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        et1=(EditText)findViewById(R.id.Edit1);
        et2=(EditText)findViewById(R.id.Edit2);
        result=(TextView) findViewById(R.id.textview);
        btn_Add=(Button)findViewById(R.id.button);
        btn_Sub=(Button)findViewById(R.id.button2);
        btn_Mul=(Button)findViewById(R.id.button3);
        btn_Div=(Button)findViewById(R.id.button4);
        btn_back=(Button)findViewById(R.id.btn_back);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=Integer.parseInt(et1.getText().toString());
                num2=Integer.parseInt(et2.getText().toString());
                cal.addCal(num1,num2);
                result.setText(cal.getResult());
            }
        });

        btn_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=Integer.parseInt(et1.getText().toString());
                num2=Integer.parseInt(et2.getText().toString());
                cal.subCal(num1,num2);
                result.setText(cal.getResult());
            }
        });

        btn_Mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=Integer.parseInt(et1.getText().toString());
                num2=Integer.parseInt(et2.getText().toString());
                cal.mulCal(num1,num2);
                result.setText(cal.getResult());
            }
        });

        btn_Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=Integer.parseInt(et1.getText().toString());
                num2=Integer.parseInt(et2.getText().toString());
                cal.divCal(num1,num2);
                result.setText(cal.getResult());
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

