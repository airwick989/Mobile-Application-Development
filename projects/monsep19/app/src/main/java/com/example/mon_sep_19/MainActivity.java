package com.example.mon_sep_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spCities; //reference to an object
    RadioGroup rgUni;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spCities = findViewById(R.id.spinner); //static method
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cities, android.R.layout.simple_spinner_dropdown_item);
        //An array adapter may hold any data type, just has to be an array (<T> means generic class)
        //We wanna link this adapter to the array we created in the resources
        //The resource is the final parameter
        //The first parameter is the context, defining what class we are talking about. "this" means we are referring to MainActivity.java

        spCities.setAdapter(adapter); //setAdapter links the spinner to the string array of cities
        spCities.setOnItemSelectedListener(this);

        rgUni = findViewById(R.id.rgUni);
        rgUni.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.otu:
                        Toast.makeText(MainActivity.this, "You go to: OTU", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.uottawa:
                        Toast.makeText(MainActivity.this, "You go to: UOttawa", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.uoft:
                        Toast.makeText(MainActivity.this, "You go to: UofT", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        progressBar = findViewById(R.id.progressBar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run(){
                for (int i = 0; i < 10; i++){
                    progressBar.incrementProgressBy(i);
                    SystemClock.sleep(500);
                }
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        thread.start();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String city = spCities.getItemAtPosition(i).toString();
        Toast.makeText(this, "You selected:" + city, Toast.LENGTH_SHORT).show();;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}