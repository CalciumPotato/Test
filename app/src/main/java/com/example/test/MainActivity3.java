package com.example.test;

import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity3 extends AppCompatActivity {

    // 0. Initialize
    private EditText input_amount_customBD;
    private EditText input_number_people;
    private LinearLayout list_pcnt;
    private TextView tv_person_pcnt;
    private TextView tv_text;
    private Button btn_confirm_people;
    private Button btn_mode;
    private Button btn_calc_customBD;

    private boolean modePcnt = true;
    private int numPeople;
    private double totalBill;
    private double result[];
    private String input_totalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        // 1. Bridging between the components in XML to the program
        input_amount_customBD = findViewById(R.id.input_amount_customBD);
        input_number_people = findViewById(R.id.input_number_people);
        list_pcnt = findViewById(R.id.list_pcnt);
        btn_confirm_people = findViewById(R.id.btn_confirm_people);
        tv_person_pcnt = findViewById(R.id.tv_person_pcnt);
        tv_text = findViewById(R.id.tv_text);
        btn_mode = findViewById(R.id.btn_mode);
        btn_calc_customBD = findViewById(R.id.btn_calc_customBD);


        // 2. Define the listener/action
        btn_confirm_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (modePcnt) {
                    confirmRow_pcnt();
                }
                else {
                    confirmRow_ratio();
                }
            }
        });

        btn_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeChange();
            }
        });

        btn_calc_customBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modePcnt) {
                    calcPercentageBD();
                }
                else {
                    calcRatioBD();
                }
            }
        });
    }

    private void modeChange() {
        // Add if-else: Check current mode is percentage/ratio
        // if ratio mode, change to percentage, vice versa
        if (modePcnt) {
            tv_person_pcnt.setText("Ratio each person:");
            tv_text.setText("RATIO");
            modePcnt = false;
        }
        else {
            tv_person_pcnt.setText("Percentage each person:");
            tv_text.setText("PCNT");
            modePcnt = true;
        }
    }

    private void confirmRow_pcnt() {

        input_totalBill = input_amount_customBD.getText().toString();
        String input_numPeople = input_number_people.getText().toString();

        if (checkInvalid.numberIsInvalid(input_totalBill)) {
            tv_text.setText("$ ERR");
        } else if (checkInvalid.numberIsInvalid(input_numPeople)) {
            tv_text.setText("NUM ERR");
        } else {

            // Variables for calculation:
            totalBill = Double.parseDouble(input_totalBill);
            numPeople = Integer.parseInt(input_numPeople);

            // Remove previously added fields
            list_pcnt.removeAllViews();

            for (int i = 0; i < numPeople; i++) {

                TableRow row_pcnt = new TableRow(MainActivity3.this);

                TextView tv_index = new TextView(MainActivity3.this);
                formatView.tv_index(tv_index);
                tv_index.setText((i + 1) + ".");

                EditText input_pcnt = new EditText(MainActivity3.this);
                formatView.input_pcnt(input_pcnt);
                input_pcnt.setHintTextColor(getColor(R.color.gray));
                input_pcnt.setHint("Percentage " + (i + 1));

                TextView output_amount_customBD = new TextView(MainActivity3.this);
                formatView.output_amount_customBD(output_amount_customBD);

                Button btn_clr = new Button(MainActivity3.this);
                btn_clr.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.vigna_red)));
                formatView.btn_clr(btn_clr);

                btn_clr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        input_pcnt.setText("0");
                    }
                });

                list_pcnt.addView(row_pcnt);
                row_pcnt.addView(tv_index);
                row_pcnt.addView(input_pcnt);
                row_pcnt.addView(output_amount_customBD);
                row_pcnt.addView(btn_clr);
            }

            // Add a "Calculate Split" button after the percentage fields
//            Button buttonCalculateSplit = new Button(MainActivity3.this);
//            buttonCalculateSplit.setText("Calculate Split");
        }
    }

    private void confirmRow_ratio() {

        input_totalBill = input_amount_customBD.getText().toString();
        String input_numPeople = input_number_people.getText().toString();

        if (checkInvalid.numberIsInvalid(input_totalBill)) {
            tv_text.setText("$ ERR");
        } else if (checkInvalid.numberIsInvalid(input_numPeople)) {
            tv_text.setText("NUM ERR");
        } else {

            // Variables for calculation:
            totalBill = Double.parseDouble(input_totalBill);
            numPeople = Integer.parseInt(input_numPeople);

            // Remove previously added fields
            list_pcnt.removeAllViews();

            for (int i = 0; i < numPeople; i++) {

                TableRow row_pcnt = new TableRow(MainActivity3.this);

                TextView tv_index = new TextView(MainActivity3.this);
                formatView.tv_index(tv_index);
                tv_index.setText((i + 1) + ".");

                EditText input_ratio = new EditText(MainActivity3.this);
                formatView.input_pcnt(input_ratio);
                input_ratio.setHintTextColor(getColor(R.color.gray));
                input_ratio.setHint("Ratio " + (i + 1));

                TextView output_amount_customBD = new TextView(MainActivity3.this);
                formatView.output_amount_customBD(output_amount_customBD);

                Button btn_clr = new Button(MainActivity3.this);
                btn_clr.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.vigna_red)));
                formatView.btn_clr(btn_clr);

                btn_clr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        input_ratio.setText("0");
                    }
                });

                list_pcnt.addView(row_pcnt);
                row_pcnt.addView(tv_index);
                row_pcnt.addView(input_ratio);
                row_pcnt.addView(output_amount_customBD);
                row_pcnt.addView(btn_clr);
            }

            // Add a "Calculate Split" button after the percentage fields
//            Button buttonCalculateSplit = new Button(MainActivity3.this);
//            buttonCalculateSplit.setText("Calculate Split");
        }
    }

    private void calcPercentageBD() {

        input_totalBill = input_amount_customBD.getText().toString();
        totalBill = Double.parseDouble(input_totalBill);
        result = new double[numPeople];
        int totalPcnt = 0;

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

                        if (checkInvalid.percentageIsValid(editText))
                        {
                            int editTextValue = Integer.parseInt(editText.getText().toString());
                            tv_text.setText("" + editTextValue);
                            totalPcnt += editTextValue;

                            result[i] = totalBill * editTextValue / 100;
                        } else {
                            editText.setText("0");
                        }
                    }
                }
            }
        }

            // Sum and check sum of percentages
            // If total percentage is 100%, print the individual results
            if (totalPcnt != 100) {
                tv_text.setText("% ERR");
            } else {
                // Limit to 2 decimal places
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
                                textView.setText("" + nf.format(result[j]));
                            }
                        }
                    }
                }
                tv_text.setText("PCNT");
            }
        }

    private void calcRatioBD() {

        input_totalBill = input_amount_customBD.getText().toString();
        totalBill = Double.parseDouble(input_totalBill);
        int[] ratio = new int[numPeople];   // Store input ratio
        result = new double[numPeople];
        int totalRatio = 0;

        // Get all ratio
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

                        if (checkInvalid.ratioIsValid(editText))
                        {
                            int editTextValue = Integer.parseInt(editText.getText().toString());
                            tv_text.setText("" + editTextValue);
                            ratio[i] = editTextValue;
                            totalRatio += editTextValue;

                        } else {
                            editText.setText("0");
                        }
                    }
                }
            }
        }

        // Sum and check sum of ratio
        // If total ratio <= 0, print error
        if (totalRatio <= 0) {
            tv_text.setText(": ERR");
        } else {
            // Limit to 2 decimal places
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
                            result[j] = totalBill * ratio[j] / totalRatio;

                            TextView textView = (TextView) childView;
                            textView.setText("" + nf.format(result[j]));
                        }
                    }
                }
            }
            tv_text.setText("RATIO");
        }
    }
    }

