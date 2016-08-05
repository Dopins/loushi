package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
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
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.CommentActivity;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.MyWebView;
import com.android.loushi.loushi.util.ShareSomeThing;
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

    private LinearLayout collect;
    private LinearLayout comment;
    private LinearLayout share;
    private ImageButton btn_collect;
    private ImageButton btn_comment;
    private TextView tv_collect_count;
    private String url="";
    private TextView tv_comment_count;
    private TextView tv_share_count;
    private String sceneJsonString="";
    private SceneJson.BodyBean sceneJson;
    private String scene_id="1";
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        if(!TextUtils.isEmpty(getArguments().getString("SCENE_ID")))
        scene_id = getArguments().getString("SCENE_ID");
        sceneJsonString=getArguments().getString("SCENE_STRING");
        sceneJson=new Gson().fromJson(sceneJsonString,SceneJson.BodyBean.class);
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
        url="http://www.loushi666.com:8080/loushi/scene.html?user_id="+ BaseActivity.user_id+"&scene_id="+scene_id;
        Log.e("url",url);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                super.onProgressChanged(view, progress);
            }
        });


        webView.getSettings().setJavaScriptEnabled(true);
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
        }
        webView.getSettings().setDefaultZoom(zoomDensity);
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
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgurl = sceneJson.getImgUrl();

                String title = sceneJson.getName();
                String text = sceneJson.getDigest();

                //Toast.makeText(this, "click clean ", Toast.LENGTH_SHORT).show();
                ShareSomeThing shareSomeThing = new ShareSomeThing(getApplicationContext(), imgurl, url, text, title,BaseActivity.user_id,"1",sceneJson.getId()+"");
                shareSomeThing.DoShare();
            }
        });
        tv_comment_count.setText(sceneJson.getCommentNum()+"");
        tv_collect_count.setText(sceneJson.getCollectionNum()+"");
        tv_share_count.setText(sceneJson.getForwordNum()+"");
    }
}
