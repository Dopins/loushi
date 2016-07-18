package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.TopicGroupjson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by liaoyt on 16-4-28.
 */
public abstract class TopicGroupCallBack extends Callback<TopicGroupjson> {
    @Override
    public TopicGroupjson parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        TopicGroupjson topicGroupjson = new Gson().fromJson(string, TopicGroupjson.class);
        return topicGroupjson;
    }
}
