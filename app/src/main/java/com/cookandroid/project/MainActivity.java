package com.cookandroid.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView iv[] =new ImageView[12];
    CardView cv[] = new CardView[12];
    CardView cv_add_post;
    Integer i_id[] ={R.id.iv_0,R.id.iv_1,R.id.iv_2,R.id.iv_3,R.id.iv_4,R.id.iv_5,R.id.iv_6,R.id.iv_7,R.id.iv_8,R.id.iv_9,R.id.iv_10,R.id.iv_11};
    Integer c_id[]={R.id.cv_0,R.id.cv_1,R.id.cv_2,R.id.cv_3,R.id.cv_4,R.id.cv_5,R.id.cv_6,R.id.cv_7,R.id.cv_8,R.id.cv_9,R.id.cv_10,R.id.cv_11};
    static int count=0;
    String content;
    Uri selectedImageUri;
    String currentDate;
    TextView tv_count,tv_userName;
    String uri;
    int post_num;
    CardView cv_profile;
    ImageView img_photo;
    final private static String TAG = "CAMERA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "권한 설정 완료");
            }
            else {
                Log.d(TAG, "권한 설정 요청"); requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        tv_userName=findViewById(R.id.tv_userName);
        cv_add_post=findViewById(R.id.cv_add_post);
        tv_count=findViewById(R.id.tv_count);
        cv_profile=findViewById(R.id.cv_profile);
        img_photo=findViewById(R.id.img_photo);

        //위젯 연결
        for(int i=0; i<12; i++){
            iv[i]=findViewById(i_id[i]);
            cv[i]=findViewById(c_id[i]);
        }

        //기존 게시물 피드에 띄어주기
        String filename="count";
        FileInputStream inFs;
        try {
            inFs = openFileInput(filename);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            String str = (new String(txt)).trim();
            post_num=Integer.parseInt(str);
            count=post_num+1;
            tv_count.setText(count+"");
        } catch (IOException e) {

        }
        for(int i=0; i<post_num; i++){
            String files[]=new String[3];
            String rst = null;
            try {
                inFs = openFileInput(i+"file");
                byte[] txt = new byte[500];
                inFs.read(txt);
                inFs.close();
                String str = (new String(txt)).trim();
                files=str.split(",");
            } catch (IOException e) {
            }

            try {
                inFs = openFileInput(files[1]);
                byte[] txt = new byte[500];
                inFs.read(txt);
                inFs.close();
                rst = (new String(txt)).trim();
            } catch (IOException e) {
            }
            Uri img_uri= Uri.parse(rst);
            iv[i].setImageURI(img_uri);

        }

        int i;
        for(i=0; i<12; i++){
            final int index;
            index=i;

            cv[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String filenames[]= new String[3];
                    if(index<count){
                        String filename = index+"file";
                        FileInputStream inFs;
                        try {
                            inFs = openFileInput(filename);
                            byte[] txt = new byte[500];
                            inFs.read(txt);
                            inFs.close();
                            String str = (new String(txt)).trim();
                            filenames=str.split(",");
                        } catch (IOException e) {

                        }
                        String rst[] = new String[3];
                        for(int i=0; i<3; i++){
                            try {
                                inFs = openFileInput(filenames[i]);
                                byte[] txt = new byte[500];
                                inFs.read(txt);
                                inFs.close();
                                rst[i] = (new String(txt)).trim();
                            } catch (IOException e) {

                            }
                        }


                        Intent intent = new Intent(MainActivity.this, View_Post.class);
                        intent.putExtra("date",rst[0]);
                        Uri u=Uri.parse(rst[1]);
                        intent.putExtra("Uri",u);
                        intent.putExtra("content",rst[2]);
                        startActivity(intent);



                    }else{
                        Toast.makeText(getApplicationContext(),"기록된 게시물이 없습니다",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        //게시물 추가 버튼
        cv_add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Add_Post.class);
                startActivityForResult(intent,0);
            }
        });
        //프로필 변경 버튼
        cv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Profile.class);
                startActivityForResult(intent,1);
            }
        });

    }
    //추가하는 작업
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                // MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                case 0:
                    currentDate=data.getStringExtra("date");
                    selectedImageUri=data.getParcelableExtra("Uri");
                    content=data.getStringExtra("content");
                    uri=selectedImageUri.toString();

                    String file_content[]={currentDate,uri,content};

                    String filename[]= new String[3];
                    filename[0]=currentDate+count+"date";
                    filename[1]=currentDate+count+"Uri";
                    filename[2]=currentDate+count+"content";
                    //파일에 쓰기
                    for(int i=0; i<3; i++){
                        try {
                            FileOutputStream outFs = openFileOutput(filename[i],
                                    Context.MODE_PRIVATE);
                            String str = file_content[i];
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                    }

                    try {
                        FileOutputStream outFs = openFileOutput(count+"file",
                                Context.MODE_PRIVATE);
                        String str = filename[0]+","+filename[1]+","+filename[2];
                        outFs.write(str.getBytes());
                        outFs.close();
                        Toast.makeText(getApplicationContext(),
                                "게시물이 저장됨", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                    }

                    try {
                        FileOutputStream outFs = openFileOutput("count",
                                Context.MODE_PRIVATE);
                        String str =Integer.toString(count);
                        outFs.write(str.getBytes());
                        outFs.close();
                        Toast.makeText(getApplicationContext(),
                                "게시물이 저장됨", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                    }

                    iv[count].setImageURI(selectedImageUri);
                    count++;
                    tv_count.setText(count+"");
                    break;

            }
        }
    }
    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED ) {
            Log.d(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

}
