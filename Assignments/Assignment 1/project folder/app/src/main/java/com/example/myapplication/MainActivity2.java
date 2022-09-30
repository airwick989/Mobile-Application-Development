package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    //Initialise global variables
    TextView tvSlice, tvTopping, tvCheese, tvDelivery, tvInstructions, tvCost, tvName, tvAddress, tvPhone, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Link variables to their corresponding views
        tvSlice = findViewById(R.id.tvSlice);
        tvTopping = findViewById(R.id.tvTopping);
        tvCheese = findViewById(R.id.tvCheese);
        tvDelivery = findViewById(R.id.tvDelivery);
        tvInstructions = findViewById(R.id.tvInstructions);
        tvCost = findViewById(R.id.tvCost);
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);

        //set the text of each view to their associated data as received through the intent from activity1
        tvSlice.setText(getIntent().getStringExtra("keySlices"));
        tvTopping.setText(getIntent().getStringExtra("keyTopping"));
        tvCheese.setText(getIntent().getStringExtra("keyCheese"));
        tvDelivery.setText(getIntent().getStringExtra("keyDelivery"));
        tvInstructions.setText(getIntent().getStringExtra("keyInstructions"));
        tvCost.setText("$" + getIntent().getStringExtra("keyCost"));
        tvName.setText(getIntent().getStringExtra("keyName"));
        tvAddress.setText(getIntent().getStringExtra("keyAddress"));
        tvPhone.setText(getIntent().getStringExtra("keyPhone"));
        tvEmail.setText(getIntent().getStringExtra("keyEmail"));

    }

    //If button is pressed, return to the calling activity (activity1) and finish this activity with the return result ok
    public void goBack(View view){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}