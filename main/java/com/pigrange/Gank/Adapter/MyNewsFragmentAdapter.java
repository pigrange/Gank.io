package com.pigrange.Gank.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public abstract class MyNewsFragmentAdapter extends FragmentStatePagerAdapter{
    private int mChildCount = 0;

    public MyNewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (mChildCount > 0){
            mChildCount --;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
