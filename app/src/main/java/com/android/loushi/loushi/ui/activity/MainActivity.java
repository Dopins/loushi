package com.android.loushi.loushi.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.TradeConfigs;
import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.fragment.CategoryFragment;
import com.android.loushi.loushi.ui.fragment.MyFragment;
import com.android.loushi.loushi.ui.fragment.SceneFragment;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.UrlConstant;
import com.lzy.okhttputils.OkHttpUtils;
import com.taobao.tae.sdk.callback.InitResultCallback;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    public FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {SceneFragment.class, CategoryFragment.class, MyFragment.class};
    private String mTextviewArray[] = {"场景", "指南", "我的"};
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {
            R.drawable.tab_scene_button,
            R.drawable.tab_category_button,
            R.drawable.tab_my_button};

    private final static int FLAG_LOGIN = 1;
    private final static int FLAG_GET_USRINFO = 2;
    private final static int DELAYTIME_LOGIN = 29 * 60 * 1000;
    private final static int DELAYTIME_USERINFO = 10 * 1000;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FLAG_LOGIN:
                    autoLogin();
                    handler.sendEmptyMessageDelayed(FLAG_LOGIN, DELAYTIME_LOGIN);
                    break;
                case FLAG_GET_USRINFO:
                    updateUserInfo();
                    handler.sendEmptyMessageDelayed(FLAG_GET_USRINFO, DELAYTIME_USERINFO);
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        InitTaobao();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        startHandler();
    }

    private void startHandler() {
        handler.sendEmptyMessageDelayed(FLAG_LOGIN, DELAYTIME_LOGIN);
        handler.sendEmptyMessageDelayed(FLAG_GET_USRINFO, DELAYTIME_USERINFO);
    }


    private void updateUserInfo() {
        if (!hasLogin()||MyMessageActivity.hasNewMessage())
            return;
        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userinfo.action")
                .params(KeyConstant.USER_ID, MainActivity.user_id)
                .execute(new JsonCallback<UserInfoJson>(UserInfoJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserInfoJson userInfoJson, Request request, @Nullable Response response) {
                        if (userInfoJson.isState()) {
                            if(userInfoJson.getBody().getMessageCount()==0)
                                return ;
                            Log.i(TAG, "getMessageCount==" + userInfoJson.getBody().getMessageCount());
                            CurrentAccount.setMessageCount(userInfoJson.getBody().getMessageCount());
                            EventBus.getDefault().post(new MainEvent(MainEvent.UPDATE_USERINFO));
                        }
                    }
                });
    }

    private boolean hasLogin() {
        String phone = CurrentAccount.getMobile_phone();
        String password = CurrentAccount.getPassword();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password))
            return false;
        else
            return true;
    }

    private void autoLogin() {
        //TODO 第三方登录？？
        if (!hasLogin())
            return;
        String phone = CurrentAccount.getMobile_phone();
        String password = CurrentAccount.getPassword();
        OkHttpUtils.post(UrlConstant.USERLOGINURL)
                .params(KeyConstant.MOBILE_PHONE, phone)
                .params(KeyConstant.PASSWORD, password)
                .params(KeyConstant.ISTHIRD, "false")
                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                        if (userLoginJson.getState()) {
                            Log.e(TAG, "autoLogin 登录成功！");
                        } else {
                            Log.e(TAG, "autoLogin 登录失败！");
                        }
                    }
                });

    }

    private void initView() {
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.content);
        int count = fragmentArray.length;


        for (int i = 0; i < count; i++) {

            //为每一个Tab按钮设置图标、文字和内容

            final TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));

            mTabHost.addTab(tabSpec, fragmentArray[i], null);

        }


    }


    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }


    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);


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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MainEvent event) {
        if (event.getMsg() == MainEvent.NEED_LOGIN) {
//            Intent intent =new Intent(MainActivity.this,FeedActivity.class);
//            startActivity(intent);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        handler.removeCallbacksAndMessages(null);
        handler = null;
        MobclickAgent.onProfileSignOff();
    }

}
