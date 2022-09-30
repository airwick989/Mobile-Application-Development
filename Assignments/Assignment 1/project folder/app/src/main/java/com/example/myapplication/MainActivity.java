package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

    //Initialise global variables
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

    //Hashmap of order details
    Map<String, String> orderDetails = new HashMap<String, String>();


    //When launching the intent for the confirmation screen, obtain the return result upon finishing the second activity
    //If the result is ok, display a thank you message
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null){
                        if(result.getData() != null && result.getResultCode() == RESULT_OK){
                            Toast.makeText(MainActivity.this, "Thank you for ordering!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link variables to their corresponding views
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

        //Disable other order inputs unless at least a pizza size is selected
        spToppings.setEnabled(false);
        cbExtraCheese.setEnabled(false);
        cbIncludeDelivery.setEnabled(false);
        etInstructions.setEnabled(false);

        rgSlice = findViewById(R.id.rgSlice);

        //Enable other order inputs once a pizza size is selected
        //If any order input is changed / selected, the calcTotal function is called to recalculate the total cost in real time
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


    //Calculate the total order cost and insert the current order details into the orderDetails hashmap
    private double calcTotal(){
        double totalCost = 0.00;

        //Do the following only if a radiobutton is checked (if a pizza size is selected)
        if (rgSlice.getCheckedRadioButtonId() != -1)
        {
            //Map pizza size selections to prices and add it to the total cost
            Map<String, Double> mapSlice = new HashMap<String, Double>();
            mapSlice.put("Round Pizza 6 slices - serves 3 people ($5.50)", 5.50);
            mapSlice.put("Round Pizza 8 slices - serves 4 people ($7.99)", 7.99);
            mapSlice.put("Round Pizza 10 slices - serves 5 people ($9.50)", 9.50);
            mapSlice.put("Round Pizza 12 slices - serves 6 people ($11.38)", 11.38);

            int selectedSlice = rgSlice.getCheckedRadioButtonId();
            RadioButton slice = findViewById(selectedSlice);
            totalCost += mapSlice.get(slice.getText().toString());

            //Map the topping selections to prices and add it to the total cost
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

            //Clear the orderDetails hashmap to insert updated values for the updated order
            orderDetails.clear();

            //All statements below are for inserting order details into the orderDetails hashmap
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
            orderDetails.put("Cost", strTotalCost);

            orderDetails.put("Slices", slice.getText().toString());
            orderDetails.put("Topping", selectedTopping);
        }

        return totalCost;

    }


    //function for validating form input fields
    private boolean validateForm(){
        //initially set flags to false
        boolean validEmail = false;
        boolean validPhone = false;
        boolean validAddress = false;
        boolean validName = false;
        boolean validPizza = false;

        //Validate email by checking it follows the pattern '<something>@<something>.<something>'
        if(!TextUtils.isEmpty(etEmail.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()){
            validEmail = true;
        }
        else{
            Toast.makeText(this, "Please check the format of your email", Toast.LENGTH_SHORT).show();
        }

        //Validate Phone Number by ensuring it is at least 10 digits
        String txtPhone = etPhone.getText().toString().replaceAll("[^0-9]", "");
        if(txtPhone.length() < 10){
            Toast.makeText(this, "Phone number must be at least 10 digits", Toast.LENGTH_SHORT).show();
        }
        else {
            validPhone = true;
        }

        //Validate Address by ensuring the field is not empty
        if(etAddress.getText().toString().matches("") || etAddress.getText().toString() == null){
            Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show();
        }
        else{
            validAddress = true;
        }

        //Validate Name by ensuring the field is not empty
        if(etAddress.getText().toString().matches("") || etAddress.getText().toString() == null){
            Toast.makeText(this, "Please enter a Name", Toast.LENGTH_SHORT).show();
        }
        else{
            validName = true;
        }

        //Validate Pizza selection by ensuring at least a size is selected (the other order inputs have a default value once the size is selected)
        if(rgSlice.getCheckedRadioButtonId() != -1){
            validPizza = true;
        }
        else{
            Toast.makeText(this, "Please at least select a pizza", Toast.LENGTH_SHORT).show();
        }

        //Ensure all form inputs are validated and return the result
        return validEmail && validPhone && validAddress && validName && validPizza;
    }


    //If the form inputs are all valid, place all the necessary order and contact details into the intent and launch it
    public void submitForm(View view){
        if(validateForm()){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("keySlices", orderDetails.get("Slices"));
            intent.putExtra("keyTopping", orderDetails.get("Topping"));
            intent.putExtra("keyCheese", orderDetails.get("Cheese"));
            intent.putExtra("keyDelivery", orderDetails.get("Delivery"));
            intent.putExtra("keyInstructions", etInstructions.getText().toString());
            intent.putExtra("keyCost", orderDetails.get("Cost"));
            intent.putExtra("keyName", etName.getText().toString());
            intent.putExtra("keyAddress", etAddress.getText().toString());
            intent.putExtra("keyPhone", etPhone.getText().toString());
            intent.putExtra("keyEmail", etEmail.getText().toString());
            startForResult.launch(intent);
        }
    }


    //Used to call calcTotal function for when an item is selected in the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        calcTotal();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}