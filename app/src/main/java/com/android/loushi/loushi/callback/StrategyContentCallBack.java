package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.StrategyContentjson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by liaoyt on 16-4-28.
 */
public abstract class StrategyContentCallBack extends Callback<StrategyContentjson> {
    @Override
    public StrategyContentjson parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        StrategyContentjson strategyContentjson = new Gson().fromJson(string, StrategyContentjson.class);
        return strategyContentjson;
    }
}
