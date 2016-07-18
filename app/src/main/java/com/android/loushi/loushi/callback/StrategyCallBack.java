package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.Strategyjson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by liaoyt on 16-4-28.
 */
public abstract class StrategyCallBack extends Callback<Strategyjson> {
    @Override
    public Strategyjson parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        Strategyjson strategyjson = new Gson().fromJson(string, Strategyjson.class);
        return strategyjson;
    }
}
