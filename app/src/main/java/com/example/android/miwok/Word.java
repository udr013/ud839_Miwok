package com.example.android.miwok;

/**
 * Created by udr013 on 19-8-2016.
 */
public class Word {
    private int  imageResourceId;
    private String defaultWord;
    private String miwokWord;

    public Word(String defaultWord, String miwokWord) {
        this.defaultWord = defaultWord;
       this.miwokWord = miwokWord;
    }

    public Word(int imageResourceId, String defaultWord, String miwokWord) {
        this.imageResourceId = imageResourceId;
        this.defaultWord = defaultWord;
        this.miwokWord = miwokWord;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getDefaultWord() {
        return defaultWord;
    }

    public void setDefaultWord(String defaultWord) {
        this.defaultWord = defaultWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public void setMiwokWord(String miwokWord) {
        this.miwokWord = miwokWord;
    }
}
