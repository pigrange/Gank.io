package com.pigrange.rxjavaretrofittest.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pigrange.rxjavaretrofittest.Fragment.GanHuoFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabArray;
    private Context mContext;
    private List<Fragment> mFragments;

    public ViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments, String[] tabs) {
        super(fm);
        this.tabArray = tabs;
        this.mContext = context;
        this.mFragments = fragments;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabArray[position%tabArray.length];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
