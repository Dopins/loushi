package com.android.loushi.loushi.util;

import android.util.Log;
import android.widget.Toast;


import com.alibaba.nb.android.trade.AliTradeSDK;
import com.alibaba.nb.android.trade.callback.AliTradeInitCallback;
import com.alibaba.nb.android.trade.model.AliTradeTaokeParams;
import com.alibaba.sdk.android.AlibabaSDK;

import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.MainActivity;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.CookieStore;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.squareup.picasso.Picasso;

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
    public static final String APP_KEY = "23393904";
    private static final String TAG="MyApplication";

    public void onCreate() {
        super.onCreate();

        OkHttpUtils.init(this);
        OkHttpUtils.getInstance()
                .debug("OkHttpUtils")
                .setCookieStore(new PersistentCookieStore())
        ;
        CurrentAccount.init(this);
//        OkHttpUtils.post(UrlConstant.USERLOGINURL)
//                .params(KeyConstant.MOBILE_PHONE, phone)
//                .params(KeyConstant.PASSWORD, password)
//                .params(KeyConstant.ISTHIRD, "false")
//                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
//                    @Override
//                    public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
//                        if (userLoginJson.getState()) {
//                            BaseActivity.user_id = userLoginJson.getBody() + "";
//                            Log.e(TAG, "autoLogin 登录成功！");
//                        } else {
//                            Log.e(TAG, "autoLogin 登录失败！");
//                        }
//                    }
//                });

        //InitTaobao();

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
/*        TradeConfigs.defaultTaokePid = "mm_114880276_0_0";
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

        });*/
        try {
            AlibabaSDK.turnOnDebug();
            //电商SDK初始化
            AliTradeSDK.asyncInit(this, APP_KEY, new AliTradeInitCallback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(MyApplication.this, "初始化成功", Toast.LENGTH_SHORT).show();
                    Log.e("myapplication", "chenggong");
                    AliTradeTaokeParams taokeParams = new AliTradeTaokeParams("mm_114880276_0_0", "", "");
                    AliTradeSDK.setTaokeParams(taokeParams);
                    AliTradeSDK.setSyncForTaoke(true);
                }

                @Override
                public void onFailure(int code, String msg) {
                    Toast.makeText(MyApplication.this, "初始化失败" + code + msg, Toast.LENGTH_SHORT).show();
                    Log.e("myapplication", "shibai" + code + msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("myapplication",Log.getStackTraceString(e));
        }

    }

}
