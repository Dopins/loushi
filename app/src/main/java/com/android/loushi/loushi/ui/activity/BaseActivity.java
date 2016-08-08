package com.android.loushi.loushi.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.UrlConstant;
import com.lzy.okhttputils.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/17.
 */
public abstract class BaseActivity extends AppCompatActivity {


    private static final String TAG = "BaseActivity";

    public static final String url = "http://www.loushi666.com/LouShi/";
    public static final String url_update = "http://www.loushi666.com/LouShi/base/updateVersion";
    public static String user_id = "48";
    public static String TYPE = "TYPE";
    public static String GOOD_STRING = "GOOD_STRING";
    public static final String url_goods="http://www.loushi666.com/LouShi/base/goods";
    private final static int FLAG_LOGIN = 1;
    private final static int DELAYTIME = 29 * 60 * 1000;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FLAG_LOGIN:
                    autoLogin();
                    break;
            }
            super.handleMessage(msg);
        }
    };



    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startHandler();
        setContentView(getLayoutId());

    }

    private void startHandler() {
        handler.sendEmptyMessageDelayed(FLAG_LOGIN, DELAYTIME);
    }


    private void autoLogin() {
        //TODO 第三方登录？？
//        String phone = CurrentAccount.getMobile_phone();
//        String password = CurrentAccount.getPassword();
//        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password))
//            return;
//        OkHttpUtils.post(UrlConstant.USERLOGINURL)
//                .params(KeyConstant.MOBILE_PHONE, phone)
//                .params(KeyConstant.PASSWORD, password)
//                .params(KeyConstant.ISTHIRD, "false")
//                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
//                    @Override
//                    public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
//                        if (userLoginJson.getState()) {
//                            Log.e(TAG, "autoLogin 登录成功！");
//                        } else {
//                            Log.e(TAG, "autoLogin 登录失败！");
//                        }
//                    }
//                });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handler = null;
    }


}
