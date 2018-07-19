package com.example.daisuke.hycn2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main6Activity extends AppCompatActivity {

    //定義
    private EditText et01;
    private EditText et02;
    private EditText et03;
    private EditText et04;
    private EditText et05;

    private Spinner sp01;
    private Spinner sp02;
    private Spinner sp03;
    private Spinner sp04;
    private Spinner sp05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }


    /*
    main4et1 ~ main4et7
    */
    public void caction2(View view) {//completeボタンの挙動

        //紐付け
        et01 =findViewById(R.id.main6et1);
        et02 =findViewById(R.id.main6et2);
        et03 =findViewById(R.id.main6et3);
        et04 =findViewById(R.id.main6et4);
        et05 =findViewById(R.id.main6et5);

        sp01 = findViewById(R.id.spinner1);
        sp02 = findViewById(R.id.spinner2);
        sp03 = findViewById(R.id.spinner3);
        sp04 = findViewById(R.id.spinner4);
        sp05 = findViewById(R.id.spinner5);




        //文字を取得
        String temp1 = et01.getText().toString();
        String temp2 = et02.getText().toString();
        String temp3 = et03.getText().toString();
        String temp4 = et04.getText().toString();
        String temp5 = et05.getText().toString();


        if (temp1.equals("") == false && temp2.equals("") == false
                && temp3.equals("") == false && temp4.equals("") == false
                && temp5.equals("") == false) {

            //保存
            // コメント情報保存
            saveFile("testfile3.txt",temp1,temp2,temp3,temp4,temp5);
            //スピナー情報保存
            saveFile2("testfile2.txt",sp01,sp02,sp03,sp04,sp05);

            // コメント情報保存
            //saveFile("Kimotocomment.txt",temp1,temp2,temp3,temp4,temp5);
            //スピナー情報保存
            //saveFile2("Kimotospinner.txt",sp01,sp02,sp03,sp04,sp05);

            Intent intent = new Intent(this, Main5Activity.class);
            startActivityForResult(intent, 0);
        }
    }


    // ファイルを保存
    public void saveFile(String file, String str1, String str2, String str3
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

    // ファイルを保存
    public void saveFile2(String file, Spinner spin1, Spinner spin2, Spinner spin3
            , Spinner spin4, Spinner spin5) {
        try(FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {
            fileOutputstream.write(((String)spin1.getSelectedItem()).getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(((String)spin2.getSelectedItem()).getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(((String)spin3.getSelectedItem()).getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(((String)spin4.getSelectedItem()).getBytes());
            fileOutputstream.write(13);
            fileOutputstream.write(((String)spin5.getSelectedItem()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
