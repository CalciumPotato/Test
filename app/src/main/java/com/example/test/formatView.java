package com.example.test;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class formatView {

    public static void tv_index(TextView tv_index) {
        tv_index.setTextColor(Color.WHITE);
        tv_index.setGravity(Gravity.CENTER);
        tv_index.setPadding(10, 0, 0, 0);
        tv_index.setInputType(InputType.TYPE_CLASS_NUMBER);  // android:inputType="number"
        tv_index.setTextSize(18);
    }

    public static void input_pcnt(EditText input_pcnt) {
        input_pcnt.setEms(6);
        input_pcnt.setPadding(10, 12, 10, 12);
        input_pcnt.setInputType(InputType.TYPE_CLASS_NUMBER);
        input_pcnt.setTextColor(Color.WHITE);
    }

    public static void output_amount_customBD(TextView output_amount_customBD) {
        output_amount_customBD.setEms(6);
        output_amount_customBD.setGravity(Gravity.RIGHT);
        output_amount_customBD.setPadding(0, 0, 8, 0);
        output_amount_customBD.setTextColor(Color.WHITE);
        output_amount_customBD.setTextSize(22);
        output_amount_customBD.setTypeface(Typeface.DEFAULT_BOLD);
        output_amount_customBD.setText("0");     // Individual result
    }

    public static void btn_clr(Button btn_clr) {
        btn_clr.setTextColor(Color.WHITE);
        btn_clr.setText("Clear");
    }

}
