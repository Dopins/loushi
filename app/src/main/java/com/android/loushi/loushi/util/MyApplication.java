package com.android.loushi.loushi.util;

import android.util.Log;


import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.TradeConfigs;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.MainActivity;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.CookieStore;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.squareup.picasso.Picasso;
import com.taobao.tae.sdk.callback.InitResultCallback;

import org.litepal.LitePalApplication;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/16.
 */
public class MyApplication extends LitePalApplication {

    private static final String TAG="MyApplication";

    public void onCreate() {
        super.onCreate();

        CurrentAccount.init(this);


        //kookie设置为持久化
        //debug是打印调试信息 可不要
        //域名放在baseactivity的静态变量里
        OkHttpUtils.init(this);
        OkHttpUtils.getInstance()
               .debug("OkHttpUtils")
                .setCookieStore(new PersistentCookieStore())
                ;

        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")

                // 请求方式和请求url
//                .tag(this).params("mobile_phone", "123456").params("password", "mtf071330")
                .tag(this).params("mobile_phone", "13750065622").params("password", "mtf071330")
                .params("isThird", "false")
                        // 请求的 tag, 主要用于取消对应的请求
                        // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                        // 缓存模式，详细请看缓存介绍

                        //有的操作要设置缓存，有的不要 这个号供测试用

                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                    @Override
                    public void onResponse(boolean b, UserLoginJson userLoginJson, Request request, Response response) {

                        //这里现在是48
                        Log.e(TAG, Integer.toString(userLoginJson.getBody()));
                        //这里现在是48了
                        Log.e("test", response.toString());
                        BaseActivity.user_id=Integer.toString(userLoginJson.getBody());



                    }


                });
        InitTaobao();

//这个是设置了缓存的示例
        //        OkHttpUtils.post("http://119.29.187.58:10000/LouShi/base/scene.action")
//                // 请求方式和请求url
//                .tag(this).params("scene_group_id", "1").params("user_id", "0").params("recommended","false")
//                .params("skip", "0").params("take", "3")
//                        // 请求的 tag, 主要用于取消对应的请求
//                .cacheKey("scene")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)    // 缓存模式，详细请看缓存介绍
//                .execute(new JsonCallback<SceneJson>(SceneJson.class) {
//                    @Override
//                    public void onResponse(boolean b, SceneJson sceneJson, Request request, Response response) {
//                        tv.setText(sceneJson.getBody().get(0).getName());
//                    }
//
//
//                });
    }
    private void InitTaobao() {
        TradeConfigs.defaultTaokePid = "mm_114880276_0_0";
        AlibabaSDK.asyncInit(this, new InitResultCallback() {

            @Override
            public void onSuccess() {
//                Toast.makeText(getApplicationContext(), "初始化成功", Toast.LENGTH_SHORT)
//                        .show();
                Log.e("splash", "success");
                //showItemDetailPage(ll);
            }

            @Override
            public void onFailure(int code, String message) {
//                Toast.makeText(getApplicationContext(), "初始化异常", Toast.LENGTH_SHORT)
//                        .show();
                Log.e("splash", "nosuccess" + message);
            }

        });
    }


}
