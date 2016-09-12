package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    private AudioManager am;

    private MediaPlayer.OnCompletionListener myOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener afListener= new AudioManager.OnAudioFocusChangeListener() {


        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                Log.v("onFocusChange", "AUDIOFOCUS_LOSS_TRANSIENT");
                if (mMediaPlayer!=null) {
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                Log.v("onFocusChange", "AUDIOFOCUS_GAIN");
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                Log.v("onFocusChange", "AUDIOFOCUS_LOSS");
                mMediaPlayer.stop();
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                Log.v("onFocusChange", "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                mMediaPlayer.setVolume(2, 2);
            }
        }
    };

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            am.abandonAudioFocus(afListener);
        }
    }

    public ColorsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list,container,false);

        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Red", "Wetetti", R.drawable.color_red,R.raw.color_red));
        words.add(new Word("Green","Chokokki", R.drawable.color_green,R.raw.color_green));
        words.add(new Word("Brown","Takaakki", R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("Gray", "Topoppi", R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("Black", "kululli", R.drawable.color_black,R.raw.color_black));
        words.add(new Word("White", "Kelelli", R.drawable.color_white,R.raw.color_white));
        words.add(new Word("Dusty yellow", "Topiisә", R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("Mustard yellow","Chiwiiṭә", R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(),words, R.color.category_colors);


        ListView listView = (ListView) rootView.findViewById(R.id.wordsList);
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                releaseMediaPlayer();//this will cleanup memory used by mediaplayer
                Word word = words.get(position);

                int result = am.requestAudioFocus(afListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);//AUDIOFOCUS_GAIN_TRANSIENT cause short clips

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(myOnCompletionListener);

                }
            }
        });


        return rootView;
    }

}
