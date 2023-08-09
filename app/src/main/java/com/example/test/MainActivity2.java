package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity2 extends AppCompatActivity {

    // Initialize buttons
    private EditText input_amount_equalBD;
    private EditText input_people_equalBD;
    private Button btn_calc_equalBD;
    private TextView output_amount_equalBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 1. Bridging between the components in XML to the program
        input_amount_equalBD = findViewById(R.id.input_amount_equalBD);
        input_people_equalBD = findViewById(R.id.input_people_equalBD);
        btn_calc_equalBD = findViewById(R.id.btn_calc_equalBD);
        output_amount_equalBD = findViewById(R.id.output_amount_equalBD);

        // 2. Define the listener/action
        btn_calc_equalBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEqualBD();
            }
        });
    }

    private void calcEqualBD() {
        String billAmount = input_amount_equalBD.getText().toString();    // Get input: Bill amount
        String numberPeople = input_people_equalBD.getText().toString();    // Get input: number of people
        // Need to use toString() in order to check using isEmpty()

        // Check input: Empty? 0?
        if (checkInvalid.numberIsInvalid(billAmount)) {
            output_amount_equalBD.setText("$ ERR");

            Toast toast = Toast.makeText(this, "Invalid bill amount. Please enter a proper bill amount.", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
        }
        else if (checkInvalid.numberIsInvalid(numberPeople)) {
            output_amount_equalBD.setText("NUM ERR");

            Toast toast = Toast.makeText(this, "Invalid number of people. Please enter a proper number of people.", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
        }
        else {
            // Done checking, convert Strings to numbers
            NumberFormat nf= NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);

            float amt = Float.parseFloat(billAmount);
            int people = Integer.parseInt(numberPeople);

            // Calculation:
            float result = amt / people;

            output_amount_equalBD.setText("" + nf.format(result));
        }
    }
}