package com.example.daisuke.hycn2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

// main5tv1 ~ main5tv7

public class Main5Activity extends AppCompatActivity {

    //定義
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;

    private TextView tv11;
    private TextView tv12;
    private TextView tv13;
    private TextView tv14;
    private TextView tv15;

    private TextView tv21;
    private TextView tv22;
    private TextView tv23;
    private TextView tv24;
    private TextView tv25;

    //グローバル変数
    globalsClass globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //userProfile読み込みここから
        Log.d("userProfile読み込み","読み込み開始");
        //グローバル変数を取得
        globals = (globalsClass) this.getApplication();
        if(!globals.mapMyProfile.isEmpty()){
            Log.d("myProfileMap",globals.mapMyProfile.toString());
            //mapの確認
            for (Map.Entry<String , String> myProfileMap : globals.mapMyProfile.entrySet()) {
                Log.d("myProfileMap",myProfileMap.getKey()+":"+myProfileMap.getValue());
            }
        }else {
            Log.d("myProfileMap", "Error matMyProfile is Empty");
            // Go to MainActivity
            startActivity(new Intent(Main5Activity.this, Main5Activity.class));
            finish();
            return ;
        }
        setContentView(R.layout.activity_main5);
        //userProfile読み込みここまで

        //紐付け
        //基本情報
        tv1 = findViewById(R.id.main5tv1);
        tv2 = findViewById(R.id.main5tv2);
        tv3 = findViewById(R.id.main5tv3);
        tv4 = findViewById(R.id.main5tv4);
        tv5 = findViewById(R.id.main5tv5);
        tv6 = findViewById(R.id.main5tv6);
        tv7 = findViewById(R.id.main5tv7);

        //趣味のジャンル
        tv11 = findViewById(R.id.textView521);
        tv12 = findViewById(R.id.textView522);
        tv13 = findViewById(R.id.textView523);
        tv14 = findViewById(R.id.textView524);
        tv15 = findViewById(R.id.textView525);
        //趣味のコメント
        tv21 = findViewById(R.id.main52tv1);
        tv22 = findViewById(R.id.main52tv2);
        tv23 = findViewById(R.id.main52tv3);
        tv24 = findViewById(R.id.main52tv4);
        tv25 = findViewById(R.id.main52tv5);

        tv1.setText(globals.mapMyProfile.get("name"));
        tv2.setText(globals.mapMyProfile.get("number"));
        tv3.setText(globals.mapMyProfile.get("faculty"));
        tv4.setText(globals.mapMyProfile.get("circle"));
        tv5.setText(globals.mapMyProfile.get("birth"));
        tv6.setText(globals.mapMyProfile.get("age"));
        tv7.setText(globals.mapMyProfile.get("comment"));

        //趣味取り出し
        try{
            JSONObject JSONHobby = new JSONObject(globals.mapMyProfile.get("hobby").toString());
            Log.d("JSONHobby",JSONHobby.toString(2));
            Iterator<String> keys = JSONHobby.keys();
            int count = 0;
            while(keys.hasNext()){
                String key = keys.next();
                String value = JSONHobby.getString(key);
                Log.d("Main5Activity", "キー名 = " + key + "   :   値 = " + value);
                switch(count){
                    case 0:
                        tv11.setText(key);
                        tv21.setText(value);
                        break;
                    case 1:
                        tv12.setText(key);
                        tv22.setText(value);
                        break;
                    case 2:
                        tv13.setText(key);
                        tv23.setText(value);
                        break;
                    case 3:
                        tv14.setText(key);
                        tv24.setText(value);
                        break;
                    case 4:
                        tv15.setText(key);
                        tv25.setText(value);
                        break;
                    default :
                        break;
                }
                count++;
            }
        } catch (JSONException e){

        }




/*
        //ファイル初期読み込み
        String[] temp = readFile("testfile.txt");
        String[] temp2 = readFile2("testfile2.txt");//スピナー情報
        String[] temp3 = readFile2("testfile3.txt");//コメント情報
        if(temp != null) {
            Log.d("tv1",temp[0]);
            tv1.setText(temp[0]);
            tv2.setText(temp[1]);
            tv3.setText(temp[2]);
            tv4.setText(temp[3]);
            tv5.setText(temp[4]);
            tv6.setText(temp[5]);
            tv7.setText(temp[6]);
        }

        if(temp2 != null) {
            Log.d("tv11",temp2[0]);
            tv11.setText(temp2[0]);
            tv12.setText(temp2[1]);
            tv13.setText(temp2[2]);
            tv14.setText(temp2[3]);
            tv15.setText(temp2[4]);
        }

        if(temp3 != null) {
            Log.d("tv21",temp3[0]);
            tv21.setText(temp3[0]);
            tv22.setText(temp3[1]);
            tv23.setText(temp3[2]);
            tv24.setText(temp3[3]);
            tv25.setText(temp3[4]);
        }
        //ファイル初期読み込み
*/

    }


    @Override
    public void onStart(){
        super.onStart();

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

    //個人情報登録画面へ遷移
    public void hobclick(View view) {
        Intent intent = new Intent(this, Main6Activity.class);
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

    // ファイルを読み出し
    public String[] readFile2(String file){

        String[] text = {null, null, null, null, null};

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
