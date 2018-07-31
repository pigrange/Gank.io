package com.pigrange.Gank.Utils;

import android.content.Context;
import android.content.Intent;

import com.pigrange.Gank.Activity.ShowZhiHuNews;
import com.pigrange.Gank.Activity.WebViewActivity;
import com.pigrange.Gank.Activity.ZhiHuDailyActivity;
import com.pigrange.Gank.Model.ZhiHuNews;

public class JumpUtil {
    public static final String HOME_PAGE = "my_home_page";
    public static final String SHOW_GANHUO = "show_ganhuo";

    public static void showHomePage(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", "https://github.com/pigrange");
        context.startActivity(intent);
    }

    public static void startWebView(Context context, String TAG,String url,String title){
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    public static void startZhiHuNews(Context context, String id,int date){
        Intent intent = new Intent(context, ShowZhiHuNews.class);
        intent.putExtra("id",id);
        intent.putExtra("date",date);
        context.startActivity(intent);
    }

    public static void startZhiHuDaily(Context context){
        Intent intent = new Intent(context, ZhiHuDailyActivity.class);
        context.startActivity(intent);
    }

}
