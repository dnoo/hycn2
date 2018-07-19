package com.example.daisuke.hycn2;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class operateDatabase {
    public operateDatabase(){

    }
    public static final String TAG = "operateDatabase";

    //追加ここから

    // Write a message to the database
    public void testWriteMas2Database () {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }
    String str="";
    public TextView tvClass;
    // test Read from the database
    public void testReadMesFromDatabase (TextView tv) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        Log.d(TAG, "path::"+myRef.toString() );
        tvClass=tv;
        myRef.addValueEventListener(new ValueEventListener() {
            String value;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                str = ("Value is: " + value).toString();
                tvClass.setText(str);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                str = ("Value is: " + value).toString();
            }

        });
    }


    // Read from the database
    public String ReadPlaceUserID (String place , String userID) {
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
                                //JSONで取得してみる．
                                JSONObject json = new JSONObject(map);
                                try {
                                    //JSONの表示
                                     Log.d("JSON表示", json.toString(2));

                                    //JSON 操作
                                    Log.d("JSON操作", json.getString("birth"));

                                    //誕生日の取り出し
                                    String birthjson =json.getString("birth");
                                    JSONObject JSONBirth = new JSONObject(birthjson);
                                    Log.d("JSONHobby",JSONBirth.toString(1));
                                    Log.d("year",JSONBirth.getString("year"));
                                    Log.d("month",JSONBirth.getString("month"));
                                    Log.d("day",JSONBirth.getString("day"));
                                    /*
                                    Log.d("birth_raw",birthjson);
                                    String day=  birthjson.substring(birthjson.indexOf("day=")+4,birthjson.indexOf(",",birthjson.indexOf("day=")+4));
                                    Log.d("day",day);
                                    String month=  birthjson.substring(birthjson.indexOf("month=")+6,birthjson.indexOf(",",birthjson.indexOf("month=")+6));
                                    Log.d("month",month);
                                    String year=  birthjson.substring(birthjson.indexOf("year=")+5,birthjson.indexOf("}",birthjson.indexOf("year=")+5));
                                    Log.d("year",year);
                                    */

                                    //趣味取り出し
                                    String hobbyjson =json.getString("hobby");
                                    Log.d("hobby_raw",hobbyjson);
                                    JSONObject JSONHobby = new JSONObject(hobbyjson);
                                    Log.d("JSONHobby",JSONHobby.toString(2));
                                    Iterator<String> keys = JSONHobby.keys();
                                    while(keys.hasNext()){
                                        String key = keys.next();
                                        String value = JSONHobby.getString(key);
                                        Log.d("MainActivity", "キー名 = " + key + "   :   値 = " + value);
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
                            Log.d("map",myFriendsMap.getKey()+":"+myFriendsMap.getValue());
                        }

                        //名前だけ 名前(Value)からID(Key)を参照できないので気をつける．参考https://qiita.com/Shoeshock/items/57b25e7037ce81f15ab6
                        for (String name : mapFriends.values()) {
                            Log.d("FriendList",name);
                        }

                        /*
                        //JSONで取得してみる．
                        JSONObject JSON = new JSONObject(map);
                        try {

                            //友達リスト化

                            Iterator<String> keys = JSON.keys();
                            while(keys.hasNext()){
                                String key = keys.next();
                                String value = JSON.getString(key);
                                Log.d("FriendList", "キー名 = " + key + "   :   値 = " + value);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        */
                        /*
                        //friendのuserIDから名前取得（めちゃくちゃ重くなってゆく部分．user-friendsをuserID:{friendID:friendName}にしたのでコメントアウト
                        DatabaseReference friendsDataRef = FirebaseDatabase.getInstance().getReference("user-data");
                        Log.d("user-data:", "path::"+friendsDataRef.toString() );
                        friendsDataRef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                Log.d("user-friends:user-data:", "path::"+dataSnapshot.toString());
                                Log.d("user-friends:user-data:"+"getValue():", "path::"+dataSnapshot.getValue().toString());
                                //Log.d("onChildAdded","データ取得"+friendList.get(0));
                                //DatabaseReference friendRef = dataSnapshot.getRef();
                                //Log.d("user-friends:user-data:"+friendList.get(0), ":path::"+friendRef.toString() );
                                //Log.d("friend-name",friendRef.getValue().toString());
                                try {
                                    JSONObject JSONUserData = new JSONObject(dataSnapshot.getValue().toString());
                                    Log.d("JSONUserData",JSONUserData.toString(2));
                                    Log.d("frind-name",dataSnapshot.getKey()+"は"+JSONUserData.getString("name"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
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

        return str;
    }
    //追加ここまで
}
