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

    private Button BackToLogin;
    private EditText EmailEntry;
    private EditText NameEntry;
    private EditText PasswordEntry;
    private Button CreateAccountButton;

    DatabaseReference Userdatabase;

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

        Userdatabase = FirebaseDatabase.getInstance().getReference("Users");
        EmailEntry = (EditText) findViewById(R.id.EmailEntry);
        NameEntry = (EditText) findViewById(R.id.NameEntry);
        PasswordEntry = (EditText) findViewById(R.id.PasswordEntry);
        CreateAccountButton = (Button) findViewById(R.id.CreateAccountButton);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }


    public void createUser() {
        String email = EmailEntry.getText().toString().trim();
        String password = PasswordEntry.getText().toString().trim();
        String name = NameEntry.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Please make sure that all fields are filled out", Toast.LENGTH_LONG).show();
        } else {
            String ID = Userdatabase.push().getKey();

            Users User = new Users(email,password,name);

            Userdatabase.child(ID).setValue(name);



            Toast.makeText(this, "New Account Created", Toast.LENGTH_LONG).show();
        }
    }
}
