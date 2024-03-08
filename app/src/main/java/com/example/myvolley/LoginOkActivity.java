package com.example.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginOkActivity extends AppCompatActivity {

    private TextView idText;
    private TextView emailText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ok);

        idText= findViewById(R.id.idText);
        emailText1= findViewById(R.id.emailText1);

        Intent intent = getIntent();
        String pseudo = intent.getStringExtra("pseudo");
        String email = intent.getStringExtra("email");

        idText.setText("Pseudo: " + pseudo);
        emailText1.setText("Email: " + email);
    }
}