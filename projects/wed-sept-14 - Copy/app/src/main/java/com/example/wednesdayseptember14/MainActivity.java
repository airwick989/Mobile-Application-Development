package com.example.wednesdayseptember14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout sportLayout;
    CheckBox tennisCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tennisCheckBox = findViewById(R.id.checkBoxTennis);
        tennisCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    Toast.makeText(MainActivity.this, "Tennis is checked", Toast.LENGTH_LONG).show();
                }

            }
        });

        sportLayout = findViewById(R.id.sportLayout);

    }
}