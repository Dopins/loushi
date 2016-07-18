package com.android.loushi.loushi.callback;


import com.google.gson.Gson;
import com.android.loushi.loushi.json.SceneJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/25.
 */
public abstract class SceneCallBack extends Callback<SceneJson> {
@Override
public SceneJson parseNetworkResponse(Response response) throws IOException
        {
        String string = response.body().string();
        SceneJson sceneJson = new Gson().fromJson(string, SceneJson.class);
        return sceneJson;
        }

        }
