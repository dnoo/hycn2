package com.example.daisuke.hycn2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main7Activity extends AppCompatActivity {

    //定義
    // basic
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;

    //
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
        //グローバル変数を取得
        globals = (globalsClass) this.getApplication();

        if(!globals.mapFriendsProfile.isEmpty()){
              Log.d("mapFriendsProfile",globals.mapMyProfile.toString());
            //mapの確認
            for (Map.Entry<String , String> myProfileMap : globals.mapFriendsProfile.entrySet()) {
                       Log.d("mapFriendsProfile",myProfileMap.getKey()+":"+myProfileMap.getValue());
            }
               Log.d("checkUserID",globals.getMyFriendID());
        }else {
               Log.d("mapFriendsProfile", "Error matMyProfile is Empty");
            // Go to MainActivity
            startActivity(new Intent(Main7Activity.this, Main7Activity.class));
            finish();
            return ;
        }

        setContentView(R.layout.activity_main7);

        //ファイル読み込み部分
        String target = readFile3("target.txt");

        //target 人の名前
        String basicfile = target.concat("basic.txt");
        String spinnerfile = target.concat("spinner.txt");
        String commentfile = target.concat("comment.txt");



        //紐付け部分
        tv1 = findViewById(R.id.main7tv1);
        tv2 = findViewById(R.id.main7tv2);
        tv3 = findViewById(R.id.main7tv3);
        tv4 = findViewById(R.id.main7tv4);
        tv5 = findViewById(R.id.main7tv5);
        tv6 = findViewById(R.id.main7tv6);
        tv7 = findViewById(R.id.main7tv7);

        tv11 = findViewById(R.id.textView721);//スピナー情報
        tv12 = findViewById(R.id.textView722);
        tv13 = findViewById(R.id.textView723);
        tv14 = findViewById(R.id.textView724);
        tv15 = findViewById(R.id.textView725);

        tv21 = findViewById(R.id.main72tv1);
        tv22 = findViewById(R.id.main72tv2);
        tv23 = findViewById(R.id.main72tv3);
        tv24 = findViewById(R.id.main72tv4);
        tv25 = findViewById(R.id.main72tv5);

        tv1.setText(globals.mapFriendsProfile.get("name"));
        tv2.setText(globals.mapFriendsProfile.get("number"));
        tv3.setText(globals.mapFriendsProfile.get("faculty"));
        tv4.setText(globals.mapFriendsProfile.get("circle"));
        tv5.setText(globals.mapFriendsProfile.get("birth"));
        tv6.setText(globals.mapFriendsProfile.get("age"));
        tv7.setText(globals.mapFriendsProfile.get("comment"));

        //趣味取り出し＆マッチング
        try{
            JSONObject JSONHobbyMine   = new JSONObject(globals.mapMyProfile.get("hobby").toString());
            JSONObject JSONHobbyFriend = new JSONObject(globals.mapFriendsProfile.get("hobby").toString());
            Map <String,String> MyHobby = new LinkedHashMap<String, String>();
            Map <String,String> FriendHobby = new LinkedHashMap<String, String>();
            // Log.d("JSONHobby",JSONHobby.toString(2));
            Iterator<String> keys = JSONHobbyMine.keys();
            while(keys.hasNext()){
                String key = keys.next();
                String value = JSONHobbyMine.getString(key);
                MyHobby.put(key,value);
                //    Log.d("Main5Activity", "キー名 = " + key + "   :   値 = " + value);
            }
            keys = JSONHobbyFriend.keys();
            while(keys.hasNext()){
                String key = keys.next();
                String value = JSONHobbyFriend.getString(key);
                FriendHobby.put(key,value);
                //    Log.d("Main5Activity", "キー名 = " + key + "   :   値 = " + value);
            }
            int count = 0;
            for (Map.Entry<String , String> friendHobbyMap : FriendHobby.entrySet()) {
                String key = friendHobbyMap.getKey();
                String value = friendHobbyMap.getValue();
                Log.d("map","(key)"+key+"(Value)"+value);
                if(MyHobby.containsKey(key)) {
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
            }


        } catch (JSONException e){

        }




        /*
        //ファイル初期読み込み
        String[] temp = readFile(basicfile);
        String[] temp2 = readFile2(spinnerfile);//スピナー情報
        String[] temp3 = readFile2(commentfile);//コメント情報
        String[] mytemp2 = readFile2("testfile2.txt");//自分のスピナー情報
        if(temp != null) {
            tv1.setText(temp[0]);
            tv2.setText(temp[1]);
            tv3.setText(temp[2]);
            tv4.setText(temp[3]);
            tv5.setText(temp[4]);
            tv6.setText(temp[5]);
            tv7.setText(temp[6]);
        }

        Log.d(temp2[0], "number:1 ");
        Log.d(temp2[1], "number:2 ");
        Log.d(temp2[2], "number:3 ");
        Log.d(temp2[3], "number:4 ");
        Log.d(temp2[4], "number:5 ");

        Log.d(mytemp2[0], "number:11 ");
        Log.d(mytemp2[1], "number:12 ");
        Log.d(mytemp2[2], "number:13 ");
        Log.d(mytemp2[3], "number:14 ");
        Log.d(mytemp2[4], "number:15 ");
        */
        /*
        //ここからマッチング実装
        if(temp2 != null) {
            if(temp2[0].equals(mytemp2[0]) || temp2[0].equals(mytemp2[1]) ||temp2[0].equals(mytemp2[2]) ||temp2[0].equals(mytemp2[3])
                    ||temp2[0].equals(mytemp2[4])) {
                tv11.setText(temp2[0]);
                Log.d("A", "number");
            }

            if(temp2[1].equals(mytemp2[0]) || temp2[1].equals(mytemp2[1])
                    ||temp2[1].equals(mytemp2[2]) ||temp2[1].equals(mytemp2[3])
                    ||temp2[1].equals(mytemp2[4])) {
                tv12.setText(temp2[1]);
                Log.d("B", "number");
            }

            if(temp2[2].equals(mytemp2[0]) || temp2[2].equals(mytemp2[1])
                    ||temp2[2].equals(mytemp2[2]) ||temp2[2].equals(mytemp2[3])
                    ||temp2[2].equals(mytemp2[4])) {
                tv13.setText(temp2[2]);
                Log.d("C", "number");
            }

            if(temp2[3].equals(mytemp2[0])|| temp2[3].equals(mytemp2[1])
                    ||temp2[3].equals(mytemp2[2]) ||temp2[3].equals(mytemp2[3])
                    ||temp2[3].equals(mytemp2[4])) {
                tv14.setText(temp2[3]);
                Log.d("D", "number");
            }

            if(temp2[4].equals(mytemp2[0]) || temp2[4].equals(mytemp2[1])
                    ||temp2[4].equals(mytemp2[2]) ||temp2[4].equals(mytemp2[3])
                    ||temp2[4].equals(mytemp2[4])) {
                tv15.setText(temp2[4]);
                Log.d("E", "number");
            }
        }

        if(temp3 != null) {

            if(temp2[0].equals(mytemp2[0]) || temp2[0].equals(mytemp2[1])
                    ||temp2[0].equals(mytemp2[2]) ||temp2[0].equals(mytemp2[3])
                    ||temp2[0].equals(mytemp2[4])) {
                tv21.setText(temp3[0]);
            }

            if(temp2[1].equals(mytemp2[0]) || temp2[1].equals(mytemp2[1])
                    ||temp2[1].equals(mytemp2[2]) ||temp2[1].equals(mytemp2[3])
                    ||temp2[1].equals(mytemp2[4])) {
                tv22.setText(temp3[1]);
            }

            if(temp2[2].equals(mytemp2[0]) || temp2[2].equals(mytemp2[1])
                    ||temp2[2].equals(mytemp2[2]) ||temp2[2].equals(mytemp2[3])
                    ||temp2[2].equals(mytemp2[4])) {
                tv23.setText(temp3[2]);
            }

            if(temp2[3].equals(mytemp2[0]) || temp2[3].equals(mytemp2[1])
                    ||temp2[3].equals(mytemp2[2]) ||temp2[3].equals(mytemp2[3])
                    ||temp2[3].equals(mytemp2[4])) {
                tv24.setText(temp3[3]);
            }

            if(temp2[4].equals(mytemp2[0]) || temp2[4].equals(mytemp2[1])
                    ||temp2[4].equals(mytemp2[2]) ||temp2[4].equals(mytemp2[3])
                    ||temp2[4].equals(mytemp2[4])) {
                tv25.setText(temp3[4]);
            }
        }
        //ファイル初期読み込み
        */






    }

    //画面への遷移
    public void changescreen00(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main5Activity.class);
        startActivityForResult(intent, 0);
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

    // ファイルを読み出し(ターゲット name)
    public String readFile3(String file){

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
