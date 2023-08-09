package com.example.test;

import android.widget.EditText;
import android.widget.TextView;

public class checkInvalid {

    public static boolean numberIsInvalid(String num)
    {
        if (num.isEmpty() || Float.parseFloat(num) <= 0)
        {
            return true;
        }
        return false;
    }

    public static boolean numberIsValid(EditText editText)
    {
        if ((!editText.getText().toString().matches("")) || Float.parseFloat(editText.getText().toString()) <= 0)
        {
            return true;
        }
        return false;
    }

    public static boolean percentageIsValid(EditText editText)
    {
        if ((!editText.getText().toString().matches("")) && (Integer.parseInt(editText.getText().toString()) >= 0) && (Integer.parseInt(editText.getText().toString()) <= 100))
        {
            return true;
        }
        return false;
    }

    public static boolean ratioIsValid(EditText editText)
    {
        if ((!editText.getText().toString().matches("")) && (Integer.parseInt(editText.getText().toString()) >= 0))
        {
            return true;
        }
        return false;
    }

}
