package com.example.test;

import android.widget.EditText;
import android.widget.TextView;

public class checkInvalid {

    public static boolean checkNumber(String num)
    {
        if (num.isEmpty() || Float.parseFloat(num) <= 0)
        {
            return true;
        }
        return false;
    }

    public static boolean checkPercentage(String pcnt)
    {
        if (pcnt.isEmpty() || Float.parseFloat(pcnt) <= 0 || Float.parseFloat(pcnt) > 100)
        {
            return true;
        }
        return false;
    }

    public static void outputNumberInvalidity(TextView tv, String str)
    {
        tv.setText("Please enter a valid " + str + ".");
    }

}
