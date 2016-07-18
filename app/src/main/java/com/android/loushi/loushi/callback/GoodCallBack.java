package com.android.loushi.loushi.callback;

import com.android.loushi.loushi.json.GoodJson;
import com.google.gson.Gson;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/28.
 */
public abstract class GoodCallBack extends Callback<GoodJson> {
    @Override
    public GoodJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        GoodJson goodJson = new Gson().fromJson(string, GoodJson.class);
        return goodJson;
    }



}