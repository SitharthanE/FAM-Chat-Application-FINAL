package com.example.famchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SendUpdateRequestsActivity extends AppCompatActivity {

    DatabaseReference datab;
    ListView MemberView;
    ArrayList<String> memberlist;
    ArrayAdapter<String> arrayAdapter;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_update_requests);

        memberlist = new ArrayList<>();
        datab = FirebaseDatabase.getInstance().getReference().child("Users");
        MemberView = (ListView) findViewById(R.id.MemberView);
        arrayAdapter = new ArrayAdapter<String>(SendUpdateRequestsActivity.this, android.R.layout.simple_list_item_1, memberlist);
        MemberView.setAdapter(arrayAdapter);

        datab.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    user = snapshot.getValue(Users.class);

                    if (user.getGroupID().equals(GlobalVariable.CurrentGroupID)){
                        memberlist.add(user.toString());
                    }
                }

                MemberView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
