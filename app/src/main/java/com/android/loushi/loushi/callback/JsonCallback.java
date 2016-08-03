package com.android.loushi.loushi.callback;

import android.text.TextUtils;
import android.util.Log;

import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;

import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;


public abstract class JsonCallback<T> extends EncryptCallback<T> {
private Boolean log=true;
    private Class<T> clazz;
    private Type type;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonCallback(Type type) {
        this.type = type;
    }



    //该方法是子线程处理，不能做ui相关的工作
    @Override
    public T parseNetworkResponse(Response response) throws Exception {


        String responseData = response.body().string();
        JSONObject jsonObject = new JSONObject(responseData);
        String code = jsonObject.optString("code", "");
        if(code!=null&&code.equals("10000")) {
            Log.e("clazz", code);
            if(log) {

                //执行登陆操作
            }
//            OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
//
//                    // 请求方式和请求url
//                    .tag(this).params("mobile_phone", "13750065622").params("password", "mtf071330")
//                    .params("isThird", "false")
//                            // 请求的 tag, 主要用于取消对应的请求
//                            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                            // 缓存模式，详细请看缓存介绍
//
//                            //有的操作要设置缓存，有的不要 这个号供测试用
//
//                    .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
//                        @Override
//                        public void onResponse(boolean b, UserLoginJson userLoginJson, Request request, Response response) {
//
//                            //这里现在是48
//                            //Log.e(TAG, Integer.toString(userLoginJson.getBody()));
//                            //这里现在是48了
//                            //Log.e("test", response.toString());
//
//
//                        }
//
//
//                    });
            return null;
        }
        //Log.e("te",responseData);
//        if (TextUtils.isEmpty(responseData)) return null;
        //JSONObject jsonObject = new JSONObject(responseData);
       // String res=responseData;
       // ResponseJson responseJson = new Gson().fromJson(res, ResponseJson.class);
//        if (responseJson.getCode()!=null)
//            Log.e("clazz","4");
//        else
//        Log.e("clazz","5");
//        final String code =jsonObject.optString("code");
//        if(TextUtils.isEmpty(code)) {

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
}
