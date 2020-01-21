package com.example.famchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button RegisterButton;
    Button LoginButton;
    EditText EmailText;
    EditText PasswordText;

    DatabaseReference userDatabase;

    Users User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fields where the user will input email and password
        EmailText = (EditText) findViewById(R.id.EmailText);
        PasswordText = (EditText) findViewById(R.id.PasswordText);

        //If user does not have an account and selects create account, lead him to the activity that will help them create an account
        RegisterButton = (Button) findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        //Run the login function if user chooses to login with their info
        LoginButton = (Button) findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }

    //Leads to registerActivity
    public void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void loginUser(){
        //Get the texts from their fields
        final String Email = EmailText.getText().toString().trim();
        final String Password = PasswordText.getText().toString().trim();

        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users user = snapshot.getValue(Users.class);

                    if ((Email.equals(user.getEmail())) && (Password.equals(user.getPassword()))) {
                        Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_LONG).show();
                        //Leads to chat activity
                    }
                }

                Toast.makeText(MainActivity.this, "Please make sure that the Email and Password are entered correctly", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
