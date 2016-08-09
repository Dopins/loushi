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
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
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


        OkHttpUtils.init(this);
        OkHttpUtils.getInstance()
                .debug("OkHttpUtils")
                .setCookieStore(new PersistentCookieStore())
        ;



        CurrentAccount.init(this);
        String phone = CurrentAccount.getMobile_phone();
        String password = CurrentAccount.getPassword();
        Log.e("my",phone+password);
        OkHttpUtils.post(UrlConstant.USERLOGINURL)
                .params(KeyConstant.MOBILE_PHONE, phone)
                .params(KeyConstant.PASSWORD, password)
                .params(KeyConstant.ISTHIRD, "false")
                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                        if (userLoginJson.getState()) {
                            BaseActivity.user_id = userLoginJson.getBody() + "";
                            Log.e(TAG, "autoLogin 登录成功！");
                        } else {
                            Log.e(TAG, "autoLogin 登录失败！");
                        }
                    }
                });

        //kookie设置为持久化
        //debug是打印调试信息 可不要
        //域名放在baseactivity的静态变量里



//        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
//
//                // 请求方式和请求url
////                .tag(this).params("mobile_phone", "123456").params("password", "mtf071330")
//                .tag(this).params("mobile_phone", "13750065622").params("password", "mtf071330")
//                .params("isThird", "false")
//                        // 请求的 tag, 主要用于取消对应的请求
//                        // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                        // 缓存模式，详细请看缓存介绍
//
//                        //有的操作要设置缓存，有的不要 这个号供测试用
//
//                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
//                    @Override
//                    public void onResponse(boolean b, UserLoginJson userLoginJson, Request request, Response response) {
//
//                        //这里现在是48
//                        Log.e(TAG, Integer.toString(userLoginJson.getBody()));
//                        //这里现在是48了
//                        Log.e("test", response.toString());
//                        BaseActivity.user_id = Integer.toString(userLoginJson.getBody());
//
//
//                    }
//
//
//                });


        InitTaobao();

        initBugly();
        initUMeng();

    }

    private void initUMeng(){
        MobclickAgent.UMAnalyticsConfig config=new MobclickAgent.UMAnalyticsConfig(
                getApplicationContext(),
                "57a5eda367e58ef278000163",
                "testchannel",
                MobclickAgent.EScenarioType. E_UM_NORMAL,
                true);
        MobclickAgent.startWithConfigure(config);
        MobclickAgent.setDebugMode(true);
    }

    private void initBugly(){
        CrashReport.initCrashReport(getApplicationContext(), "900045792", true);
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
