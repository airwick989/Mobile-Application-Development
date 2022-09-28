package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    Spinner spToppings;
    RadioGroup rgSlice;
    CheckBox cbExtraCheese;
    CheckBox cbIncludeDelivery;
    EditText etInstructions;
    TextView txtTotalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spToppings = findViewById(R.id.spinnerTopping);
        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this, R.array.toppings,
                android.R.layout.simple_spinner_dropdown_item);
        spToppings.setAdapter(spAdapter);

        cbExtraCheese = findViewById(R.id.cbExtraCheese);
        cbIncludeDelivery = findViewById(R.id.cbIncludeDelivery);
        etInstructions = findViewById(R.id.etInstructions);
        txtTotalCost = findViewById(R.id.totalCost);

        spToppings.setEnabled(false);
        cbExtraCheese.setEnabled(false);
        cbIncludeDelivery.setEnabled(false);
        etInstructions.setEnabled(false);

        rgSlice = findViewById(R.id.rgSlice);
        rgSlice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                spToppings.setEnabled(true);
                cbExtraCheese.setEnabled(true);
                cbIncludeDelivery.setEnabled(true);
                etInstructions.setEnabled(true);

                calcTotal();
            }
        });
    }



    public double calcTotal(){
        double totalCost = 0;

        Map<String, Double> mapSlice = new HashMap<String, Double>();
        mapSlice.put("Round Pizza 6 slices - serves 3 people ($5.50)", 5.50);
        mapSlice.put("Round Pizza 8 slices - serves 4 people ($7.99)", 7.99);
        mapSlice.put("Round Pizza 10 slices - serves 5 people ($9.50)", 9.50);
        mapSlice.put("Round Pizza 12 slices - serves 6 people ($11.38)", 11.38);

        int selectedSlice = rgSlice.getCheckedRadioButtonId();
        RadioButton slice = findViewById(selectedSlice);
        totalCost += mapSlice.get(slice.getText().toString());

        Map<String, Double> mapTopping = new HashMap<String, Double>();
        mapTopping.put("None", 0.0);
        mapTopping.put("Mushrooms ($5)", 5.0);
        mapTopping.put("Sun Dried Tomatoes ($5)", 5.0);
        mapTopping.put("Chicken ($7)", 7.0);
        mapTopping.put("Ground Beef ($8)", 8.0);
        mapTopping.put("Shrimp ($10)", 10.0);
        mapTopping.put("Pineapple ($5)", 5.0);
        mapTopping.put("Steak ($9)", 9.0);
        mapTopping.put("Avocado ($5)", 5.0);
        mapTopping.put("Tuna ($5)", 5.0);
        mapTopping.put("Broccoli ($8)", 8.0);

        String selectedTopping = spToppings.getSelectedItem().toString();
        totalCost += mapTopping.get(selectedTopping);

        if(cbExtraCheese.isChecked()){
            totalCost += 5.0;
        }
        if(cbIncludeDelivery.isChecked()){
            totalCost += 5.0;
        }

        String strTotalCost = String.format("%.2f", totalCost);
        txtTotalCost.setText("$" + strTotalCost);
        return totalCost;

    }
}