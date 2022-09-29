package com.example.sep_28_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter("com.example.sep_18_broadcastsender");
        BroadcastReceiver broadcastReceiver = new MyMessageReceiver();
        registerReceiver(broadcastReceiver, intentFilter);
    }
}