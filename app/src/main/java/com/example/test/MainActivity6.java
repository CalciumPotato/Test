package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        EditText editTextTotalBill = findViewById(R.id.editTextTotalBill);
        EditText editTextNumPeople = findViewById(R.id.editTextNumPeople);
        LinearLayout percentageContainer = findViewById(R.id.percentageContainer);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalBillStr = editTextTotalBill.getText().toString();
                String numPeopleStr = editTextNumPeople.getText().toString();

                if (!totalBillStr.isEmpty() && !numPeopleStr.isEmpty()) {
                    double totalBill = Double.parseDouble(totalBillStr);
                    int numPeople = Integer.parseInt(numPeopleStr);

                    percentageContainer.removeAllViews(); // Clear previous dynamically added fields

                    for (int i = 0; i < numPeople; i++) {
                        EditText editTextPercentage = new EditText(MainActivity6.this);
                        editTextPercentage.setHint("Enter Percentage for Person " + (i + 1));
                        percentageContainer.addView(editTextPercentage);
                    }

                    // Add a "Calculate Split" button after the percentage fields
                    Button buttonCalculateSplit = new Button(MainActivity6.this);
                    buttonCalculateSplit.setText("Calculate Split");
                    buttonCalculateSplit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            double[] percentages = new double[numPeople];
                            double totalPercentage = 0;

                            for (int i = 0; i < numPeople; i++) {
                                EditText editTextPercentage = (EditText) percentageContainer.getChildAt(i);
                                String percentageStr = editTextPercentage.getText().toString();
                                if (!percentageStr.isEmpty()) {
                                    percentages[i] = Double.parseDouble(percentageStr);
                                    totalPercentage += percentages[i];
                                }
                            }

                            if (totalPercentage > 0) {
                                double totalAmount = Double.parseDouble(totalBillStr);
                                for (int i = 0; i < numPeople; i++) {
                                    double individualAmount = (percentages[i] / totalPercentage) * totalAmount;
                                    // Display or use individualAmount as needed
                                }
                            } else {
                                // Handle error: Total percentages are not entered or are zero
                                // Display an error message or a Toast
                            }
                        }
                    });

                    percentageContainer.addView(buttonCalculateSplit);
                } else {
                    // Handle empty input fields
                    // Display an error message or a Toast
                }
            }
        });
    }
}