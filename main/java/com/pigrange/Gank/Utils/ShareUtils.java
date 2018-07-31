package com.pigrange.Gank.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.INotificationSideChannel;

import com.pigrange.Gank.R;

public class ShareUtils {
    public static void shareApp(Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //为Intent创建一个新的任务栈
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, context.getResources().getString(R.string.share_app));
        //实现一个选择器，并且创建标题
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.share_app_to_friends)));
    }

    public static void shareImage(Context context, Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/jpeg");

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, "分享图片"));
    }

    public static void shareGanhuo(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,context.getResources().getString(R.string.share_ganhuo_to_friends)+url);
        context.startActivity(Intent.createChooser(intent,"分享干货"));
    }

}
