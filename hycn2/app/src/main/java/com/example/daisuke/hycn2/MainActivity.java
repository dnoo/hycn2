package com.example.daisuke.hycn2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    /*　margeにつき除去
    private NoguchiClass nc; //noguchiClassのインスタンス
    private TextView tv;
    */
    private operateDatabase od;//operateDatabaseのインスタンス
    private TextView tv;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*margeにつき除去
        nc = new NoguchiClass();//noguchiClassのインスタンス生成
        tv = findViewById(R.id.text_view);
        String str = nc.onTokenRefresh();
        tv.setText(str);
        */
        od = new operateDatabase();//operateDatabaseの作成
        od.testWriteMas2Database();
        tv = findViewById(R.id.textView3);
        od.testReadMesFromDatabase(tv);
    }




    public void changescreen01(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, 0);
    }

    public void changescreen02(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main3Activity.class);
        startActivityForResult(intent, 0);
    }
}




////////original codes////////
/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changelabel(View view) {
        TextView tv = findViewById(R.id.mytext1);
        tv.setText("Changed!");
    }
}*/
///////////////////////////////