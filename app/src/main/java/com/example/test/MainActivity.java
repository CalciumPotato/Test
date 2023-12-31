package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    // 0. Initialize buttons
    private FrameLayout btn_equalBD;
    private FrameLayout btn_customBD;
    private FrameLayout btn_amountBD;
    private FrameLayout btn_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Bridging between the components in XML to the program
        btn_equalBD = findViewById(R.id.btn_goEqualBD);
        btn_customBD = findViewById(R.id.btn_goCustomBD);
        btn_amountBD = findViewById(R.id.btn_goCombinedBD);
        btn_history = findViewById(R.id.btn_goHistory);

        // 2. Define the listener/action
        // 2.1 Button: Go to Equal Breakdown
        btn_equalBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent k = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(k);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 2.2 Button: Go to Custom Breakdown
        btn_customBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Intent k = new Intent(MainActivity.this, MainActivity3.class);
                    Intent k = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(k);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 2.3 Button: Go to Combined Breakdown
        btn_amountBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent k = new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(k);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 2.4 Button: Go to History
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent k = new Intent(MainActivity.this, MainActivity5.class);
                    startActivity(k);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


}