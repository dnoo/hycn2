package com.example.daisuke.hycn2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class StartPage extends Activity{
    //グローバル変数
    globalsClass globals;
    private FirebaseUser signinUser;//ログインで使うuser情報

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main8fromhayato);
        Log.d("StartPage", "onCreate!!" );

        //グローバル変数を取得
        globals = (globalsClass) this.getApplication();
        //初期化
            globals.IsDataRead=false;//データ読み込み待ち
            globals.mAuth = FirebaseAuth.getInstance();//FirebaseAuthのインスタンス生成
            //ログインしているのか確認
            FirebaseUser user = globals.mAuth.getCurrentUser();
            if(user!=null) {
                globals.updateUI(user);
                return ;
            }
            //ログイン処理
            globals.mAuth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        public static final String TAG = "onSignInAnonymously";
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInAnonymously:success");
                                signinUser = globals.mAuth.getCurrentUser();
                                Log.d(TAG, "UserID:"+signinUser.getUid());
                                globals.updateUI(signinUser);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInAnonymously:failure", task.getException());
                          /*Toast.makeText(MainActivity.this, "Authentication failed.",
                                  Toast.LENGTH_SHORT).show();*/
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
    }
    @Override
    public void onStart(){
        super.onStart();
        //setContentView(R.layout.activity_main8fromhayato);
        Log.d("StartPage", "onStart!!" );
        Log.d("madamadadana", "madamadadana" );
        Intent intent = new Intent(this, Main5Activity.class);
        startActivityForResult(intent, 0);
    }
}
