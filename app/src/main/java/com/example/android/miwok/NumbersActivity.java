package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
//        String[] numbers = new String[10];
        ArrayList<Word> numbers = new ArrayList<>();
        numbers.add(new Word("One", "Lutti", R.drawable.number_one));
        numbers.add(new Word("Two","Otiiko", R.drawable.number_two));
        numbers.add(new Word("Three","Tolukoosu", R.drawable.number_three));
        numbers.add(new Word("Four", "oyyisa", R.drawable.number_four));
        numbers.add(new Word("Five", "massokka", R.drawable.number_five));
        numbers.add(new Word("Six", "temmokka", R.drawable.number_six));
        numbers.add(new Word("Seven", "kenakaku", R.drawable.number_seven));
        numbers.add(new Word("Eight","kawinta", R.drawable.number_eight));
        numbers.add(new Word("Nine", "wo'e", R.drawable.number_nine));
        numbers.add(new Word("Ten", "na'aacha", R.drawable.number_ten));

        /**
         * An ArrayAdapter knows how to make a list item View for the ListView and reuses a view that's no longer visible
         * android.R.layout.simple_list_item_1 is included in the Android  FRAMEWORK package
         */


        WordAdapter itemsAdapter = new WordAdapter(this,numbers, R.color.category_numbers);

        //the dividers are included in listview, which can be styled by changing the xml
        ListView listView = (ListView) findViewById(R.id.wordsList);
//        listView.setBackgroundColor(getResources().getColor(R.color.category_numbers));
        listView.setAdapter(itemsAdapter);

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.


        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.


        /**
         * the out commented code is memory consuming, we rather use ArrayAdapter
         * */

//        LinearLayout numbersView = (LinearLayout) findViewById(R.id.numbersView);
//
//        for (String nr : numbers) {
//            // the "this" keyword refers to the current class as  the context (numbersActivity)
//            TextView textView = new TextView(this);
//            textView.setText(nr);
//            numbersView.addView(textView);
//            Log.v("numbers Activity", "numberArray contains: " + nr);
//        }
    }
}
