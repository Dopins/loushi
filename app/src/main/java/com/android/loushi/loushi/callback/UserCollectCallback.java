package com.android.loushi.loushi.callback;

import android.support.annotation.Nullable;

import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.google.gson.Gson;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/26 0026.
 */
public abstract class UserCollectCallback extends EncryptCallback<ResponseJson>{
    @Override
    public ResponseJson parseNetworkResponse(Response response) throws Exception {
        return new Gson().fromJson(response.body().string(),ResponseJson.class);
    }

}
