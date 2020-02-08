package com.example.famchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.util.jar.Attributes;

public class RegisterActivity extends AppCompatActivity {

    Button BackToLogin;
    EditText EmailEntry;
    EditText NameEntry;
    EditText PasswordEntry;
    Button CreateAccountButton;

    DatabaseReference Userdatabase;

    Users User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BackToLogin = (Button) findViewById(R.id.BackToLogin);
        BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class); //Switch to login activity
                startActivity(intent);
            }
        });

        EmailEntry = (EditText) findViewById(R.id.EmailEntry);
        NameEntry = (EditText) findViewById(R.id.NameEntry);
        PasswordEntry = (EditText) findViewById(R.id.PasswordEntry);
        CreateAccountButton = (Button) findViewById(R.id.CreateAccountButton);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        }); //Create user function if the button is pressed
    }


    public void createUser() {
        //Create and get texts from fields into strings
        final String email = EmailEntry.getText().toString().trim();
        final String password = PasswordEntry.getText().toString().trim();
        final String name = NameEntry.getText().toString().trim();
        final String groupID = "NULL";
        final Double latitude = 0.0;
        final Double longitude = 0.0;
        final String status = "NA";


        //Create new User object
        User = new Users();
        Query emailQuery = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("email").equalTo(email);//Query to check if the email already exists
        //Add listener in case the email exists
        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) { //If even one identical email already exists, then prompt the user to login with it or use a different email
                    Toast.makeText(RegisterActivity.this, "Account with this email already exists. Please Login or use different email", Toast.LENGTH_SHORT).show();
                } else { //If email is unique
                    if (email.isEmpty() || password.isEmpty() || name.isEmpty()) { //Check if all fields are filled properly
                        Toast.makeText(RegisterActivity.this, "Please make sure that all fields are filled out", Toast.LENGTH_LONG).show();
                    } else {
                        //Set info for the user
                        User.setEmail(email);
                        User.setPassword(password);
                        User.setName(name);
                        User.setGroupID(groupID);
                        User.setLatitude(latitude);
                        User.setLongitude(longitude);
                        User.setStatus(status);

                        String newEmail = email.replace(".", ",");
                        Userdatabase = FirebaseDatabase.getInstance().getReference();
                        Userdatabase.child("Users").child(newEmail).setValue(User); //Push user's info to the database

                        Toast.makeText(RegisterActivity.this, "New Account Created", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
