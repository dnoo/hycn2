package com.example.daisuke.hycn2;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    // Read from the database
    public void testReadMesFromDatabase (TextView tv) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
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
    //追加ここまで
}
