package com.android.loushi.loushi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.DialogCallback;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.jsonbean.GoodsJson;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.ShareSomeThing;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/29.
 */
public class CategoryDetailActivity extends BaseActivity implements View.OnClickListener {
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
    private String cate_url="";
    private Toolbar toolbar;
    private ImageView back;
    private TextView tv_title;
    private WebView WebView;
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
            cate_url=topicBean.getImgUrl();
            if (cate_url.indexOf("|||") >= 0)
                cate_url = cate_url.substring(cate_url.indexOf("|||")+3);
            Log.e("url",cate_url);
            if(TextUtils.isEmpty(cate_url))
                cate_url="";

        }
        if(type.equals("1")){
            cate_url="http://www.loushi666.com:8080/loushi/topic.html?user_id="+BaseActivity.user_id
                    +"&topic_id="+
            topicBean.getId();
        }
        Log.e("cate", cate_url);

        initView();
    }

    private void initView() {
        initWebView();
        initToolBar();
        bindCollectBarView();

    }

    private void initWebView() {
        webView = (WebView)findViewById(R.id.webview);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                super.onProgressChanged(view, progress);
            }
        });
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            public boolean shouldOverrideUrlLoading(WebView view, final String url) {
                //获取web跳转的url 根据 url的后缀来确定商品id
                Log.e("categourl", url);
                if (!url.equals(cate_url)) {
                    String good_id = "";
                    int index = url.indexOf("good_id=");
                    for (int i = index + 8; i < url.length(); i++) {
                        if (url.charAt(i) >= '0' && url.charAt(i) <= '9')
                            good_id += url.charAt(i);
                    }

                    Log.e("goodid", good_id);
                    getGoodDetail(good_id);
                    return true;
                } else
                    return false;

            }
        });

        webView.getSettings().setJavaScriptEnabled(true);



        webView.getSettings().setBuiltInZoomControls(true);

        webView.getSettings().setSupportZoom(true);

        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBlockNetworkImage(false);

        //webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        int screenDensity = getResources().getDisplayMetrics().densityDpi ;
        Log.e("density", screenDensity +"");
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM ;
        switch (screenDensity){
            case DisplayMetrics.DENSITY_LOW :
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break ;
            default:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        Log.e("density", zoomDensity + "");

        webView.getSettings().setDefaultZoom(zoomDensity);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //webView.setInitialScale(50);
        webView.loadUrl(cate_url);



    }

    private void getGoodDetail(String good_id) {
        OkHttpUtils.post(BaseActivity.url_goods).params("user_id",BaseActivity.user_id)
                .params("good_id",good_id).execute(new DialogCallback<GoodsJson>(CategoryDetailActivity.this,GoodsJson.class) {
            @Override
            public void onResponse(boolean b, GoodsJson goodsJson, Request request, Response response) {
                if(goodsJson.getState()) {
                    //ToastUtils.show(CategoryDetailActivity.this, "获取商品", ToastUtils.LENGTH_SHORT);
                    Intent intent = new Intent(CategoryDetailActivity.this, GoodDetailActivity.class);
                    intent.putExtra("GOOD_ID", goodsJson.getBody().getId() + "");
                    intent.putExtra(BaseActivity.GOOD_STRING, new Gson().toJson(goodsJson.getBody()));
                    startActivity(intent);
                }
            }
        });
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


    private void bindCollectBarView(){

        collect_bar=(LinearLayout) findViewById(R.id.collect_bar);
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
        collect.setOnClickListener(this);
        comment.setOnClickListener(this);
        share.setOnClickListener(this);

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
                            EventBus.getDefault().post(new MainEvent(MainEvent.UPDATE_COLLECT));
                        }
                    }
                });
                break;
            case R.id.collect_bar_linear_share:
                String imgurl="";
                String title="";
                String text="";
                if (!TextUtils.isEmpty(topicBean.getImgUrl()))
                imgurl = topicBean.getImgUrl();
                if(type.equals("2")){
                    if(!TextUtils.isEmpty(imgurl.substring(0,imgurl.indexOf("|||"))))
                    imgurl=imgurl.substring(0,imgurl.indexOf("|||"));
                }
                if(!TextUtils.isEmpty(topicBean.getName()))
                title = topicBean.getName();

                if(!TextUtils.isEmpty(topicBean.getDigest()))
                text = topicBean.getDigest();

                //Toast.makeText(this, "click clean ", Toast.LENGTH_SHORT).show();
                if(!TextUtils.isEmpty(imgurl)) {
                    ShareSomeThing shareSomeThing = new ShareSomeThing(CategoryDetailActivity.this, imgurl, cate_url, text, title, user_id, type, topicBean.getId() + "");
                    shareSomeThing.DoShare();
                }
                break;


        }

    }


}
