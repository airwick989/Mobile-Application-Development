package com.example.wed_sep_21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnIntent;
    EditText stdName, stdUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stdName = findViewById(R.id.stdName);
        stdUserName = findViewById(R.id.stdUserName);

        btnIntent = findViewById(R.id.btnIntent);
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = stdName.getText().toString();
                String userName = stdUserName.getText().toString();

                Intent intent = new Intent(MainActivity.this, MainActivity2.class); //"this" refers to the current class, which is why we put "class" when referring to MainActivity2
                intent.putExtra("keyName", name);
                //putextra means you can load that object with some values in an intent and send that to another activity
                //putextra
                intent.putExtra("keyUserName", userName);
                startActivity(intent);
            }
        });
    }
}