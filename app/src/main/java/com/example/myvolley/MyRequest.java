package com.example.myvolley;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyRequest {
    private Context context;
    private RequestQueue queue;

    public MyRequest(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    public void register(final String LOGIN, final String EMAIL, final String PASSWORD, final String PASSWORD2, final RetoursPHP rP){
        String url = "http://192.168.1.159/MyVolley/register.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("PHP",response);

                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");


                    if(!error) {
                        //Toast.makeText(context, "Reussite de l'inscription", Toast.LENGTH_LONG).show();
                        rP.toutOK("felicitation votre compte a été créée");
                    } else {
                        //Toast.makeText(context, "Erreur lors de l'inscription", Toast.LENGTH_LONG).show();
                        rP.pasOK(json.getString("message"));
                        Log.d("PHP", "Passage rp.PasOK dans MYregister");
                    }
                } catch (JSONException e) {
                    Log.d("PHP", "passage dans le catch de register: " + e);
                    rP.systemError("Une erreur est survenue, veuillez renouveler votre essai");
                    //Toast.makeText(context, ":( Problème serveur rencontré", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("PHP", "Error :"+ error);
                if((error instanceof NetworkError)) {
                    rP.systemError("Une erreur reseau s'est produite, \n\r impossible de joindre le serveur");
                } else if (error instanceof  VolleyError) {
                    rP.systemError("Une erreur s'est produite, impossible de joindre le serveur");
                }

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
                map.put("login",LOGIN);
                map.put("email",EMAIL);
                map.put("password",PASSWORD);
                map.put("password2",PASSWORD2);

                return map;
            }
        };

        queue.add(request);
        Log.d("PHP","request: " + request);
    }


    public void login(final String LOGIN,final String PASSWORD, final RetoursPHP rP){
        String url = "http://192.168.1.159/MyVolley/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("PHP",response);

                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");


                    if(!error) {
                        //Toast.makeText(context, "Reussite de l'inscription", Toast.LENGTH_LONG).show();
                        rP.toutOK("Vous etes bien connectée");
                    } else {
                        //Toast.makeText(context, "Erreur lors de l'inscription", Toast.LENGTH_LONG).show();
                        rP.pasOK(json.getString("message"));
                        Log.d("PHP", "Passage rp.PasOK dans MYregister");
                    }
                } catch (JSONException e) {
                    Log.d("PHP", "passage dans le catch de register: " + e);
                    rP.systemError("Une erreur est survenue, veuillez renouveler votre essai");
                    //Toast.makeText(context, ":( Problème serveur rencontré", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("PHP", "Error :"+ error);
                if((error instanceof NetworkError)) {
                    rP.systemError("Une erreur reseau s'est produite, \n\r impossible de joindre le serveur");
                } else if (error instanceof  VolleyError) {
                    rP.systemError("Une erreur s'est produite, impossible de joindre le serveur");
                }

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
                map.put("login",LOGIN);
                map.put("password",PASSWORD);


                return map;
            }
        };

        queue.add(request);
        Log.d("PHP","request: " + request);



    }
    public interface RetoursPHP {
        void toutOK(String message); //tout c'est bien passé
        void pasOK(String message); //erreurs de saisie
        void systemError(String message);
    }
}
