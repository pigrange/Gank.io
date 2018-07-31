package com.pigrange.Gank.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.mingle.widget.LoadingView;
import com.pigrange.Gank.Interface.GetZhihuNewsService;
import com.pigrange.Gank.Model.ZhiHuNews;
import com.pigrange.Gank.R;
import com.pigrange.Gank.Utils.HtmlUtil;
import com.pigrange.Gank.Utils.MyRetrofit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZhiHuNewsFragment extends Fragment {
    private WebView mWebView;
    private LoadingView mLoadingView;
    private RelativeLayout noWIFI;
    private String id;
    private CallBackx mCallback;

    public void setCallback(CallBackx callback){
        this.mCallback = callback;
    }

    public static ZhiHuNewsFragment getInstance(int id) {
        ZhiHuNewsFragment fragment = new ZhiHuNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", String.valueOf(id));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString("id");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_zhihu_news, container, false);
        mWebView = v.findViewById(R.id.zhihu_news_webView);
        mLoadingView = v.findViewById(R.id.zhihu_news_loadingView);
        noWIFI = v.findViewById(R.id.zhihu_news_noWIFI);
        loadData();
        initWebView();
        return v;
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
    }

    private void loadData() {
        MyRetrofit.getZhiHuRetrofit()
                .create(GetZhihuNewsService.class)
                .getZhihuNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuNews>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ZhiHuNews value) {
                        String html = HtmlUtil.getHtml(value);
                        if (html != null) {
                            showHtml(html);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ShowZhiHuNews", "出错了");
                        showNoWIFI();
                    }

                    @Override
                    public void onComplete() {
                        Log.e("ShowZhiHuNewsFragment", "Fragment表示填充完毕");
                    }
                });
    }

    private void showHtml(final String html) {
        mLoadingView.setVisibility(View.GONE);
        mWebView.loadData(html, "text/html; charset=UTF-8", null);
    }

    private void showNoWIFI() {
        mLoadingView.setVisibility(View.GONE);
        mWebView.setVisibility(View.GONE);
        noWIFI.setVisibility(View.VISIBLE);
    }

    public interface CallBackx{
        void setTitle(String title);
    }
}
