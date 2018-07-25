package com.pigrange.rxjavaretrofittest.Utils;

import android.content.Context;
import android.content.Intent;

import com.pigrange.rxjavaretrofittest.Activity.ShowZhiHuNews;
import com.pigrange.rxjavaretrofittest.Activity.WebViewActivity;
import com.pigrange.rxjavaretrofittest.Activity.ZhiHuDailyActivity;
import com.pigrange.rxjavaretrofittest.Model.ZhiHuNews;

public class JumpUtil {
    public static final String HOME_PAGE = "my_home_page";
    public static final String SHOW_GANHUO = "show_ganhuo";

    public static void showHomePage(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", "https://github.com/pigrange");
        context.startActivity(intent);

    }

    public static void startWebView(Context context, String TAG,String url){
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    public static void startZhiHuNews(Context context, String id){
        Intent intent = new Intent(context, ShowZhiHuNews.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    public static void startZhiHuDaily(Context context){
        Intent intent = new Intent(context, ZhiHuDailyActivity.class);
        context.startActivity(intent);
    }

}
