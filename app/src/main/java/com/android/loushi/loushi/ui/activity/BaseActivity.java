package com.android.loushi.loushi.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.lzy.okhttputils.OkHttpUtils;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String url="http://www.loushi666.com/LouShi/";
    public static final String url_update="http://www.loushi666.com/LouShi/base/updateVersion";
    public static String user_id="48";
    public static String TYPE="TYPE";
    public static String GOOD_STRING="GOOD_STRING";

    private final static int FLAG_LOGIN=1;
    private LoginRunnnable loginTask;
    private final static int DELAYTIME=29*60*1000;

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case FLAG_LOGIN:

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

    private void startHandler(){
        if(loginTask==null)
            loginTask=new LoginRunnnable();
        handler.postDelayed(loginTask,DELAYTIME);
    }

    private class LoginRunnnable implements Runnable{

        @Override
        public void run() {

            //TODO 等待封装
//            OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
//                    .params("mobile_phone", login_edit_phone.getText().toString())
//                    .params("password", login_edit_password.getText().toString())
//                    .params("isThird", "false")
//                    .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
//                        @Override
//                        public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
//                            Log.e(TAG, request.toString());
//                            Log.e(TAG, response.toString());
//                            if (userLoginJson.getState()) {
//                                Log.e(TAG, "登录成功！");
//
//                                sharedPreferences = getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
//                                editor = sharedPreferences.edit();
//                                editor.putBoolean("LoginOrNot", true);
//                                editor.putString("phone", login_edit_phone.getText().toString());
//                                editor.putString("password", login_edit_password.getText().toString());
//                                editor.commit();
//                                BaseActivity.user_id=userLoginJson.getBody()+"";
//                                getUserInfo(userLoginJson.getBody());
//
//                                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
//                                transferMyFragmentToPersonalFragment();
//                            } else {
//                                Log.e(TAG, "登录失败！");
//                            }
//                        }
//                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handler=null;
    }
}
