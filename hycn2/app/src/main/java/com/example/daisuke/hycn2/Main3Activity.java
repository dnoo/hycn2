package com.example.daisuke.hycn2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void changescreen00(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main5Activity.class);
        startActivityForResult(intent, 0);
    }

    public void changescreen01(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, 0);
    }
}
