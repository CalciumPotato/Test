package com.example.test;

import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity4 extends AppCompatActivity {

    // 0. Initialize
    private EditText input_amount_customBD;
    private EditText input_number_people;
    private LinearLayout list_pcnt;
    private TextView tv_text;
    private Button btn_confirm_people;
    private Button btn_calc_customBD;

    private int numPeople;
    private double totalBill;
    private double result[];
    private String input_totalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        // 1. Bridging between the components in XML to the program
        input_amount_customBD = findViewById(R.id.input_amount_customBD);
        input_number_people = findViewById(R.id.input_number_people);
        list_pcnt = findViewById(R.id.list_pcnt);
        btn_confirm_people = findViewById(R.id.btn_confirm_people);
        tv_text = findViewById(R.id.tv_text);
        btn_calc_customBD = findViewById(R.id.btn_calc_customBD);


        // 2. Define the listener/action
        btn_confirm_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmRow();
            }
        });

        btn_calc_customBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcAmountBD();
            }
        });
    }

    private boolean confirmRow() {

        input_totalBill = input_amount_customBD.getText().toString();
        String input_numPeople = input_number_people.getText().toString();

        // Check input validity and notify user
        if (checkInvalid.numberIsInvalid(input_totalBill)) {
            tv_text.setText("$ ERR");

            Toast toast = Toast.makeText(this, "Invalid bill amount. Please enter a proper bill amount.", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if(v != null) v.setGravity(Gravity.CENTER);
            toast.show();

            return false;
        } else if (checkInvalid.numberIsInvalid(input_numPeople)) {
            tv_text.setText("NUM ERR");

            Toast toast = Toast.makeText(this, "Invalid number of people. Please enter a proper number of people.", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if(v != null) v.setGravity(Gravity.CENTER);
            toast.show();

            return false;
        } else {

            // Variables for calculation:
            totalBill = Double.parseDouble(input_totalBill);
            numPeople = Integer.parseInt(input_numPeople);

            // Remove previously added fields
            list_pcnt.removeAllViews();

            // Construct views according to number of people
            for (int i = 0; i < numPeople; i++) {

                TableRow row_pcnt = new TableRow(MainActivity4.this);

                TextView tv_index = new TextView(MainActivity4.this);
                formatView.tv_index(tv_index);
                tv_index.setText((i + 1) + ".");

                EditText input_amount = new EditText(MainActivity4.this);
                formatView.input_amount(input_amount);
                input_amount.setHintTextColor(getColor(R.color.gray));
                input_amount.setHint("Amount " + (i + 1));

                TextView output_amount_customBD = new TextView(MainActivity4.this);
                formatView.output_amount_customBD(output_amount_customBD);

                Button btn_clr = new Button(MainActivity4.this);
                btn_clr.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.vigna_red)));
                formatView.btn_clr(btn_clr);

                // Set listener for button
                btn_clr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        input_amount.setText("0");
                    }
                });

                // Add views
                list_pcnt.addView(row_pcnt);
                row_pcnt.addView(tv_index);
                row_pcnt.addView(input_amount);
                row_pcnt.addView(output_amount_customBD);
                row_pcnt.addView(btn_clr);
            }
            return true;
        }
    }

    private void calcAmountBD() {

        input_totalBill = input_amount_customBD.getText().toString();
        totalBill = Double.parseDouble(input_totalBill);
        result = new double[numPeople];
        double remBill = 0;

        // Get all percentages
        for (int i = 0; i < numPeople; i++)
        {
            View view = list_pcnt.getChildAt(i);
            if (view instanceof TableRow)
            {
                TableRow tableRow = (TableRow) view;
                int numEditTexts = tableRow.getChildCount();

                for (int j = 0; j < numEditTexts; j++)
                {
                    View childView = tableRow.getChildAt(j);

                    if (childView instanceof EditText)
                    {
                        EditText editText = (EditText) childView;

                        if (checkInvalid.numberIsValid(editText))
                        {
                            double editTextValue = Double.parseDouble(editText.getText().toString());

                            if (i == 0) {
                                result[i] = totalBill - editTextValue;
                                remBill = result[i];
                            }
                            else {
                                result[i] = result[i-1] - editTextValue;
                                remBill = result[i];
                            }


                        } else {
                            editText.setText("AMT ERR");
                        }
                    }
                }
            }
        }

        // Sum and check sum of percentages
        // If total percentage is 100%, print the individual results

        // Limit result to 2 decimal places
        NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

        for (int j = 0; j < numPeople; j++) {

            View view = list_pcnt.getChildAt(j);
            if (view instanceof TableRow) {
                TableRow tableRow = (TableRow) view;
                int numChild = tableRow.getChildCount();     // 4 children

                // Print result at child index 2
                for (int k = 0; k < numChild; k++) {
                    View childView = tableRow.getChildAt(2);

                    if (childView instanceof TextView) {
                        TextView textView = (TextView) childView;
                        textView.setText("Remain: " + nf.format(result[j]));
                    }
                }
            }
        }

        if (remBill != 0) {
            tv_text.setText("Remain: " + nf.format(remBill));
        }
        else {
            tv_text.setText("DONE");
        }
    }

}