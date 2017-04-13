package com.example.pianoafrik.devlessbackend.DevlessPackage;

import android.content.Context;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Devless {

    String token, rootUrl, serviceName;

    Context mContext;

    public Devless(String token, String rootUrl, String serviceName, Context mContext) {
        this.token = token;
        this.rootUrl = rootUrl;
        this.serviceName = serviceName;
        this.mContext = mContext;
    }

    public JSONObject createDevlessPostBody (String table_name, Map<String, Object> fieldMap ) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> mainList  = new ArrayList<Object>();
        Map<String, Object> miniMap = new HashMap<String, Object>();
        List<Object> fieldList = new ArrayList<Object>();
        fieldList.add(fieldMap);
        miniMap.put("name", table_name);
        miniMap.put("field", fieldList);
        mainList.add(miniMap);
        map.put("resource", mainList);
        JSONObject apper = new JSONObject(map);
        return apper;
    }


    public void addData( Map<String, Object> dataToAdd, String tableName){

        String URL = rootUrl + "/api/v1/service/" + serviceName + "/db?table="+ tableName;

        final Map<String, String> headers = new HashMap<>();
        headers.put("devless-token", token);


        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        final JSONObject body = createDevlessPostBody (tableName, dataToAdd);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //tv.setText(response);
                Toast.makeText(mContext, "Post successful \nResponse: " + response , Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(mContext, "Post unsuccessful" + error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try
                {
                    return body.toString().getBytes("utf-8");
                } catch (UnsupportedEncodingException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };

        requestQueue.add(stringRequest);

    }


    public void queryData (String tableName, final  QueryResponse callback) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("devless-token", token);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String URL = rootUrl + "/api/v1/service/" + serviceName + "/db?table="+ tableName;


        final DevlessRequest stringRequest = new DevlessRequest(Request.Method.GET, URL, headers,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
                        callback.onSuccess(response);

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
                        callback.onSuccess(error.toString());

                    }


                });
        //customise RetryPolicy
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        //Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public interface QueryResponse{
        void onSuccess(String result);
    }
}
