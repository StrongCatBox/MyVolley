package com.example.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.android.material.textfield.TextInputLayout;

public class Register_Activity extends AppCompatActivity {

    private TextInputLayout email, login, password, confirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        btnRegister = findViewById(R.id.Registerbtn2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    btnRegister.setVisibility(View.GONE);
                }
            }
        });
    }

    private boolean validateInputs() {
        String strLogin = login.getEditText().getText().toString().trim();
        String strEmail = email.getEditText().getText().toString().trim();
        String strPassword = password.getEditText().getText().toString().trim();
        String strConfirmPassword = confirmPassword.getEditText().getText().toString().trim();


        //j'ai appelleé les methodes ICI
        boolean isValidLogin = ControlSaisie.isValidLogin(strLogin);
        boolean isValidEmail = ControlSaisie.isValidEmail(strEmail);
        boolean isValidPassword = ControlSaisie.isValidPassword(strPassword);

        if (!isValidLogin) {
            login.setError("Un login doit être renseigné (maximum 10 caractères)");
            return false;
        }

        if (!isValidEmail) {
            email.setError("Un email valide doit être renseigné");
            return false;
        }

        if (!isValidPassword) {
            password.setError("Le mot de passe doit contenir au moins 6 caractères, dont au moins une majuscule, une minuscule, un chiffre et un caractère spécial");
            return false;
        }

        if (!strPassword.equals(strConfirmPassword)) {
            confirmPassword.setError("Les mots de passe ne correspondent pas");
            return false;
        }

        return true;
    }
}
