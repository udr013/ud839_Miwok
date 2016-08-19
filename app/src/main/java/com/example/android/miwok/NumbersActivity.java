package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
//        String[] numbers = new String[10];
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("One");
        numbers.add("Two");
        numbers.add("Three");
        numbers.add("Four");
        numbers.add("Five");
        numbers.add("Six");
        numbers.add("Seven");
        numbers.add("Eight");
        numbers.add("Nine");
        numbers.add("Ten");

        LinearLayout numbersView = (LinearLayout) findViewById(R.id.numbersView);

        for (String nr : numbers) {
            // the "this" keyword refers to the current class as  the context (numbersActivity)
            TextView textView = new TextView(this);
            textView.setText(nr);
            numbersView.addView(textView);
            Log.v("numbers Activity", "numberArray contains: " + nr);
        }
    }
}
