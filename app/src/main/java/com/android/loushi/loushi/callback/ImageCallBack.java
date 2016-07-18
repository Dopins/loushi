package com.android.loushi.loushi.callback;

import com.android.loushi.loushi.json.ImageJson;
import com.google.gson.Gson;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/30.
 */
public abstract  class ImageCallBack  extends Callback<ImageJson> {
    @Override
    public ImageJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        ImageJson imageJson = new Gson().fromJson(string, ImageJson.class);
        return imageJson;
    }
}
