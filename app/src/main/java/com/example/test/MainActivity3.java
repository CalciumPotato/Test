package com.example.test;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity3 extends AppCompatActivity {

    // 0. Initialize
    private EditText input_amount_customBD;
    private EditText input_number_people;
    private LinearLayout list_pcnt;
    private TableRow row_pcnt;
    private TextView output_amount_customBD;
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
        setContentView(R.layout.activity_main3);


        // 1. Bridging between the components in XML to the program
        input_amount_customBD = findViewById(R.id.input_amount_customBD);
        input_number_people = findViewById(R.id.input_number_people);
        list_pcnt = findViewById(R.id.list_pcnt);
        btn_confirm_people = findViewById(R.id.btn_confirm_people);
        //output_amount_customBD = findViewById(R.id.output_amount_customBD);
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
                calcPercentageBD();
            }
        });
    }

    private void confirmRow() {

        input_totalBill = input_amount_customBD.getText().toString();
        String input_numPeople = input_number_people.getText().toString();

        if (checkInvalid.checkNumber(input_totalBill)) {
            //checkInvalid.outputNumberInvalidity(output_amount_customBD, "bill amount");
            tv_text.setText("$ ERR");
        } else if (checkInvalid.checkNumber(input_numPeople)) {
            //checkInvalid.outputNumberInvalidity(output_amount_customBD, "number of people");
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
                tv_index.setTextColor(Color.WHITE);
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setPadding(10, 0, 0, 0);
                tv_index.setInputType(InputType.TYPE_CLASS_NUMBER);  // android:inputType="number"
                tv_index.setTextSize(18);
                tv_index.setText((i + 1) + ".");

                EditText input_pcnt = new EditText(MainActivity3.this);
                input_pcnt.setEms(6);
                input_pcnt.setPadding(10, 12, 10, 12);
                input_pcnt.setInputType(InputType.TYPE_CLASS_NUMBER);
                input_pcnt.setTextColor(Color.WHITE);
                input_pcnt.setHintTextColor(getColor(R.color.gray));
                input_pcnt.setHint("Percentage " + (i + 1));

                TextView output_amount_customBD = new TextView(MainActivity3.this);
                output_amount_customBD.setEms(6);
                output_amount_customBD.setGravity(Gravity.RIGHT);
                output_amount_customBD.setPadding(0, 0, 8, 0);
                output_amount_customBD.setTextColor(Color.WHITE);
                output_amount_customBD.setTextSize(22);
                output_amount_customBD.setTypeface(Typeface.DEFAULT_BOLD);
                output_amount_customBD.setText("0");     // Individual result

                Button btn_clr = new Button(MainActivity3.this);
                //btn_clr.setBackgroundColor(getColor(R.color.vigna_red));
                btn_clr.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.vigna_red)));
                btn_clr.setTextColor(Color.WHITE);
                btn_clr.setText("Clear");

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

    private void calcPercentageBD() {

        double[] percentages = new double[numPeople];
        result = new double[numPeople];
        int totalPcnt = 0;

        // Get all percentages
        for (int i = 0; i < numPeople; i++)             // <-- Check for error
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

                        if (checkInvalid.checkPercentage(editText))
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
            if (totalPcnt != 100) {
                tv_text.setText("% ERR");
            } else {
                double totalAmount = Double.parseDouble(input_totalBill);

                for (int j = 0; j < numPeople; j++) {
                    //result[j] = totalAmount * percentages[j] / 100.0;

                    View view = list_pcnt.getChildAt(j);
                    if (view instanceof TableRow) {
                        TableRow tableRow = (TableRow) view;
                        int numTextView = tableRow.getChildCount();

                        for (int k = 0; k < numTextView; k++) {
                            View childView = tableRow.getChildAt(j+2);  // <-- Not here

                            if (childView instanceof TextView) {
                                TextView textView = (TextView) childView;
                                textView.setText("" + result[j]);           // <-- Almost there!!! Don't know why result display will shift right 1 view

                            }
                        }
                    }
                }

                tv_text.setText("100%");
            }

        }
    }

