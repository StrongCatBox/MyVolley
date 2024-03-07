package com.example.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;

public class Register_Activity extends AppCompatActivity {
    private Button Registerbtn2;
    private EditText passwordText;
    private EditText passwordText2;
    private EditText usernameText;
    private EditText emailText;
    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Registerbtn2= findViewById(R.id.Registerbtn2);
        passwordText= findViewById(R.id.passwordText);
        passwordText2= findViewById(R.id.passwordText2);
        usernameText= findViewById(R.id.usernameText);
        emailText= findViewById(R.id.emailText);


        queue = MySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        Registerbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String LOGIN = usernameText.getText().toString().trim();
                final String EMAIL = emailText.getText().toString().trim();
                final String PASSWORD = passwordText.getText().toString().trim();
                final String PASSWORD2 = passwordText2.getText().toString().trim();

                request.register(LOGIN, EMAIL, PASSWORD, PASSWORD2);

//                Intent monIntent3 = new Intent(getApplicationContext(),Login.class);
//                startActivity(monIntent3);
            }
        });
    }
}