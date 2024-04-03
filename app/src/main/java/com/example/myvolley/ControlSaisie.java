package com.example.myvolley;

import android.util.Patterns;
import java.util.regex.Pattern;

public class ControlSaisie {


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +          //début de la liste
                    "(?=.*[0-9])" +         //au moins 1 chiffre
                    "(?=.*[a-z])" +         //au moins une minuscule
                    "(?=.*[A-Z])" +         //au moins une majuscule
                    // "(?!.*[a-zA-Z])" +   // aucunes lettres possibles
                    "(?=.*[@#!$%^&+=])" +   // au moins 1 caractère spécial listé
                    "(?=\\S+$)" +         //pas d'espace vide
                    ".{6,20}" +             //minimum 6 caractères et maxi 20 caractères
                    "$");                   //fin de la ligne

//ici cest le constructeur
    private ControlSaisie() {

    }

    public static boolean isValidLogin(String login) {

        return !login.isEmpty() && login.length() <= 10;
    }

    public static boolean isValidEmail(String email) {

        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {

        return !password.isEmpty() && PASSWORD_PATTERN.matcher(password).matches();
    }
}
