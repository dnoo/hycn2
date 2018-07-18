package com.example.daisuke.hycn2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


// maintv1 ~ maintv7

public class MainActivity extends AppCompatActivity {


    //定義
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //紐付け
        tv1 = findViewById(R.id.maintv1);
        tv2 = findViewById(R.id.maintv2);
        tv3 = findViewById(R.id.maintv3);
        tv4 = findViewById(R.id.maintv4);
        tv5 = findViewById(R.id.maintv5);
        tv6 = findViewById(R.id.maintv6);
        tv7 = findViewById(R.id.maintv7);


        //ファイル初期読み込み
        String[] temp = readFile("testfile.txt");
        if(temp != null) {
            tv1.setText(temp[0]);
            tv2.setText(temp[1]);
            tv3.setText(temp[2]);
            tv4.setText(temp[3]);
            tv5.setText(temp[4]);
            tv6.setText(temp[5]);
            tv7.setText(temp[6]);
        }
        //ファイル初期読み込み


        /*変更前3
        //アクション登録
        Bt01.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //保存箇所
                String temp = et01.getText().toString();
                saveFile("testfile.txt",temp);

                //読み込み箇所
                String temp2 = readFile("testfile.txt");
                if(temp2 != null) {
                    ev01.setText(temp2);
                } else {
                    ev01.setText("error");
                }

            }
        });
        */
    }

    //画面4への遷移
    public void changescreen01(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, 0);
    }

    //画面3への遷移
    public void changescreen02(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main3Activity.class);
        startActivityForResult(intent, 0);
    }

    //個人情報登録画面へ遷移
    public void rnclick(View view) {
        Intent intent = new Intent(this, Main4Activity.class);
        startActivityForResult(intent, 0);
    }



    // ファイルの保存
    public void saveFile(String file, String str) {
        try(FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {

            fileOutputstream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // ファイルを読み出し
    public String[] readFile(String file){

        String[] text = {null, null, null, null, null, null, null};

        try(FileInputStream fileInputStream = openFileInput(file);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(fileInputStream,"UTF-8"));
            ) {

            int i = 0;
            String lineBuffer;
            while ((lineBuffer = reader.readLine()) != null) {
                text[i] = lineBuffer;
                Log.d(text[i], "readFile: ");
                Log.d(""+i+"", "number: ");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }




}




/*
///////////////
/////////
////ここから下はメモ
//////////////
////////
////


    1
    //ファイルの保存(original)
    public void saveFile(String file, String str) {
        try(FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {

            fileOutputstream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    2
    // ファイルを読み出し(original)
    public String readFile(String file){
        String text = null;

        try(FileInputStream fileInputStream = openFileInput(file);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(fileInputStream,"UTF-8"));
            ) {

            String lineBuffer;
            while ((lineBuffer = reader.readLine()) != null) {
                text = lineBuffer;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }


    3
    //OnClick登録
    public void testclick(View view) {
        String temp = et01.getText().toString();
        ev01.setText(temp);
    }


    4
    //定義(class MainActivityとonCreateの間)
    private EditText et01;
    private TextView ev01;


    5
    //紐付け(onCreateの直後)
    et01 =findViewById(R.id.my02);
    ev01 = findViewById(R.id.my01);
    Button Bt01 = findViewById(R.id.my03);


    6
    //ファイル初期読み込み1(onCreate内)
    String temp3 = readFile("testfile.txt");
    if(temp3 != null) {
        ev01.setText(temp3);
    }
    //ファイル読み込み1


    7
    //アクション登録
    Bt01.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            //保存箇所
            String temp = et01.getText().toString();
            saveFile("testfile.txt",temp);

            //読み込み箇所
            String temp2 = readFile("testfile.txt");
            if(temp2 != null) {
                ev01.setText(temp2);
            } else {
                ev01.setText("error");
            }

        }
    });




///////////////
/////////
////ここから上はメモ
//////////////
////////
////
*/




