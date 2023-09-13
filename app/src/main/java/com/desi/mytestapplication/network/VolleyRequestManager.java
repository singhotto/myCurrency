package com.desi.mytestapplication.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class VolleyRequestManager {
    private static VolleyRequestManager instance;
    private RequestQueue requestQueue;
    private static Context context;

    private VolleyRequestManager(Context ctx){
        context = ctx;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestManager getInstance(Context context){
        if(instance == null){
            instance = new VolleyRequestManager(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    public void makeGetRequest(String url, final VolleyResponseListener listener){
        JsonRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, response -> {
                    if(listener != null){
                        listener.onResponse(response);
                    }
        },
                error -> {
                    if(listener != null){
                        listener.onError(error);
                    }
                }
        );
        addToRequestQueue(jsonObjectRequest);
    }

    public  interface VolleyResponseListener{
        void onResponse(JSONObject response);
        void onError(Exception error);
    }
}
