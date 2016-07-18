package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.UpdateVersionJson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/9.
 */
public abstract class UpdateVersionCallBack extends Callback<UpdateVersionJson> {
    @Override
    public UpdateVersionJson parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        UpdateVersionJson updateVersionJson = new Gson().fromJson(string, UpdateVersionJson.class);
        return updateVersionJson;
    }
}


