package com.example.daisuke.hycn2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {

    //定義
    private EditText et01;
    private EditText et02;
    private EditText et03;
    private EditText et04;
    private EditText et05;
    private EditText et06;
    private EditText et07;
    //グローバル変数
    globalsClass globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //グローバル変数を取得
        globals = (globalsClass) this.getApplication();
    }





    /*
    main4et1 ~ main4et7
    */
    public void caction(View view) {//completeボタンの挙動

        //紐付け
        et01 =findViewById(R.id.main4et1);
        et02 =findViewById(R.id.main4et2);
        et03 =findViewById(R.id.main4et3);
        et04 =findViewById(R.id.main4et4);
        et05 =findViewById(R.id.main4et5);
        et06 =findViewById(R.id.main4et6);
        et07 =findViewById(R.id.main4et7);

        String temp1 = et01.getText().toString();
        String temp2 = et02.getText().toString();
        String temp3 = et03.getText().toString();
        String temp4 = et04.getText().toString();
        String temp5 = et05.getText().toString();
        String temp6 = et06.getText().toString();
        String temp7 = et07.getText().toString();

        if (temp1.equals("") == false && temp2.equals("") == false
                && temp3.equals("") == false && temp4.equals("") == false
                && temp5.equals("") == false && temp6.equals("") == false
                && temp7.equals("") == false) {
            //保存
            saveFile("testfile.txt",temp1,temp2,temp3,temp4,temp5,temp6,temp7);

            //データベースにアップロード
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference().child("user-data").child(globals.getMyUserID());
            //  アップロード用のキーとバリュー
            HashMap<String, Object> childUpdatesBasic = new HashMap<>();

            childUpdatesBasic.put("name", temp1);
            childUpdatesBasic.put("number", temp2);
            childUpdatesBasic.put("faculty", temp3);
            childUpdatesBasic.put("circle", temp4);
            childUpdatesBasic.put("birth", temp5);
            childUpdatesBasic.put("age", temp6);
            childUpdatesBasic.put("comment", temp7);

            for (Map.Entry<String , Object> myProfileMap : childUpdatesBasic.entrySet()) {
                Log.d("UpdateKeyValue",myProfileMap.getKey()+":"+myProfileMap.getValue());
            }
            //アップロード
            myRef.updateChildren(childUpdatesBasic);
            childUpdatesBasic.clear();
            //saveFile("Kimotobasic.txt",temp1,temp2,temp3,temp4,temp5,temp6,temp7);
            //saveFile3("flist.txt",temp1,temp2,temp3,temp4,temp5);

            Intent intent = new Intent(this, Main5Activity.class);
            startActivityForResult(intent, 0);
        }
    }


    // ファイルを保存
    public void saveFile(String file, String str1, String str2, String str3
            , String str4, String str5, String str6, String str7) {
        try(FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {
            fileOutputstream.write(str1.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str2.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str3.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str4.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str5.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str6.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str7.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ファイルを保存
    public void saveFile3(String file, String str1, String str2, String str3
            , String str4, String str5) {
        try(FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {
            fileOutputstream.write(str1.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str2.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str3.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str4.getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(str5.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
