package com.ishan1608.volleypost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView statusMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusMessage = (TextView) findViewById(R.id.status_message);

        String testUrl = "https://dartserve.herokuapp.com/";
        StringRequest testUrlRequest = new StringRequest(Request.Method.POST, testUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                displayStatus(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                displayStatus("An error occurred");
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("greeting", "Ola Server");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(testUrlRequest);
    }
    private void displayStatus(String status) {
        statusMessage.setText(status);
    }
}
