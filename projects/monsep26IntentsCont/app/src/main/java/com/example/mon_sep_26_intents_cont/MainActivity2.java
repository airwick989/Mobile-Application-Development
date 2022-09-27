package com.example.mon_sep_26_intents_cont;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtName = findViewById(R.id.txtName);
    }

    public void sendDataToActivity1(View view) {
        String nameInput = txtName.getText().toString();
        if(nameInput != null){
            Intent intent = new Intent();
            intent.putExtra("KEY_NAME", nameInput); //putExtra is a function to send data through an intent in the form of a tuple, kinda like json
            setResult(RESULT_OK, intent);   //Attach a 'result ok' result to the intent we're sending
            finish();
        }
    }

    //I think the reason we don't create an explicit connection between activity2 and activity1 is because this intent can be handled by any other activity, and the only other activity is activity1 OR maybe it's because it gets sent to the previous activity
}