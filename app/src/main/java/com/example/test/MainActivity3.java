package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    // Initialize buttons
    private EditText input_amount_pcntBD;
    private EditText input_pcnt0;
    private TextView output_amount_custBD;
    private Button btn_add_custBD_pcnt;
    private Button btn_calc_custBD_pcnt;
    private LinearLayout scrollView_pcnt_list;

    public int numberOfRow = 1;
    private ArrayList percentages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 1. Bridging between the components in XML to the program
        input_amount_pcntBD = findViewById(R.id.input_amount_BD_2a);
        btn_add_custBD_pcnt = findViewById(R.id.btn_add_custBD_pcnt);
        btn_calc_custBD_pcnt = findViewById(R.id.btn_calc_custBD_pcnt);
        output_amount_custBD = findViewById(R.id.output_amount_custBD);
        input_pcnt0 = findViewById(R.id.input_pcnt1);
        scrollView_pcnt_list = findViewById(R.id.scrollView_pcnt_list);

        // 2. Define the listener/action
        btn_calc_custBD_pcnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcPercentageBD();
            }
        });

        //final Button Add_button = (Button) findViewById(R.id.btn);
        btn_add_custBD_pcnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Float> percentages = new ArrayList<Float>();
                addRow();   // Add new row for new person

                // Change visibility?
                // for loop all EditText, if not empty, numberOfPeople++
            }
        });
    }

    private void calcPercentageBD() {

        String billAmount = input_amount_pcntBD.getText().toString();   // Get input: Bill amount
        String percentage = input_pcnt0.getText().toString();           // Get input: Percentage
        //String numberPeople = input_people_equalBD.getText().toString();    // Get input: number of people
        // Need to use toString() in order to check using isEmpty()

        // Check input: Empty? 0?
        if (checkValid.checkNumber(billAmount)) {
            checkValid.outputNumberInvalidity(output_amount_custBD, "bill amount");
        }
        else if (checkValid.checkPercentage(percentage)) {
            checkValid.outputNumberInvalidity(output_amount_custBD, "percentage");
        }
        else {

/*
            // Create an ArrayList to store the percentage input (dynamically)
            ArrayList<Editable> list_percentage = new ArrayList<>();
            // .add(), .get(), .set(), .remove(), .clear(), .size()

            for(int i = 0; i < scrollView_pcnt_list.getChildCount() ; i++)
            {
                if (scrollView_pcnt_list.getChildAt(i) instanceof EditText)
                {
                    EditText et = (EditText)scrollView_pcnt_list.getChildAt(i);
                    list_percentage.add(et.getText());
                }
            }
*/




            // Done checking, convert Strings to numbers
            float amt = Float.parseFloat(billAmount);
            float pcnt0 = Float.parseFloat(percentage) / 100;
            //int people = Integer.parseInt(numberPeople);

            // Calculation:
            float result = amt * pcnt0;

            // Output result:
            output_amount_custBD.setText("Amount per person: " + result);
        }
    }

    public void addRow() {

        LinearLayout ll = (LinearLayout)findViewById(R.id.scrollView_pcnt_list);
        // Add an EditText
        EditText input_pcnt_custBD = new EditText(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        input_pcnt_custBD.setLayoutParams(p);
        input_pcnt_custBD.setId(numberOfRow + 1);
        input_pcnt_custBD.setHint("Enter percentage " + (numberOfRow + 1));

        ll.addView(input_pcnt_custBD);
        numberOfRow++;

        //percentages.add(pcnt);
    }

}