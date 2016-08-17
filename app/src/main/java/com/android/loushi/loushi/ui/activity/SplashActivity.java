package com.android.loushi.loushi.ui.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.alibaba.nb.android.trade.AliTradeSDK;
import com.alibaba.nb.android.trade.callback.AliTradeInitCallback;
import com.alibaba.nb.android.trade.model.AliTradeTaokeParams;
import com.alibaba.sdk.android.AlibabaSDK;


import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.jsonbean.UpdateVersionJson;
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/6.
 */
public class SplashActivity extends BaseActivity {
    public static final String APP_KEY = "23393904";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Timer timer = new Timer();
        timer.schedule(task,3000);
        InitTaobao();
        CheckCanLogin();


    }
    private void CheckCanLogin(){
        if(CurrentAccount.getLoginOrNot()){
            Log.e("splash","可登陆");
            if(CurrentAccount.isThird()){
                Log.e("splash","disanfang");
                LoginThird();
            }
            else{
                Log.e("splash","feidisanfang");
               LoginNotThird();
            }

        }
       /* else
            GoMainACtivity();*/
    }
    private void LoginThird(){
        final String account=CurrentAccount.getAccount();
        String password=CurrentAccount.getPassword();
        final String type =CurrentAccount.getThirdType();

        OkHttpUtils.post(UrlConstant.USERLOGINURL)
                .params(KeyConstant.ACCOUNT, account)
                .params(KeyConstant.TYPE, type)
                .params(KeyConstant.TOKEN, password)
                .params(KeyConstant.ISTHIRD, "true")
                .connTimeOut(3000)
                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                        Log.e("splash", new Gson().toJson(userLoginJson));
                        if (userLoginJson.getState()) {
                            CurrentAccount.setLoginOrNot(true);
                            //CurrentAccount.setLoginOrNot(true);//登录成功，设置登录状态
                            String code = userLoginJson.getCode();
                            if (code != null && code.equals("3")) {

                            } else {
                                CurrentAccount.setUserId(userLoginJson.getBody() + "");
                                getUserInfo(userLoginJson.getBody());

                            }

                        } else {
                            CurrentAccount.setLoginOrNot(false);
                            Log.e("splashthirdlogin", "登录失败！");
                        }
                        //GoMainACtivity();

                    }

                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Log.e("splash", "loginerror");
                        //GoMainACtivity();
                    }
                });
    }
    private void LoginNotThird(){
        final String account=CurrentAccount.getAccount();
        String password=CurrentAccount.getPassword();
        Log.e("splashaccount",account+password);
        OkHttpUtils.post(UrlConstant.USERLOGINURL)
                .params(KeyConstant.MOBILE_PHONE, account)
                .params(KeyConstant.PASSWORD, password).connTimeOut(3000)
                .params(KeyConstant.ISTHIRD, "false")
                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                        Log.e("splash","loginresponse");
                        if (userLoginJson.getState()) {
                            Log.e("splashnotthird", "登录成功！");

                            CurrentAccount.setLoginOrNot(true);
                            CurrentAccount.setUserId(userLoginJson.getBody() + "");
                            getUserInfo(userLoginJson.getBody());
                        } else {
                            CurrentAccount.setLoginOrNot(false);
                            Log.e("splashnotthirdlogin", "登录失败！");
                        }
                        //SystemClock.sleep(2000);
                        //GoMainACtivity();
                    }
                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Log.e("splash", "loginerror");
                        //GoMainACtivity();
                    }
                });
    }
    private void getUserInfo(int id) {
        //Log.e(TAG, "getUserInfo");
        String user_id = id + "";
        Log.e("BIG ", user_id);
        OkHttpUtils.post(UrlConstant.USERINFOURL)
                .params(KeyConstant.USER_ID, user_id)
                .execute(new JsonCallback<UserInfoJson>(UserInfoJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserInfoJson userInfoJson, Request request, @Nullable Response response) {
                        if (userInfoJson.isState()) {
                            Log.e("splashgetinfo","getinfo");
                            CurrentAccount.storeUserInfo(userInfoJson);
                            EventBus.getDefault().post(new MainEvent(MainEvent.LOGIN_UPDATEINFO));
                            //transferMyFragmentToPersonalFragment();
                        }
                        //GoMainACtivity();
                    }
                });
    }
    private void InitTaobao() {

        try {
            //AlibabaSDK.turnOnDebug();
            //电商SDK初始化
            AliTradeSDK.asyncInit(this, APP_KEY, new AliTradeInitCallback() {
                @Override
                public void onSuccess() {
//                    Toast.makeText(SplashActivity.this, "初始化成功", Toast.LENGTH_SHORT).show();
                    Log.e("myapplication", "chenggong");
                    AliTradeTaokeParams taokeParams = new AliTradeTaokeParams("mm_114880276_0_0", "", "");
                    AliTradeSDK.setTaokeParams(taokeParams);
                    AliTradeSDK.setSyncForTaoke(true);
                }

                @Override
                public void onFailure(int code, String msg) {
//                    Toast.makeText(SplashActivity.this, "初始化失败" + code + msg, Toast.LENGTH_SHORT).show();
                    Log.e("myapplication", "shibai" + code + msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("myapplication",Log.getStackTraceString(e));
        }
    }

    private void GoMainACtivity(){
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    TimerTask task = new TimerTask() { //timertask实现runnable接口,TimerTask类就代表一个在指定时间内执行的task
        @Override
        public void run() {
           GoMainACtivity();
        }
    };

}
