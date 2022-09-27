package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        Button buttonSub = (Button) findViewById(R.id.buttonSub);
        buttonSub.setOnClickListener(this);
        Button buttonMul = (Button) findViewById(R.id.buttonMul);
        buttonMul.setOnClickListener(this);
        Button buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
                calculate("add");
                break;
            case R.id.buttonSub:
                calculate("sub");
                break;
            case R.id.buttonMul:
                calculate("mul");
                break;
            case R.id.buttonDiv:
                calculate("div");
                break;
            default:
                break;
        }
    }

    public void calculate(String op){
        EditText num1 = findViewById(R.id.editTxtNum1);
        EditText num2 = findViewById(R.id.editTxtNum2);
        TextView result = findViewById(R.id.txtViewResult);

        if(isNum(num1.getText().toString()) == false || isNum(num2.getText().toString()) == false){
            Toast.makeText(MainActivity.this, "One of your inputs has a string or is null!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            double n1 = Double.parseDouble(num1.getText().toString());
            double n2 = Double.parseDouble(num2.getText().toString());

            switch (op) {
                case "add":
                    result.setText("Result: " + (n1 + n2));
                    break;
                case "sub":
                    result.setText("Result: " + (n1 - n2));
                    break;
                case "mul":
                    result.setText("Result: " + (n1 * n2));
                    break;
                case "div":
                    if(n2 == 0){
                        Toast.makeText(MainActivity.this, "Divide by 0 error!", Toast.LENGTH_SHORT).show();
                        break;
                    }else {
                        result.setText("Result: " + (n1 / n2));
                    }
                    break;
                default:
                    break;
            }
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