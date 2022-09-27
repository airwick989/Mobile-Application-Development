package com.example.wed_sep_21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView nameTxtView, userNameTxtView;
    Button btnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String name = getIntent().getStringExtra("keyName");
        String userName = getIntent().getStringExtra("keyUserName");
        //Using the 2 statements above, we can extract those strings from the intent that gets placed once activity 2 is launched

        nameTxtView = findViewById(R.id.nameActivity2);
        userNameTxtView = findViewById(R.id.userNameActivity2);

        nameTxtView.setText(name);
        userNameTxtView.setText(userName);

        btnGoogle = findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.ca"));
                startActivity(intent);
            }
        });
    }
}