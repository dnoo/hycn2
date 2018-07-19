package com.example.daisuke.hycn2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class Lunchpage extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lunchpage);

        handler.postDelayed(new splashHandler(), 2000);
    }

    class splashHandler implements Runnable{
        public void run() {
            Intent intent = new Intent(getApplicationContext(), StartPage.class);

            startActivity(intent);

            Lunchpage.this.finish();
        }

    }
}
