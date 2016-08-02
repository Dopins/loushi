package com.android.loushi.loushi.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.loushi.loushi.R;

/**
 * Created by Administrator on 2016/8/1.
 */
public class MyWebView extends LinearLayout {

    private Context context;
    private WebView webView;
    private ProgressBar progressBar;
    public MyWebView(Context context) {
        super(context);


        //LayoutInflater
    }


    public MyWebView(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.ll_mywebview, this, true);
        webView= (WebView)findViewById(R.id.webview);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        //setWebView("http://www.baidu.com");
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);




    }


    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public WebView getWebView(){
        return this.webView;
    }
    public void setWebView(String url){
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if(progress==100)
                    progressBar.setVisibility(View.INVISIBLE);
                else {
                    if (View.INVISIBLE == progressBar.getVisibility()) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(progress);
                }
                super.onProgressChanged(view, progress);
            }
        });


        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        webView.getSettings().setBlockNetworkImage(false);

        webView.loadUrl(url);
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
    }
}
