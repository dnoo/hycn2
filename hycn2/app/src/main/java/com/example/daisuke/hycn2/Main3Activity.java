package com.example.daisuke.hycn2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //追加ここから
        //QRコード化する文字列
        String data = "https://www.google.com";
        //QRコード画像の大きさを指定(pixel)
        int size = 500;

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            //QRコードをBitmapで作成
            Bitmap bitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, size, size);

            //作成したQRコードを画面上に配置
            ImageView imageViewQrCode = (ImageView) findViewById(R.id.imageView);
            imageViewQrCode.setImageBitmap(bitmap);

        } catch (WriterException e) {
            throw new AndroidRuntimeException("Barcode Error.", e);
        }
        //追加ここまで
    }

    public void changescreen00(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 0);
    }

    public void changescreen01(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, 0);
    }

    //追加ここから
    public void changescreen02(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        IntentIntegrator integrator =  new IntentIntegrator(Main3Activity.this);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String code;

        if (null != intentResult && (code = intentResult.getContents()) != null) {
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
        }
    }
    //追加ここまで
}
