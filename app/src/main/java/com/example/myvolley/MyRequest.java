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

    public void register(final String LOGIN, final String EMAIL, final String PASSWORD, final String PASSWORD2, final LoginCallBack callBack){
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
                        callBack.toutOK("felicitation votre compte a été créée");
                    } else {
                        //Toast.makeText(context, "Erreur lors de l'inscription", Toast.LENGTH_LONG).show();
                        callBack.pasOK(json.getString("message"));
                        Log.d("PHP", "Passage rp.PasOK dans MYregister");
                    }
                } catch (JSONException e) {
                    Log.d("PHP", "passage dans le catch de register: " + e);
                    callBack.systemError("Une erreur est survenue, veuillez renouveler votre essai");
                    //Toast.makeText(context, ":( Problème serveur rencontré", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("PHP", "Error :"+ error);
                if((error instanceof NetworkError)) {
                    callBack.systemError("Une erreur reseau s'est produite, \n\r impossible de joindre le serveur");
                } else if (error instanceof  VolleyError) {
                    callBack.systemError("Une erreur s'est produite, impossible de joindre le serveur");
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


    public void login(final String LOGIN,final String PASSWORD, final LoginCallBack callBack){
        String url = "http://192.168.1.159/MyVolley/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("PHP",response);

                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");


                    if(!error) {
                        HashMap<String,String>logIN = new HashMap<>();
                        logIN.put("id",json.getString("id"));
                        logIN.put("login",json.getString("login"));
                        logIN.put("email",json.getString("email"));
                        callBack.toutOK(logIN,"felicitation vous etes connecté");
                        Log.d("PHP","hashmap logIN+logIN");
                    } else {
                        callBack.pasOK(json.getString("message"));
                    }
                } catch (JSONException e)  {
                    e.printStackTrace();
                    Log.d("PHP", "passage dans le catch de login "+e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("PHP", "Error :"+ error);
                if((error instanceof NetworkError)) {
                    callBack.systemError("Une erreur reseau s'est produite, \n\r impossible de joindre le serveur");
                } else if (error instanceof  VolleyError) {
                    callBack.systemError("Une erreur s'est produite, impossible de joindre le serveur");
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
    public interface LoginCallBack {
        void toutOK(HashMap<String,String>logIN,String message); //tout c'est bien passé

        void toutOK(String message);

        void pasOK(String message); //erreurs de saisie
        void systemError(String message);
    }
}



