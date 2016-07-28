package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.android.loushi.loushi.R;
import com.google.gson.Gson;


import okhttp3.Call;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneDetailDesignFragment extends BaseFragment {
    private WebView webView;
    private LinearLayout collect_bar;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        initWebview();
        initCollectBar();
//        Log.e(TAG,"onActivityCreated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        view=inflater.inflate(R.layout.fragment_category, container, false);
        View view=inflater.inflate(R.layout.fragment_scene_detail_design,null);

        return view;
    }
    private void initWebview(){
        webView=(WebView)getView().findViewById(R.id.webview);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
//                activity.setProgress(progress * 1000);
                getActivity().setProgress(progress * 100);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            // 获取cookie
            @Override
            public void onPageFinished(WebView view, String url) {
                CookieManager cookieManager = CookieManager.getInstance();
                String CookieStr = cookieManager.getCookie(url);
                Log.e("HEHE", "Cookies = " + CookieStr);
                super.onPageFinished(view, url);
            }

            public boolean shouldOverrideUrlLoading(WebView view, final String url) {
                //获取web跳转的url 根据 url的后缀来确定商品id


                    return false;

            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置渲染优先级，加速渲染
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 先加载页面在加载图片
        // webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setBlockNetworkImage(false);
//        // 设置cookie，但是cookie不知道从哪来的，暂时注释
//        CookieSyncManager.createInstance(WebView.this);
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.setAcceptCookie(true);
//        cookieManager.setCookie(url, cookies);  //cookies是要设置的cookie字符串
//        CookieSyncManager.getInstance().sync();
        webView.loadUrl("http://172.16.1.218:8020/111/topicDetail.html");
    }
    private void initCollectBar(){
        collect_bar=(LinearLayout)getView().findViewById(R.id.collect_bar);

    }
}
