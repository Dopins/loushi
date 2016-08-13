package com.android.loushi.loushi.util;

import android.content.Context;
import android.util.Log;


import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.lzy.okhttputils.OkHttpUtils;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/28.
 */
public class ShareSomeThing {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private Context context;
    private String imgurl;
    private String url;
    private String title;
    private String text;
    private String user_id;
    private String type;
    private String pid;
    public ShareSomeThing(Context context, String imgurl, String url, String text, String title, String user_id, String type, String pid) {
        this.context = context;
        this.imgurl = imgurl;
        this.url = url;
        this.text = text;
        this.title = title;
        this.type=type;
        this.user_id=user_id;
        this.pid=pid;
    }
    public void DoShare(){
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        //oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        //oks.setTitleUrl("http://www.baidu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setTitle(title);
        oks.setText(text);
        oks.setTitleUrl(url);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        // oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        oks.setImageUrl(imgurl);

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("url");

// 启动分享GUI
        oks.show(context);
//        OkHttpUtils.post().url("http://119.29.187.58:10000/LouShi/base/forword.action").
//                addParams("type",type).addParams("user_id",user_id).addParams("pid",pid)
//                .build().execute(new NormalCallBack() {
//            @Override
//            public void onError(Call call, Exception e) {
//                Log.e("share", Log.getStackTraceString(e));
//            }
//
//            @Override
//            public void onResponse(ResponseJson responseJson) {
//                Log.e("share",Boolean.toString(responseJson.getState()));
//            }
//        });
//        OkHttpUtils.post(UrlConstant.SHAREURL)
//                .params("user_id",KeyConstant.USER_ID)
//                .params("pid",pid)
//                .params("type",type).execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
//            @Override
//            public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
//                if(responseJson.getState()){
//                   Log.e("share","转发成功");
//                }
//            }
//        });


    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
