package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.TradeConfigs;
import com.android.loushi.loushi.R;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.taobao.tae.sdk.callback.InitResultCallback;

/**
 * Created by Administrator on 2016/8/6.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        InitTaobao();

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
