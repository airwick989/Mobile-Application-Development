package com.example.myfirstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnGreetings(View view){
        EditText nameInput = findViewById(R.id.editTxtName);

        TextView txtMessage = findViewById(R.id.txtView1);  //We refer to the view in activity_main.xml
        //The one we gave the id txtView1
        //The R library contains all the views in our application

        txtMessage.setText("Hello " + nameInput.getText().toString());
    }
    //TODO finish task 1
}