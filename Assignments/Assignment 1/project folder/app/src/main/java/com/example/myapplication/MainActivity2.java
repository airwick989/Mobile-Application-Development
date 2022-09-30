package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tvSlice, tvTopping, tvCheese, tvDelivery, tvInstructions, tvCost, tvName, tvAddress, tvPhone, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

    public void goBack(View view){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}