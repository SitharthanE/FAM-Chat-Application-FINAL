package com.example.famchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JoinActivity extends AppCompatActivity {

    EditText GroupIDText;
    EditText GroupPasswordText;
    Button JoinGroupButton;

    DatabaseReference reff;

    Groups group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        GroupIDText = (EditText) findViewById(R.id.GroupIDText);
        GroupPasswordText = (EditText) findViewById(R.id.GroupPasswordText);
        JoinGroupButton = (Button) findViewById(R.id.JoinGroupButton);


        JoinGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinGroup();
            }
        });

    }

    public void JoinGroup() {
        final String groupid = GroupIDText.getText().toString().trim();
        final String groupPassword = GroupPasswordText.getText().toString().trim();

        FirebaseDatabase.getInstance().getReference().child("Group Detail").addListenerForSingleValueEvent(new ValueEventListener() {
            int check = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Groups group = snapshot.getValue(Groups.class);

                    if ((groupid.equals(group.getGroupID())) && (groupPassword.equals(group.getGroupPassword()))) {
                        check = 1;
                        GlobalVariable.CurrentGroupID = groupid;
                        String newemail = GlobalVariable.CurrentEmail.replace(".",",");
                        reff = FirebaseDatabase.getInstance().getReference();
                        reff.child("Users").child(newemail).child("groupID").setValue(groupid);
                        reff.child("Users").child(newemail).child("status").setValue("Member");
                        Toast.makeText(JoinActivity.this, "Successfully joined your Family!", Toast.LENGTH_LONG).show();

                        //Leads to Home activity or first login activity
                    }
                }
                if (check == 0) {
                    Toast.makeText(JoinActivity.this, "Please make sure that the Group Id and Group Password is entered correctly", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
