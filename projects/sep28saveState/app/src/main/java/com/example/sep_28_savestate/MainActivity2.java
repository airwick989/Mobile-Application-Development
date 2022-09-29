package com.example.sep_28_savestate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String name = "myApp";
        SharedPreferences prefs = getSharedPreferences(name, Activity.MODE_PRIVATE);
        String firstName = prefs.getString("firstName", null);
        String lastName = prefs.getString("lastName", null);
        Toast.makeText(this, "Firstname: " + firstName + ",  Lastname: " + lastName, Toast.LENGTH_LONG).show();

        webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/blank.html");
        //folder called asset in project folder
    }
}