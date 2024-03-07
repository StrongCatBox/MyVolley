package com.example.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register_Activity extends AppCompatActivity {
    private Button Registerbtn2;
    private EditText passwordText;
    private EditText passwordText2;
    private EditText usernameText;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Registerbtn2= findViewById(R.id.Registerbtn2);
        passwordText= findViewById(R.id.passwordText);
        passwordText2= findViewById(R.id.passwordText2);
        usernameText= findViewById(R.id.usernameText);
        emailText= findViewById(R.id.emailText);

        Registerbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String LOGIN = usernameText.getText().toString().trim();
                final String EMAIL = emailText.getText().toString().trim();
                final String PASSWORD = passwordText.getText().toString().trim();
                final String PASSWORD2 = passwordText2.getText().toString().trim();

                Intent monIntent3 = new Intent(getApplicationContext(),Login.class);
                startActivity(monIntent3);
            }
        });
    }
}