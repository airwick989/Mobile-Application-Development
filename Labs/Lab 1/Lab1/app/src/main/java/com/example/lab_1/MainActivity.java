package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Add(View view){

        EditText num1 = findViewById(R.id.editTxtNum1);
        EditText num2 = findViewById(R.id.editTxtNum2);
        TextView result = findViewById(R.id.txtViewResult);

        if(isNum(num1.getText().toString()) == false || isNum(num2.getText().toString()) == false){
            Toast.makeText(MainActivity.this, "One of your inputs has a string or is null!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            result.setText("Result: " + (Double.parseDouble(num1.getText().toString()) + Double.parseDouble(num2.getText().toString())));
        }

    }//

    public void Sub(View view){

        EditText num1 = findViewById(R.id.editTxtNum1);
        EditText num2 = findViewById(R.id.editTxtNum2);
        TextView result = findViewById(R.id.txtViewResult);

        if(isNum(num1.getText().toString()) == false || isNum(num2.getText().toString()) == false){
            Toast.makeText(MainActivity.this, "One of your inputs has a string or is null!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            result.setText("Result: " + (Double.parseDouble(num1.getText().toString()) - Double.parseDouble(num2.getText().toString())));
        }

    }

    public void Mul(View view){

        EditText num1 = findViewById(R.id.editTxtNum1);
        EditText num2 = findViewById(R.id.editTxtNum2);
        TextView result = findViewById(R.id.txtViewResult);

        if(isNum(num1.getText().toString()) == false || isNum(num2.getText().toString()) == false){
            Toast.makeText(MainActivity.this, "One of your inputs has a string or is null!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            result.setText("Result: " + (Double.parseDouble(num1.getText().toString()) * Double.parseDouble(num2.getText().toString())));
        }

    }

    public void Div(View view){

        EditText num1 = findViewById(R.id.editTxtNum1);
        EditText num2 = findViewById(R.id.editTxtNum2);
        TextView result = findViewById(R.id.txtViewResult);

        if(isNum(num1.getText().toString()) == false || isNum(num2.getText().toString()) == false){
            Toast.makeText(MainActivity.this, "One of your inputs has a string or is null!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            result.setText("Result: " + (Double.parseDouble(num1.getText().toString()) / Double.parseDouble(num2.getText().toString())));
        }

    }

    private static boolean isNum(String str){
        if(str == null){
            return  false;
        }

        try{
            double num = Double.parseDouble(str);
        } catch(NumberFormatException nfe){
            return false;
        }

        return true;
    }
}