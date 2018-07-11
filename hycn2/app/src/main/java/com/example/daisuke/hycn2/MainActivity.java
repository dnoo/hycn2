package com.example.daisuke.hycn2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private NoguchiClass nc; //noguchiClassのインスタンス
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nc = new NoguchiClass();//noguchiClassのインスタンス生成
        tv = findViewById(R.id.text_view);
        String str = nc.onTokenRefresh();
        tv.setText(str);
    }
}
