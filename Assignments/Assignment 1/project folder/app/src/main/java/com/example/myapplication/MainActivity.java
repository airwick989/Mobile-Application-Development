package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spToppings;
    RadioGroup rgSlice;
    CheckBox cbExtraCheese;
    CheckBox cbIncludeDelivery;
    EditText etInstructions;
    TextView txtTotalCost;
    EditText etEmail;
    EditText etPhone;
    EditText etAddress;
    EditText etName;
    Map<String, String> orderDetails = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spToppings = findViewById(R.id.spinnerTopping);
        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this, R.array.toppings,
                android.R.layout.simple_spinner_dropdown_item);
        spToppings.setAdapter(spAdapter);
        spToppings.setOnItemSelectedListener(this);

        cbExtraCheese = findViewById(R.id.cbExtraCheese);
        cbIncludeDelivery = findViewById(R.id.cbIncludeDelivery);
        etInstructions = findViewById(R.id.etInstructions);
        txtTotalCost = findViewById(R.id.totalCost);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        etAddress = findViewById(R.id.etAddress);
        etName = findViewById(R.id.etName);

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

        cbExtraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calcTotal();
            }
        });

        cbIncludeDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calcTotal();
            }
        });
    }



    private double calcTotal(){
        double totalCost = 0.00;

        if (rgSlice.getCheckedRadioButtonId() != -1)
        {
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

            orderDetails.clear();

            if(cbExtraCheese.isChecked()){
                totalCost += 5.0;
                orderDetails.put("Cheese", "Selected ($5)");
            }
            else{
                orderDetails.put("Cheese", "Not Selected");
            }
            if(cbIncludeDelivery.isChecked()){
                totalCost += 5.0;
                orderDetails.put("Delivery", "Selected ($5)");
            }
            else{
                orderDetails.put("Delivery", "Not Selected");
            }

            String strTotalCost = String.format("%.2f", totalCost);
            txtTotalCost.setText("$" + strTotalCost);

            orderDetails.put("Slices", slice.getText().toString());
            orderDetails.put("Topping", selectedTopping);
            if(etInstructions.getText().toString() == "" || etInstructions.getText().toString() == null){
                orderDetails.put("Instructions", "None");
            }
            else{
                orderDetails.put("Instructions", etInstructions.getText().toString());
            }
        }

        return totalCost;

    }



    private boolean validateForm(){
        boolean validEmail = false;
        boolean validPhone = false;
        boolean validAddress = false;
        boolean validName = false;
        boolean validPizza = false;

        //Validate email
        if(!TextUtils.isEmpty(etEmail.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()){
            validEmail = true;
        }
        else{
            Toast.makeText(this, "Please check the format of your email", Toast.LENGTH_SHORT).show();
        }

        //Validate Phone Number
        String txtPhone = etPhone.getText().toString().replaceAll("[^0-9]", "");
        if(txtPhone.length() < 10){
            Toast.makeText(this, "Phone number must be at least 10 digits", Toast.LENGTH_SHORT).show();
        }
        else {
            validPhone = true;
        }

        //Validate Address
        if(etAddress.getText().toString().matches("") || etAddress.getText().toString() == null){
            Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show();
        }
        else{
            validAddress = true;
        }

        //Validate Name
        if(etAddress.getText().toString().matches("") || etAddress.getText().toString() == null){
            Toast.makeText(this, "Please enter a Name", Toast.LENGTH_SHORT).show();
        }
        else{
            validName = true;
        }

        //Validate Pizza selection
        if(rgSlice.getCheckedRadioButtonId() != -1){
            validPizza = true;
        }
        else{
            Toast.makeText(this, "Please at least select a pizza", Toast.LENGTH_SHORT).show();
        }

        return validEmail && validPhone && validAddress && validName && validPizza;
    }



    public void submitForm(View view){
        if(validateForm()){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            //orderDetails.get(<Slices, Topping, Cheese, Delivery, Instructions>);
            intent.putExtra("keySlices", orderDetails.get("Slices"));
            intent.putExtra("keyTopping", orderDetails.get("Topping"));
            intent.putExtra("keyCheese", orderDetails.get("Cheese"));
            intent.putExtra("keyDelivery", orderDetails.get("Delivery"));
            intent.putExtra("keyInstructions", orderDetails.get("Instructions"));
            intent.putExtra("keyName", etName.getText().toString());
            intent.putExtra("keyAddress", etAddress.getText().toString());
            intent.putExtra("keyPhone", etPhone.getText().toString());
            intent.putExtra("keyEmail", etEmail.getText().toString());
            startActivity(intent);
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        calcTotal();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}