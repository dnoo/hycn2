package com.example.daisuke.hycn2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {


    private Main2Activity inner_delegate = this;
    //グローバル変数
    globalsClass globals;

    //private TextView tv1;

    //試し
    //ScrollView scrollView;
    //LinearLayout linearLayout;

    //private float dpdp = getResources().getDisplayMetrics().density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);//ビューを配置
        //グローバル変数を取得
        globals = (globalsClass) this.getApplication();

        //全体
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //タイトル
        LinearLayout layout2 = new LinearLayout(this);
        layout2.setOrientation(LinearLayout.HORIZONTAL);

        //スクロールビュー
        ScrollView scrollView = new ScrollView(this);

        //リストボタン
        LinearLayout layout3 = new LinearLayout(this);
        layout3.setOrientation(LinearLayout.VERTICAL);

        //下ボタン
        LinearLayout layout4 = new LinearLayout(this);
        layout4.setOrientation(LinearLayout.HORIZONTAL);

        ////////////////

        //全体レイアウトの設定
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        layout2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        float dp1 = getResources().getDisplayMetrics().density;
        int dp2 = (int)(410 * dp1);//default:490, 実機:450, 野口:410
        scrollView.setLayoutParams(new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                dp2));

        layout3.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        layout4.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        ////////////////

        //layout2について
        TextView tv1 = new TextView(this);
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,37);
        LinearLayout.LayoutParams tv1param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        tv1.setLayoutParams(tv1param);
        tv1.setText("友達画面");
        tv1.setGravity(Gravity.CENTER);
        layout2.addView(tv1);

        //layout4について
        layout4.setGravity(Gravity.CENTER_HORIZONTAL);

        Button button1 = new Button(this);
        Button button2 = new Button(this);
        Button button3 = new Button(this);

        button1.setText("個人");
        button2.setText("友達");
        button3.setText("QR");

        LinearLayout.LayoutParams button1param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams button2param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams button3param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        button1param.setMargins((int)(4 * dp1),(int)(0 * dp1),(int)(28 * dp1),(int)(8 * dp1));
        button2param.setMargins((int)(24 * dp1),(int)(0 * dp1),(int)(24 * dp1),(int)(8 * dp1));
        button3param.setMargins((int)(28 * dp1),(int)(0 * dp1),(int)(4 * dp1),(int)(8 * dp1));

        button1.setLayoutParams(button1param);
        button2.setLayoutParams(button2param);
        button3.setLayoutParams(button3param);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //ここに記述
                Intent intent = new Intent(inner_delegate, Main5Activity.class);
                startActivityForResult(intent, 0);


            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //ここに記述
                Intent intent = new Intent(inner_delegate, Main3Activity.class);
                startActivityForResult(intent, 0);

            }
        });


        //button1.margin

        layout4.addView(button1);
        layout4.addView(button2);
        layout4.addView(button3);


        ////////////////
        globals.mapMyFriends.size();
        Button fbutton[] = new Button[globals.mapMyFriends.size()];
        int i=0;
        //大事なスクロールのやつ
        for (Map.Entry<String , String> myFriendsMap : globals.mapMyFriends.entrySet()) {
                   Log.d("map","(key)"+myFriendsMap.getKey()+"(Value)"+myFriendsMap.getValue());

            fbutton[i] = new Button(this);
            fbutton[i].setText(myFriendsMap.getValue());
            layout3.addView(fbutton[i]);
            final String key = myFriendsMap.getKey();
            final String value = myFriendsMap.getValue();

            fbutton[i].setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    //ここに記述
                    globals.setMyFriendID(key);
                    //保存
                    saveFile("target.txt",value);

                    //友達のデータ読み込み
                    Toast.makeText(Main2Activity.this, value+"さんの情報を読み込んでいます..." ,
                            Toast.LENGTH_LONG).show();
                    globals.clearFriendProfile();
                    globals.getFriendProfile();
                    //アクテビティ遷移
                    Intent intent = new Intent(inner_delegate, Main7Activity.class);
                    startActivityForResult(intent, 0);
                }
            });
        }
        /*
        final List list01 = readFile("flist.txt");
        Button fbutton[] = new Button[list01.size()];
        //大事なスクロールのやつ
        for (int i = 0; i<list01.size(); i++){
            fbutton[i] = new Button(this);
            fbutton[i].setText((String)list01.get(i));
            layout3.addView(fbutton[i]);
            final int ii = i;

            fbutton[i].setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    //ここに記述

                    //保存
                    saveFile("target.txt",(String)(list01.get(ii)));

                    Intent intent = new Intent(inner_delegate, Main7Activity.class);
                    startActivityForResult(intent, 0);
                }
            });
        }
    */
        ////////////////


        //layoutのヒエラルキー
        scrollView.addView(layout3);
        layout.addView(layout2);
        layout.addView(scrollView);
        layout.addView(layout4);

        //描写設定
        setContentView(layout);

    }




    public void changescreen00(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main5Activity.class);
        startActivityForResult(intent, 0);
    }

    public void changescreen02(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main3Activity.class);
        startActivityForResult(intent, 0);
    }


    // ファイルを読み出し
    public List<String> readFile(String file){

        List<String> list1 = new ArrayList<>();

        try(FileInputStream fileInputStream = openFileInput(file);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(fileInputStream,"UTF-8"));
        ) {

            String lineBuffer;
            while ((lineBuffer = reader.readLine()) != null) {
                list1.add(lineBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list1;
    }

    // ファイルを保存
    public void saveFile(String file, String str1) {
        try(FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {
            fileOutputstream.write(str1.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
