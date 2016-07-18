package com.android.loushi.loushi.callback;

import com.android.loushi.loushi.json.CollectionsJson;
import com.google.gson.Gson;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/2.
 */
public abstract class CollecttionsCallBack  extends Callback<CollectionsJson> {
    @Override
    public CollectionsJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        CollectionsJson collectionsJson = new Gson().fromJson(string, CollectionsJson.class);
        return collectionsJson;
    }
}
