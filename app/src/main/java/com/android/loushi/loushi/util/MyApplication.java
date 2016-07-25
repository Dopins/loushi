package com.android.loushi.loushi.util;

import android.util.Log;


import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;

import org.litepal.LitePalApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/16.
 */
public class MyApplication extends LitePalApplication {

    private static final String TAG="MyApplication";

    public void onCreate() {
        super.onCreate();

        OkHttpUtils.init(this);
        OkHttpUtils.getInstance()//
                .debug("OkHttpUtils").setCookieStore(new PersistentCookieStore());
        OkHttpUtils.post("http://119.29.187.58:10000/LouShi/user/userLogin.action")
                // 请求方式和请求url
                .tag(this).params("mobile_phone", "13750065622").params("password", "mtf071330")
                .params("isThird", "false")
                        // 请求的 tag, 主要用于取消对应的请求
                        // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                        // 缓存模式，详细请看缓存介绍

                        //有的操作要设置缓存，有的不要 这个号供测试用

                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                    @Override
                    public void onResponse(boolean b, UserLoginJson userLoginJson, Request request, Response response) {
                        Log.e(TAG,Integer.toString(userLoginJson.getBody()));
                        //这里现在是48了

                    }


                });
    }


}
