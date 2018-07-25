package com.pigrange.rxjavaretrofittest.Utils;

import android.content.Context;
import android.content.Intent;

import com.pigrange.rxjavaretrofittest.R;

public class ShareUtils {
    public static void share(Context context){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //为Intent创建一个新的任务栈
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT,context.getResources().getString(R.string.share_app));
        //实现一个选择器，并且创建标题
        context.startActivity(Intent.createChooser(intent,context.getResources().getString(R.string.share_app_to_friends)));
    }


}
