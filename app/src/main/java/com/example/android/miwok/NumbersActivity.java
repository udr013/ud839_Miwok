package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer m;
    //globel to decrease mem use, now we can use it everytime without needing to create a new object everytime
    private MediaPlayer.OnCompletionListener myOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
//        String[] numbers = new String[10];
        final ArrayList<Word> numbers = new ArrayList<>();
        numbers.add(new Word("One", "Lutti", R.drawable.number_one,R.raw.number_one));
        numbers.add(new Word("Two","Otiiko", R.drawable.number_two, R.raw.number_two));
        numbers.add(new Word("Three","Tolukoosu", R.drawable.number_three, R.raw.number_three));
        numbers.add(new Word("Four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbers.add(new Word("Five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbers.add(new Word("Six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbers.add(new Word("Seven", "kenakaku", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new Word("Eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new Word("Nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new Word("Ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = numbers.get(position);
                m = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                m.start();
                m.setOnCompletionListener(myOnCompletionListener);

            }
        });


    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (m != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            m.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            m = null;
        }
    }
}
