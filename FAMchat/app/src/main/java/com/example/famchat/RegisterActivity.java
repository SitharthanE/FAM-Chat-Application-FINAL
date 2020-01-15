package com.example.famchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        EmailEntry = (EditText) findViewById(R.id.EmailEntry);
        NameEntry = (EditText) findViewById(R.id.NameEntry);
        PasswordEntry = (EditText) findViewById(R.id.PasswordEntry);
        CreateAccountButton = (Button) findViewById(R.id.CreateAccountButton);
        Userdatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }


    public void createUser() {
        User = new Users();

        String email = EmailEntry.getText().toString().trim();
        String password = PasswordEntry.getText().toString().trim();
        String name = NameEntry.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Please make sure that all fields are filled out", Toast.LENGTH_LONG).show();
        } else {
            User.setEmail(email);
            User.setPassword(password);
            User.setName(name);

            Userdatabase.push().setValue(User);

            Toast.makeText(this, "New Account Created", Toast.LENGTH_LONG).show();
        }
    }
}
