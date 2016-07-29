package com.android.loushi.loushi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
 * Created by Administrator on 2016/7/29.
 */
public class CategoryDetailActivity extends BaseActivity {
    private WebView webView;
    private LinearLayout collect_bar;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scene_detail_design;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_scene_detail_design);

        initView();
    }

    private void initView() {
        initWebView();
        collect_bar = (LinearLayout)findViewById(R.id.collect_bar);

    }

    private void initWebView() {
        webView =(WebView) findViewById(R.id.webview);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                setProgress(progress * 100);
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            public boolean shouldOverrideUrlLoading(WebView view, final String url) {
                //获取web跳转的url 根据 url的后缀来确定商品id


                    return false;

            }
        });
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        webView.getSettings().setBlockNetworkImage(false);

        webView.loadUrl("http://172.16.1.218:8020/111/topicDetail.html");
    }
    private void initCollectBar(){
        
    }
}
