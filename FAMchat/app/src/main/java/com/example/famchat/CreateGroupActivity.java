package com.example.famchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateGroupActivity extends AppCompatActivity {

    EditText GroupNameText;
    EditText GroupIDText;
    EditText GroupPasswordText;
    EditText GroupSafeCodeText;
    EditText GroupEmergencyCodeText;
    Button CreateGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        GroupNameText = (EditText) findViewById(R.id.GroupNameText);
        GroupIDText = (EditText) findViewById(R.id.GroupIDText);
        GroupPasswordText = (EditText) findViewById(R.id.GroupPasswordText);
        GroupSafeCodeText = (EditText) findViewById(R.id.GroupSafeCodeText);
        GroupEmergencyCodeText = (EditText) findViewById(R.id.GroupEmergencyCodeText);

        CreateGroupButton = (Button) findViewById(R.id.CreateGroupButton);
        CreateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGroup();
            }
        });


    }

    public void createGroup(){
        final String GroupName = GroupNameText.getText().toString().trim();
        final String GroupID = GroupIDText.getText().toString().trim();
        final String GroupPassword = GroupIDText.getText().toString().trim();
        final String GroupSafeCode = GroupSafeCodeText.getText().toString().trim();
        final String GroupEmergencyCode = GroupEmergencyCodeText.getText().toString().trim();

        FirebaseDatabase.getInstance().getReference().child("Group Detail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Groups group = snapshot.getValue(Groups.class);

                    if (GroupID.equals(group.getGroupID())){
                        Toast.makeText(CreateGroupActivity.this, "This Group ID already exists. Please try a different ID", Toast.LENGTH_LONG).show();
                    }else{

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
