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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CreateGroupActivity extends AppCompatActivity {

    EditText GroupNameText;
    EditText GroupIDText;
    EditText GroupPasswordText;
    EditText GroupSafeCodeText;
    EditText GroupEmergencyCodeText;
    Button CreateGroupButton;

    DatabaseReference Userdatabase;

    Groups Group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        GroupNameText = (EditText) findViewById(R.id.GroupNameText);
        GroupIDText = (EditText) findViewById(R.id.GroupIDText);
        GroupPasswordText = (EditText) findViewById(R.id.GroupPasswordText);
        GroupSafeCodeText = (EditText) findViewById(R.id.GroupSafeCodeText);
        GroupEmergencyCodeText = (EditText) findViewById(R.id.GroupEmergencyCodeText);
        Userdatabase = FirebaseDatabase.getInstance().getReference().child("Group Detail");

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

        Group = new Groups();

        Query groupQuery = FirebaseDatabase.getInstance().getReference().child("Group Detail").orderByChild("groupID").equalTo(GroupID);//Query to check if the email already exists
        //Add listener in case the email exists
        groupQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) { //If even one identical email already exists, then prompt the user to login with it or use a different email
                    Toast.makeText(CreateGroupActivity.this, "This Group ID already exists. Please try a different ID", Toast.LENGTH_SHORT).show();
                } else { //If Group ID is unique
                    if (GroupName.isEmpty() || GroupID.isEmpty() || GroupPassword.isEmpty() || GroupSafeCode.isEmpty() || GroupEmergencyCode.isEmpty()) { //Check if all fields are filled properly
                        Toast.makeText(CreateGroupActivity.this, "Please make sure that all fields are filled out", Toast.LENGTH_LONG).show();
                    } else {
                        //Set info for the Group
                        Group.setGroupID(GroupID);
                        Group.setGroupName(GroupName);
                        Group.setGroupPassword(GroupPassword);
                        Group.setSafeCode(GroupSafeCode);
                        Group.setEmergencyCode(GroupEmergencyCode);

                        Userdatabase.push().setValue(Group); //Push Groups info to the database



                        Toast.makeText(CreateGroupActivity.this, "New Group Created for your Family!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
