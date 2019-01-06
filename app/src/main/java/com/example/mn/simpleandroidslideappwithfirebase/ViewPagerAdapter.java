package com.example.mn.simpleandroidslideappwithfirebase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<DataBaseGift> dataBaseGiftArrayList = new ArrayList<>();
    private ArrayList<String> dataBaseGiftsKeysArrayList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, ArrayList<String> dataBaseGiftsKeysArrayList, ArrayList<DataBaseGift> dataBaseGiftArrayList , DatabaseReference myRef){
        super(fm);
        this.dataBaseGiftArrayList=dataBaseGiftArrayList;
        this.dataBaseGiftsKeysArrayList=dataBaseGiftsKeysArrayList;
    }
    @Override
    public Fragment getItem(int i) {

        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",dataBaseGiftArrayList.get(i).getTitle());
        bundle.putString("id",dataBaseGiftsKeysArrayList.get(i));
        i++;
        viewPagerFragment.setArguments(bundle);
        return viewPagerFragment;
    }

    @Override
    public int getCount() {
        return dataBaseGiftArrayList.size();
    }


}

