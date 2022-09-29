package com.example.sep_18_broadcastsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    //Broadcasters and receivers are for sending information from one app to another
    //Generally this information should be encrypted, but for general information it's okay
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.example.sep_18_broadcastsender"); //broadcast sender
                intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES); //set the signal
                sendBroadcast(intent); //sender is ready; intent; sent as a signal
            }
        });
    }
}