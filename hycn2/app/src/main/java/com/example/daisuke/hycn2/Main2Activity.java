package com.example.daisuke.hycn2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {


    //private TextView tv1;

    //試し
    ScrollView scrollView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);//ビューを配置


        ///試し
        scrollView =new ScrollView(this);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i<20; i++){
            Button button = new Button(this);
            button.setText("Button"+(i+1));
            linearLayout.addView(button);
        }

        scrollView.addView(linearLayout);

setContentView(scrollView);
        ///



        /*
        // リニアレイアウトの設定
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        //レイアウト中央寄せ
        //layout.setGravity(Gravity.CENTER);

        setContentView(layout);//ビューを配置


        // dp単位を取得
        float dp = getResources().getDisplayMetrics().density;
        int buttonWidth = (int)(250 * dp);
        int margins = (int)(10 * dp);



        //タイトル
        tv1 = new TextView(this);
        String temp = "友達画面";
        tv1.setText(temp);
        tv1.setTextSize(10*dp);
        layout.addView(tv1,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
          */
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
}
