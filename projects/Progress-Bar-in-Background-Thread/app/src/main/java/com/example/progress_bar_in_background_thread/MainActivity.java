package com.example.progress_bar_in_background_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    private int progress = 0;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);

        runnable = new Runnable() {
            @Override
            public void run() {
                while(progress < 100){
                    try {
                        Thread.sleep(50);
                        progress++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public void doInBackground(View v) throws InterruptedException {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();  //Waits until thread is done
        Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
    }
}