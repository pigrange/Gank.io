package com.pigrange.rxjavaretrofittest.BaseClass;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class MyRecyclerView extends RecyclerView {
    private int mLastX;
    private int mLastY;
    private ViewGroup parent;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//        int x = (int) e.getX();
//        int y = (int) e.getY();
//
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                parent = (ViewGroup) getParent();
//                parent.requestDisallowInterceptTouchEvent(true);
//                break;
//            }
//            case MotionEvent.ACTION_HOVER_MOVE: {
//                int deltaX = Math.abs(x - mLastX);
//                int deltaY = Math.abs(y - mLastY);
//                if (deltaX > deltaY) {
//                    parent.requestDisallowInterceptTouchEvent(false);
//                }
//                break;
//            }
//            default:
//                break;
//        }
//        mLastX = x;
//        mLastY = y;
//        return super.dispatchTouchEvent(e);
//    }
}
