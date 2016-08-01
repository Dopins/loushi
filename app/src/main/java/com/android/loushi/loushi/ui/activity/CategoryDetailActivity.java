package com.android.loushi.loushi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.StrategyJson;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.android.loushi.loushi.util.KeyConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

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
    private String url="";
    private Toolbar toolbar;
    private ImageView back;
    private TextView tv_title;
    //private StrategyJson.BodyBean strategyBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_category_detail;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        jsonstring=getIntent().getStringExtra(JSONSTRING);
        Log.e("stra1",jsonstring);
        topicBean=new Gson().fromJson(jsonstring, TopicJson.BodyBean.class);
        Log.e("stra",topicBean.getName());
        type=getIntent().getStringExtra(TYPE);
        if(type.equals("2")) {
            url=topicBean.getImgUrl();
            if (url.indexOf("|||") >= 0)
                url = url.substring(url.indexOf("|||")+3);
            Log.e("url",url);
            if(TextUtils.isEmpty(url))
                url="";

        }
        if(type.equals("1")){
            url="http://www.loushi666.com:8080/loushi/topic.html?&user_id="+BaseActivity.user_id
                    +"&topic_id="+
            topicBean.getId();
        }
        Log.e("cate",url);

        initView();
    }

    private void initView() {
        initWebView();
        initToolBar();
        bindCollectBarView();

    }
    private void initToolBar(){
        toolbar = (Toolbar)findViewById(R.id.program_toolbar);
        back = (ImageView)toolbar.findViewById(R.id.back);
        tv_title=(TextView)toolbar.findViewById(R.id.tv_title);
        tv_title.setText(topicBean.getName());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

        webView.loadUrl(url);
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
        tv_collect_count.setText(topicBean.getCollectionNum() + "");
        tv_comment_count.setText(topicBean.getCommentNum() + "");
        tv_share_count.setText(topicBean.getForwordNum() + "");


    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.collect_bar_linear_comment:
                Intent intent = new Intent(CategoryDetailActivity.this,CommentActivity.class);
                intent.putExtra(KeyConstant.TYPE,type);
                intent.putExtra(KeyConstant.PID, topicBean.getId()+"");
                startActivity(intent);
                break;
            case R.id.collect_bar_linear_like:
                OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollect")
                        .params("user_id", user_id).params("type", type).params("pid", topicBean.getId()+"")
                        .tag(this).execute(new JsonCallback<ResponseJson>(ResponseJson.class) {

                    @Override
                    public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
                        if (responseJson.getState()) {
                            if (btn_collect.isSelected()) {
                                int num = Integer.parseInt(tv_collect_count.getText().toString()) - 1;

                                tv_collect_count.setText(num + "");
                                btn_collect.setSelected(false);
                            } else {
                                int num = Integer.parseInt(tv_collect_count.getText().toString()) + 1;
                                tv_collect_count.setText(num + "");
                                btn_collect.setSelected(true);
                            }
                        }
                    }
                });
                break;


        }
    }


}
