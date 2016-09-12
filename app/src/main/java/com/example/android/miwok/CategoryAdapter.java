package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by udr013 on 12-9-2016.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position ==0){
            return new NumberFragment();
        }else if (position == 1){
            return new ColorsFragment();
        }else if (position == 2){
            return  new FamilyFragment();
        }else return new PhrasesFragment();

    }

    @Override
    public int getCount() {
        return 4;
    }
}
