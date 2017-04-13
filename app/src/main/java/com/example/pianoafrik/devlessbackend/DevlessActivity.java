package com.example.pianoafrik.devlessbackend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pianoafrik.devlessbackend.DevlessPackage.Devless;

public class DevlessActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devless);

        tv = (TextView)findViewById(R.id.text_view);

        String rootUrl = "http://newerapper.herokuapp.com";
        String serviceName = "Grocery";
        String devlessToken = "7740b4b2303e32957a3215c344b8c21c";
        String tableName = "grocery_table";
        //TODO: format json

        Devless devless = new Devless(devlessToken, rootUrl, serviceName, this );

        devless.queryData(tableName, new Devless.QueryResponse() {
            @Override
            public void onSuccess(String result) {
                //this is great
                tv.setText(result);
            }
        });


        //Create data according to your fields
        //Map<String, Object> data = new HashMap<>();
        //data.put("name","finney nie");

        //post data by running this function
        //devless.addData(data,tableName);
    }
}
