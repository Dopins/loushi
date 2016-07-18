package com.android.loushi.loushi.callback;

import com.android.loushi.loushi.json.CommentJson;
import com.google.gson.Gson;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/21.
 */
public abstract class CommentCallBack extends Callback<CommentJson> {
    @Override
    public CommentJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        CommentJson commentJson = new Gson().fromJson(string, CommentJson.class);
        return commentJson;
    }



}
