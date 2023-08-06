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

public class MainActivity extends AppCompatActivity {

    // Initialize buttons
    private EditText input_amount_equalBD;
    private EditText input_people_equalBD;
    private Button btn_calc_equalBD;
    private TextView output_amount_equalBD;

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
        setContentView(R.layout.activity_main);

        // 1. Bridging between the components in XML to the program
        input_amount_equalBD = findViewById(R.id.input_amount_equalBD);
        input_people_equalBD = findViewById(R.id.input_people_equalBD);
        btn_calc_equalBD = findViewById(R.id.btn_calc_equalBD);
        output_amount_equalBD = findViewById(R.id.output_amount_equalBD);
        input_amount_pcntBD = findViewById(R.id.input_amount_BD_2a);
        btn_add_custBD_pcnt = findViewById(R.id.btn_add_custBD_pcnt);
        btn_calc_custBD_pcnt = findViewById(R.id.btn_calc_custBD_pcnt);
        output_amount_custBD = findViewById(R.id.output_amount_custBD);
        input_pcnt0 = findViewById(R.id.input_pcnt0);
        scrollView_pcnt_list = findViewById(R.id.scrollView_pcnt_list);


        // 2. Define the listener/action
        btn_calc_equalBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEqualBD();
            }
        });

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
            }
        });


    }

    private void calcEqualBD() {
        String billAmount = input_amount_equalBD.getText().toString();    // Get input: Bill amount
        String numberPeople = input_people_equalBD.getText().toString();    // Get input: number of people
        // Need to use toString() in order to check using isEmpty()

        // Check input: Empty? 0?
        if (checkValid.checkNumber(billAmount)) {
            checkValid.outputNumberInvalidity(output_amount_equalBD, "bill amount");
        }
        else if (checkValid.checkNumber(numberPeople)) {
            checkValid.outputNumberInvalidity(output_amount_equalBD, "number of people");
            //output_amount_equalBD.setText("Please enter a number for number of people.");
        }
        else {
            // Done checking, convert Strings to numbers
            float amt = Float.parseFloat(billAmount);
            int people = Integer.parseInt(numberPeople);

            // Calculation:
            float result = amt / people;

            output_amount_equalBD.setText("Amount per person: " + result);
        }
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