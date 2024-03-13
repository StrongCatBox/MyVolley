package com.example.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginOkActivity extends AppCompatActivity {

    private TextView idText;
    private TextView emailText1;
    private Button decoBtn;
    private SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ok);

        idText= findViewById(R.id.idText);
        emailText1= findViewById(R.id.emailText1);
        decoBtn = findViewById(R.id.decoBtn);

        sessionManager = new SessionManager(this);
        idText.setText("id: " + sessionManager.getId());
        emailText1.setText("Email: " + sessionManager.getEmail());






//        String strID = (String) getIntent().getSerializableExtra("idOK");
//        String strLogin = (String) getIntent().getSerializableExtra("loginOK");
//        String strEmail = (String) getIntent().getSerializableExtra("emailOK");
//
//        idText.setText("Pseudo: " + strID);
//        emailText1.setText("Email: " + strLogin);
//        emailText1.setText("Email: " + strEmail);

        decoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
                Intent i = new Intent(getApplicationContext(),Login.class);
                Toast.makeText(getApplicationContext(),"vous etes deconnecté", Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();
            }
        });
    }
}