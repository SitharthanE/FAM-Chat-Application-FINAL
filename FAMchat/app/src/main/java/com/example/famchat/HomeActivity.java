package com.example.famchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button SendRequestsButton;
    Button ViewRequestsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SendRequestsButton = (Button) findViewById(R.id.SendRequestsButton);
        ViewRequestsButton = (Button) findViewById(R.id.ViewRequestsButton);

        SendRequestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SendUpdateRequestsActivity.class);
                startActivity(intent);
            }
        });
    }
}
