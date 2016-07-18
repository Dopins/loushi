package com.android.loushi.loushi.callback;


import com.google.gson.Gson;
import com.android.loushi.loushi.json.SceneGroupJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/21.
 */
public abstract class SceneGroupCallBack extends Callback<SceneGroupJson> {
    @Override
    public SceneGroupJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        SceneGroupJson responseJson = new Gson().fromJson(string, SceneGroupJson.class);
        return responseJson;
    }
}
