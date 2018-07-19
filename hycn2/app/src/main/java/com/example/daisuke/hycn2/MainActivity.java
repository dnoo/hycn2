package com.example.daisuke.hycn2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;

import com.google.firebase.database.ChildEventListener;
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
    private FirebaseAuth mAuth;//FirebaseAuthのインスタンス
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
        //ここから追加
        mAuth = FirebaseAuth.getInstance();//FirebaseAuthのインスタンス生成
        //ここまで追加
        od = new operateDatabase();//operateDatabaseのインスタンス生成
        od.testWriteMas2Database();
        //DBのtest用だったもの
        //tv = findViewById(R.id.textView3);
        //od.testReadMesFromDatabase(tv);

    }





    //画面4への遷移
    public void changescreen01(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, 0);
    }

    //画面3への遷移
    public void changescreen02(View view) {
        //TextView tv = findViewById(R.id.mytext1);
        //tv.setText("Changed!");
        Intent intent = new Intent(this, Main3Activity.class);
        startActivityForResult(intent, 0);
    }

    //ここから追加


    FirebaseUser user;

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        //※updateUIはサンプルプログラム独自のもの
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

        //ログイン処理
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public static final String TAG = "onStartAnonymous";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            user = mAuth.getCurrentUser();
                            Log.d(TAG, "UserID:"+user.getUid());

                            updateUI(user);
                            Log.d(TAG, "When I do?");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }


    public void updateUI(FirebaseUser user){
        Log.d("updateUI","userID:"+user.getUid() );
        if(user!=null&&user.getUid()!=null){
            od.ReadPlaceUserID("AnonymouslyID/"+ user.getUid() , "uid");
            Log.d("updateUI","str is" + str );
        }else{
            Toast.makeText(MainActivity.this, "Authentication is not compleate.",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        //FirebaseAuth.getInstance().signOut();
        Log.d("onStop","MainActivity");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
        Log.d("onDestroy","MainActivity");
    }
    //ここまで追加
}




////////original codes////////
/*
///////////////
/////////
////ここから下はメモ
//////////////
////////
////


    1
    //ファイルの保存(original)
    public void saveFile(String file, String str) {
        try(FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {

            fileOutputstream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    2
    // ファイルを読み出し(original)
    public String readFile(String file){
        String text = null;

        try(FileInputStream fileInputStream = openFileInput(file);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(fileInputStream,"UTF-8"));
            ) {

            String lineBuffer;
            while ((lineBuffer = reader.readLine()) != null) {
                text = lineBuffer;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }


    3
    //OnClick登録
    public void testclick(View view) {
        String temp = et01.getText().toString();
        ev01.setText(temp);
    }


    4
    //定義(class MainActivityとonCreateの間)
    private EditText et01;
    private TextView ev01;


    5
    //紐付け(onCreateの直後)
    et01 =findViewById(R.id.my02);
    ev01 = findViewById(R.id.my01);
    Button Bt01 = findViewById(R.id.my03);


    6
    //ファイル初期読み込み1(onCreate内)
    String temp3 = readFile("testfile.txt");
    if(temp3 != null) {
        ev01.setText(temp3);
    }
    //ファイル読み込み1


    7
    //アクション登録
    Bt01.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            //保存箇所
            String temp = et01.getText().toString();
            saveFile("testfile.txt",temp);

            //読み込み箇所
            String temp2 = readFile("testfile.txt");
            if(temp2 != null) {
                ev01.setText(temp2);
            } else {
                ev01.setText("error");
            }

        }
    });




///////////////
/////////
////ここから上はメモ
//////////////
////////
////
*/




