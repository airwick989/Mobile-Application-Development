package com.example.mon_sep_26_intents_cont;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;


    //Below will get the result of one activity and put it in another activity??
    //Its basically used to get the data passed from activity2 to activity1
    //results is the data being passed from activity2 to activity1
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null){
                if(result.getData() != null && result.getResultCode() == RESULT_OK){
                    textView.setText(result.getData().getStringExtra("KEY_NAME"));  //When we send strings (data) from activity to activity, we give them a key for identification purposes. We did this before
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                startForResult.launch(intent);
                //startActivity(intent);
            }
        });
    }

}