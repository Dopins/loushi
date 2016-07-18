package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.Topicjson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by liaoyt on 16-4-28.
 */
public abstract class TopicCallBack extends Callback<Topicjson> {
    @Override
    public Topicjson parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        Topicjson topicjson = new Gson().fromJson(string, Topicjson.class);
        return topicjson;
    }
}
