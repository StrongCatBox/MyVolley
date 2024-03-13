package com.example.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    private EditText UsernameField;
    private EditText PasswordField;
    private Button RegisterBtn;
    private Button LoginBtn;

    private RequestQueue queue;
    private MyRequest request;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RegisterBtn= findViewById(R.id.RegisterBtn);
        UsernameField= findViewById(R.id.UsernameField);
        PasswordField= findViewById(R.id.PasswordField);
        LoginBtn= findViewById(R.id.LoginBtn);


        queue = MySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLogged()){
            Intent i = new Intent(getApplicationContext(), LoginOkActivity.class);
            startActivity(i);
        }


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String LOGIN = UsernameField.getText().toString().trim();
                final String PASSWORD = PasswordField.getText().toString().trim();

                if (!LOGIN.isEmpty() && !PASSWORD.isEmpty()) {

                    request.login(LOGIN, PASSWORD, new MyRequest.LoginCallBack() {


                        @Override
                        public void toutOK(HashMap<String, String> logIN, String message) {
                            Log.d("PHP", "messagePHP: " + message);
                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), LoginOkActivity.class);
                            i.putExtra("idOK", logIN.get(("login")));
                            i.putExtra("loginOK", logIN.get(("login")));
                            i.putExtra("emailOK", logIN.get(("email")));

                            startActivity(i);
                            finish();

                        }



                        @Override
                        public void pasOK(String message) {
                            Log.d("PHP", "passage dans PAS OK de Login Activity");
                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                            return;

                        }

                        @Override
                        public void systemError(String message) {
                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                            return;

                        }
                    });
                }
                else {

                    Toast.makeText(Login.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent2 = new Intent(getApplicationContext(),Register_Activity.class);
                startActivity(monIntent2);
            }
        });
    }
}