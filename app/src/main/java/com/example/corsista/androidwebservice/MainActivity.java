package com.example.corsista.androidwebservice;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading..");
        dialog.show();
        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(" https://androidtutorialpoint.com/api/volleyJsonObject", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("SERVICE", "Response: " + response.toString());
                        dialog.hide();
                        TextView textView = findViewById(R.id.testo);
                        textView.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("VOLLEY", "Error: " + error.getMessage());
                dialog.hide();
                TextView textView = findViewById(R.id.testo);
                textView.setText(error.toString());
            }
        });
        // Access the RequestQueue through your singleton class.
        ServiceQueueSingleton.getInstance(this).addToRequestQueue(jsonObjectReq);
    }

}
