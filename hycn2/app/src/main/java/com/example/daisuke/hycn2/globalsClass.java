package com.example.daisuke.hycn2;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class globalsClass extends Application{
    private static final String TAG = "globalsClass";
    //グローバル変数に使用する変数
    private operateDatabase od;//operateDatabaseのインスタンス
    private String str;//文字データ
    private String userID;


    public FirebaseAuth mAuth;//FirebaseAuthのインスタンス
    public FirebaseUser myuser;//user情報
    public Map<String, String> mapMyProfile = new LinkedHashMap<String, String>();//userのプロフィール
    public Map<String, String> mapMyFriends = new LinkedHashMap<String, String>();//userの友達リスト
    public Boolean IsDataRead;


    //全部初期化するメソッド

    //自分のユーザーIDをかえす．
    public String getMyUserID(){
        if(userID==null)
            userID = str;
        return userID;
    }

    //メソッド
    public void updateUI(FirebaseUser user){
       myuser = user;//グローバル変数に格納
        Log.d("updateUI","userID:"+user.getUid() );
        if(user!=null&&user.getUid()!=null){
            ReadUserData("AnonymouslyID/"+ user.getUid() , "uid");
            Log.d("updateUI","user login " + user.getUid() );
        }else{
            /*Toast.makeText(MainActivity.this, "Authentication is not compleate.",
                    Toast.LENGTH_LONG).show();*/
            Log.d("updateUI","Error"+"user!"+user+",getUid()!:" + user.getUid() );
        }
    }

    // Read from the database
    public String ReadUserData (String place , final String userID) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(place).child(userID);
        Log.d("ReadPlaceUserID", "path::"+myRef.toString() );
        myRef.addValueEventListener(new ValueEventListener() {
            String value;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ReadPlaceUserID", "onDataChange" );
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue( String.class );
                Log.d("ReadPlaceUserID", "getValue is: " + value );
                str =  value;
                Log.d("ReadPlaceUserID", "LoginID is: " + str );
                ///user-data取得
                DatabaseReference userDataRef = FirebaseDatabase.getInstance().getReference("user-data").child( str );
                Log.d("user-data:uid:", "path::"+userDataRef.toString() );
                userDataRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //インスタンスの取得

                        //HashMapクラスのオブジェクトを生成
                        Map<String, String> map = new LinkedHashMap<String, String>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Log.d("instance","(Key)"+data.getKey().toString()+":(Value)"+data.getValue().toString());
                            map.put(data.getKey(),data.getValue().toString());
                        }

                        //mapの確認
                        for (Map.Entry<String , String> myDataMap : map.entrySet()) {
                            Log.d("map","(key)"+myDataMap.getKey()+"(Value)"+myDataMap.getValue());
                        }
                        mapMyProfile = map;//グローバル変数に代入

                        //JSONで取得してみる．
                        JSONObject json = new JSONObject(map);
                        try {
                            //JSONの表示
                            Log.d("JSON表示", json.toString(2));

                            //JSON 操作
                           // Log.d("JSON操作", json.getString("birth"));

                            //誕生日の取り出し
                            //String birthjson =json.getString("birth");
                            //JSONObject JSONBirth = new JSONObject(birthjson);
                            //Log.d("JSONHobby",JSONBirth.toString(1));
                            //Log.d("year",JSONBirth.getString("year"));
                            //Log.d("month",JSONBirth.getString("month"));
                            //Log.d("day",JSONBirth.getString("day"));

                            //趣味取り出し
                            String hobbyjson =json.getString("hobby");
                            //Log.d("hobby_raw",hobbyjson);
                            JSONObject JSONHobby = new JSONObject(hobbyjson);
                            Log.d("JSONHobby",JSONHobby.toString(2));
                            Iterator<String> keys = JSONHobby.keys();
                            while(keys.hasNext()){
                                String key = keys.next();
                                String value = JSONHobby.getString(key);
                                Log.d("JSONHobby", "キー名 = " + key + "   :   値 = " + value);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("user-data", "Failed to read value.", databaseError.toException());
                    }
                });
                ///user-friends取得
                DatabaseReference userFriendsRef = FirebaseDatabase.getInstance().getReference("user-friends").child( str );
                Log.d("user-friends:uid:", "path::"+userFriendsRef.toString() );
                userFriendsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //インスタンスの取得

                        //HashMapクラスのオブジェクトを生成
                        Map<String, String> mapFriends = new LinkedHashMap<String, String>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Log.d("instance","(Key)"+data.getKey().toString()+":(value)"+data.getValue().toString());
                            mapFriends.put(data.getKey(),data.getValue().toString());
                        }

                        //mapの確認
                        for (Map.Entry<String , String> myFriendsMap : mapFriends.entrySet()) {
                            Log.d("mapFriends",myFriendsMap.getKey()+":"+myFriendsMap.getValue());
                        }

                        mapMyFriends = mapFriends;//グローバル変数に代入

                        /*
                        //名前だけ 名前(Value)からID(Key)を参照できないので気をつける．参考https://qiita.com/Shoeshock/items/57b25e7037ce81f15ab6
                        for (String name : mapFriends.values()) {
                            Log.d("FriendList",name);
                        }
                         */
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("user-friends", "Failed to read value.", databaseError.toException());
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("ReadPlaceUserID", "Failed to read value.", error.toException());
                str =  value;
            }
        });

        IsDataRead=true;
        return str;
    }

    /**
     * アプリケーションの起動時に呼び出される
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

    }

    /**
     * アプリケーション終了時に呼び出される
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate");

    }

    /**
     * グローバル変数の値を変更
     * @param global 変更する値
     */
    public void setGlobal(boolean global) {
        Log.d(TAG, "setGlobal");

    }

    /**
     * グローバル変数の値を取得
     * @return グローバル変数（mGlobal）
     */
    public void getGlobal() {
        Log.d(TAG, "getGlobal");

    }

}
