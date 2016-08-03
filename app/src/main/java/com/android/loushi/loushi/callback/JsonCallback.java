package com.android.loushi.loushi.callback;

import android.text.TextUtils;
import android.util.Log;

import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;

import java.lang.reflect.Type;

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



    //该方法是子线程处理，不能做ui相关的工作
    @Override
    public T parseNetworkResponse(Response response) throws Exception {

        //Log.e("te", response.headers().toString());
        String responseData = response.body().string();
        //Log.e("te",responseData);
        if (TextUtils.isEmpty(responseData)) return null;

        /**
         在这里实现code10000的登陆
         */
        //Log.e("te",responseData);
        if (clazz == String.class) {
            Log.e("clazz","0");
            return (T) responseData;
        }
                if (clazz != null) {
                    Log.e("clazz","1");
                    //ResponseJson responseJson = new Gson().fromJson(responseData,ResponseJson.class);
                        return new Gson().fromJson(responseData, clazz);
                }
                if (type != null) {
                    Log.e("clazz","2");
                    return new Gson().fromJson(responseData, type);
                }

//        OkHttpUtils.getInstance().getDelivery().post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

        return null;
    }
}
