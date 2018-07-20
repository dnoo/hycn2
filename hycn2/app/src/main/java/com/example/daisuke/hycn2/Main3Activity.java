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
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;
import java.util.Map;


public class Main3Activity extends AppCompatActivity {

    //グローバル変数
    globalsClass globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main3Activity","onCreate");
        //グローバル変数を取得
        globals = (globalsClass) this.getApplication();

        setContentView(R.layout.activity_main3);
        //Log.d("My name is",globals.mapMyProfile.get("name"));
        //追加ここから
        //QRコード化する文字列
        String data = "学生名刺,"+globals.getMyUserID()+","+globals.mapMyProfile.get("name");
        //QRコード画像の大きさを指定(pixel)
        int size = 500;

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            HashMap hints = new HashMap();

            //文字コードの指定
            hints.put(EncodeHintType.CHARACTER_SET, "shiftjis");

            //誤り訂正レベルを指定
            //L 7%が復元可能
            //M 15%が復元可能
            //Q 25%が復元可能
            //H 30%が復元可能
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);

            //QRコードのバージョンを指定
            hints.put(EncodeHintType.QR_VERSION, 20);
            //QRコードで日本語を扱うためにシフトJISを指定
            hints.put(EncodeHintType.CHARACTER_SET, "shiftjis");
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

    @Override
    public void onStart(){
        super.onStart();
        Log.d("Main3Activity","onStart");
        if(globals.IsBefriend==true){
            if(!globals.mapFriendsProfile.isEmpty()){
                Log.d("mapFriendsProfile",globals.mapMyProfile.toString());
                //仲良くする
                globals.befriend();
            }else {
                Log.d("mapFriendsProfile", "mapFriendsProfile is Empty");
                // Go to MainActivity
                startActivity(new Intent(Main3Activity.this, Main3Activity.class));
                finish();
                return ;
            }
        }
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

    //追加ここから
    public void changescreen02(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Toast.makeText(this, "カメラを起動しています……", Toast.LENGTH_SHORT).show();
        IntentIntegrator integrator =  new IntentIntegrator(Main3Activity.this);
        integrator.setCaptureActivity(CaptureActivityAnyOrientation.class);
        // キャプチャ画面の下方にメッセージを表示
        integrator.setPrompt("Scan a QRcode");
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String code;

        if (null != intentResult && (code = intentResult.getContents()) != null) {
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
            Log.d("readQR", intentResult.getContents());
            //文字列分解
            String[] ReadWords = code.split(",");
            for(int i=0;i<ReadWords.length;i++)
                Log.d("ReadWord",ReadWords[i]);
            globals.setMyFriendID(ReadWords[1]);
            globals.clearFriendProfile();
            //globals.getFriendProfile();//友達になる人の情報を取り寄せておく
            Log.d("Result","start befriend" );
            globals.befriend();
            //globals.IsBefriend=true;

        }
    }
    //追加ここまで
}
