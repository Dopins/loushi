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
import com.umeng.analytics.MobclickAgent;

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
    public static final String url_login="http://www.loushi666.com/LouShi/user/userLogin.action";
    public static String user_id = "48";
    public static String TYPE = "TYPE";
    public static String GOOD_STRING = "GOOD_STRING";
    public static final String url_goods="http://www.loushi666.com/LouShi/base/goods";

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }




    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}
