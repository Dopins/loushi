package com.android.loushi.loushi.util;

import android.util.Log;

import com.android.loushi.loushi.callback.UserLoginCallBack;
import com.android.loushi.loushi.json.UserLoginJson;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePalApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/4/16.
 */
public class MyApplication extends LitePalApplication {

    private static final String TAG="MyApplication";

    public void onCreate() {
        super.onCreate();

//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(
//                                Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(
//                                Stetho.defaultInspectorModulesProvider(this))
//                        .build());


        OkHttpUtils.getInstance().setConnectTimeout(10000, TimeUnit.MILLISECONDS);
        OkHttpUtils.post().url("http://119.29.187.58:10000/LouShi/user/userLogin.action")
                .addParams("mobile_phone", "13750065622")
                .addParams("password", "mtf071330")
                .addParams("isThird", "false")
                .build().execute(new UserLoginCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG,"errorerrorerrorerrorerrorerrorerror");
            }

            @Override
            public void onResponse(UserLoginJson userLoginJson) {
                Log.e(TAG, "successsuccesssuccesssuccess"+Integer.toString(userLoginJson.getBody()));
            }
        });


    }
}
