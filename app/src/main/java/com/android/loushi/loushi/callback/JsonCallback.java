package com.android.loushi.loushi.callback;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.CorrectionInfo;

import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.event.ReceiveSmsEvent;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.ToastUtils;
import com.android.loushi.loushi.util.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.lzy.okhttputils.request.BaseRequest;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


public abstract class JsonCallback<T> extends EncryptCallback<T> {

    private Class<T> clazz;
    private Type type;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);

    }

    //该方法是子线程处理，不能做ui相关的工作
    @Override
    public T parseNetworkResponse(Response response) throws Exception {


        String responseData = response.body().string();
        JSONObject jsonObject = new JSONObject(responseData);
        String code = jsonObject.optString("code", "");
        if (code != null && (code.equals("10000") || code.equals("10001"))) {
            Log.e("clazz", code);
            if (CurrentAccount.getLoginOrNot()) {
                if (!CurrentAccount.isThird()) {
                    String phone = CurrentAccount.getAccount();
                    String password = CurrentAccount.getPassword();
                    Log.e("my", phone + password);
                    OkHttpUtils.post(UrlConstant.USERLOGINURL)
                            .params(KeyConstant.MOBILE_PHONE, phone)
                            .params(KeyConstant.PASSWORD, password)
                            .params(KeyConstant.ISTHIRD, "false")
                            .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                                @Override
                                public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                                    if (userLoginJson.getState()) {

                                        BaseActivity.user_id = userLoginJson.getBody() + "";
                                        CurrentAccount.setUserId(BaseActivity.user_id);
                                        Log.e("callback", "autoLogin 登录成功！");
                                    } else {
                                        Log.e("callback", "autoLogin 登录失败！");
                                        CurrentAccount.setLoginOrNot(false);
                                    }
                                }
                            });
                } else {
                    final String account = CurrentAccount.getAccount();
                    String password = CurrentAccount.getPassword();
                    final String type = CurrentAccount.getThirdType();

                    OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
                            .params("account", account)
                            .params("type", type)
                            .params("token", password)
                            .params("isThird", "true")
                            .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                                @Override
                                public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                                    Log.e("splash", "loginresponse");
                                    if (userLoginJson.getState()) {
                                        //CurrentAccount.setLoginOrNot(true);//登录成功，设置登录状态
                                        String code = userLoginJson.getCode();
                                        if (code != null && code.equals("3")) {

                                        } else {
                                            BaseActivity.user_id = userLoginJson.getBody() + "";
                                            CurrentAccount.setUserId(userLoginJson.getBody() + "");
                                        }

                                    } else {
                                        CurrentAccount.setLoginOrNot(false);
                                        Log.e("splashthirdlogin", "登录失败！");
                                    }
                                }

                                @Override
                                public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                                    super.onError(isFromCache, call, response, e);
                                    Log.e("splash", "loginerror");
                                }
                            });
                }
                //执行登陆操作

            }
            if (!CurrentAccount.getLoginOrNot()) {
                EventBus.getDefault().post(new MainEvent(MainEvent.NEED_LOGIN));
            }

        }


        /**
         在这里实现code10000的登陆
         */
        //Log.e("te",responseData);
        if (clazz == String.class) {
            Log.e("clazz", "0");
            return (T) responseData;
        }
        if (clazz != null) {
            Log.e("clazz", "1");

            //ResponseJson responseJson = new Gson().fromJson(responseData,ResponseJson.class);
            return new Gson().fromJson(responseData, clazz);
        }
        if (type != null) {
            Log.e("clazz", "2");
            return new Gson().fromJson(responseData, type);
        }

//        OkHttpUtils.getInstance().getDelivery().post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        //}

        return null;
    }

    @Override
    public void onError(boolean isFromCache, Call call, Response response, Exception e) {
        super.onError(isFromCache, call, response, e);
        //Log.e("callerror",response.toString());
        EventBus.getDefault().post(new MainEvent(MainEvent.STATE_CONNECT_FAIL));
    }
}
