package com.android.loushi.loushi.ui.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.android.loushi.loushi.R;

/**
 * Created by Administrator on 2016/7/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    public static final String url = "http://www.loushi666.com/LouShi/";
    public static final String url_update = "http://www.loushi666.com/LouShi/base/updateVersion";
    public static final String url_login="http://www.loushi666.com/LouShi/user/userLogin.action";
    public static final String url_good_share="http://www.loushi666.com:8080/loushi/good.html?user_id=0&isForward=1&good_id=";
    public static String user_id = "0";
    public static String TYPE = "TYPE";
    public static String GOOD_STRING = "GOOD_STRING";
    public static final String url_goods="http://www.loushi666.com/LouShi/base/goods";

    protected abstract int getLayoutId();
    protected Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
        setContentView(getLayoutId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

    }




    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

}
