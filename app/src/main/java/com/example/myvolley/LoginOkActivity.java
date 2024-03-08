package com.example.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginOkActivity extends AppCompatActivity {

    private TextView idText;
    private TextView emailText1;
    private TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ok);

        idText= findViewById(R.id.idText);
        emailText1= findViewById(R.id.emailText1);
        id= findViewById(R.id.id);


        String strID = (String) getIntent().getSerializableExtra("idOK");
        String strLogin = (String) getIntent().getSerializableExtra("loginOK");
        String strEmail = (String) getIntent().getSerializableExtra("emailOK");

        idText.setText("Pseudo: " + strID);
        emailText1.setText("Email: " + strLogin);
        emailText1.setText("Email: " + strEmail);
    }
}