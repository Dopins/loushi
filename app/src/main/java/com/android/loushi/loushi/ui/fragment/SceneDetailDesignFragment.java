package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.CommentActivity;
import com.android.loushi.loushi.util.KeyConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;


import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneDetailDesignFragment extends BaseFragment {
    private WebView webView;
    private LinearLayout collect_bar;
    private String scene_id="1";
    private LinearLayout collect;
    private LinearLayout comment;
    private LinearLayout share;
    private ImageButton btn_collect;
    private ImageButton btn_comment;
    private TextView tv_collect_count;
    private TextView tv_comment_count;
    private TextView tv_share_count;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        if(!TextUtils.isEmpty(getArguments().getString("SCENE_ID")))
        scene_id = getArguments().getString("SCENE_ID");
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
        webView.loadUrl("http://www.loushi666.com:8080/loushi/scene.html?user_id="+ BaseActivity.user_id+"&scene_id="+scene_id);
    }
    private void initCollectBar(){
        collect_bar=(LinearLayout)getView().findViewById(R.id.collect_bar);
        collect = (LinearLayout)collect_bar.findViewById(R.id.collect_bar_linear_like);
        comment = (LinearLayout)collect_bar.findViewById(R.id.collect_bar_linear_comment);
        share = (LinearLayout)collect_bar.findViewById(R.id.collect_bar_linear_share);
        btn_collect=(ImageButton)collect.findViewById(R.id.collect_bar_btn_like);
        tv_collect_count=(TextView)collect.findViewById(R.id.collect_bar_tv_like);
        tv_comment_count=(TextView)collect_bar.findViewById(R.id.collect_bar_tv_comment);
        tv_share_count=(TextView)collect_bar.findViewById(R.id.collect_bar_tv_share);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollect")
                        .params("user_id", BaseActivity.user_id).params("type", "0").params("pid", scene_id + "")
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
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CommentActivity.class);
                intent.putExtra(KeyConstant.TYPE,"0");
                intent.putExtra(KeyConstant.PID, scene_id+"");
                getActivity().startActivity(intent);
            }
        });
    }
}
