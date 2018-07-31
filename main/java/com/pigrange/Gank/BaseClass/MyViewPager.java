package com.pigrange.Gank.BaseClass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.EventLog;
import android.util.Log;
import android.view.MotionEvent;

import java.io.File;
import java.lang.reflect.Field;

public class MyViewPager extends ViewPager {
    private int mLastXIntercept;
    private int mLastYIntercept;
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        final int action = event.getAction() & MotionEvent.ACTION_MASK;

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                super.onInterceptTouchEvent(event);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX  = x - mLastXIntercept;
                int deltaY  = y - mLastYIntercept;

                Log.e("BaseViewPager","dx is "+deltaX);
                Log.e("BaseViewPager","dy is "+deltaY);
                if (Math.abs(deltaX)>Math.abs(deltaY)){
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
            default:
                break;
        }
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercepted;
    }

////
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        if (action == MotionEvent.ACTION_DOWN){
//            return false;
//        }else {
//            return true;
//        }
//    }
}
