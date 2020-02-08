package com.example.famchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstLoginActivity extends AppCompatActivity {

    Button CreateButton;
    Button JoinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);

        //Takes you to the create group activity
        CreateButton = (Button) findViewById(R.id.CreateButton);
        JoinButton = (Button) findViewById(R.id.JoinButton);
        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstLoginActivity.this, CreateGroupActivity.class);
                startActivity(intent);
            }
        });

        JoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstLoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}
