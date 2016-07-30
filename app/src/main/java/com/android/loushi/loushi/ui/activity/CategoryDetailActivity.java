package com.android.loushi.loushi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.jsonbean.StrategyJson;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.google.gson.Gson;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/7/29.
 */
public class CategoryDetailActivity extends BaseActivity {
    private WebView webView;
    private LinearLayout collect_bar;
    private LinearLayout collect;
    private LinearLayout comment;
    private LinearLayout share;
    private ImageButton btn_collect;
    private ImageButton btn_comment;
    private TextView tv_collect_count;
    private TextView tv_comment_count;
    private TextView tv_share_count;
    public static String TYPE="TYPE";
    public static String JSONSTRING="JSONSTRING";
    private String jsonstring="";
    private String type="0";
    private TopicJson.BodyBean topicBean;
    //private StrategyJson.BodyBean strategyBean;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scene_detail_design;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_scene_detail_design);
        jsonstring=getIntent().getStringExtra(JSONSTRING);
        Log.e("stra1",jsonstring);
        topicBean=new Gson().fromJson(jsonstring, TopicJson.BodyBean.class);
        Log.e("stra",topicBean.getName());
        initView();
    }

    private void initView() {
        initWebView();

        bindCollectBarView();

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

    private void bindCollectBarView(){

        collect_bar=(LinearLayout)findViewById(R.id.collect_bar);
        //collect_bar.setVisibility(View.GONE);
        collect = (LinearLayout)collect_bar.findViewById(R.id.collect_bar_linear_like);
        comment = (LinearLayout)collect_bar.findViewById(R.id.collect_bar_linear_comment);
        share = (LinearLayout)collect_bar.findViewById(R.id.collect_bar_linear_share);
        btn_collect=(ImageButton)collect.findViewById(R.id.collect_bar_btn_like);
        tv_collect_count=(TextView)collect.findViewById(R.id.collect_bar_tv_like);
        tv_comment_count=(TextView)collect_bar.findViewById(R.id.collect_bar_tv_comment);
        tv_share_count=(TextView)collect_bar.findViewById(R.id.collect_bar_tv_share);
        btn_collect.setSelected(topicBean.getCollected());
        tv_collect_count.setText(topicBean.getCollectionNum()+"");
        tv_comment_count.setText(topicBean.getCommentNum()+"");
        tv_share_count.setText(topicBean.getForwordNum()+"");


    }
}
