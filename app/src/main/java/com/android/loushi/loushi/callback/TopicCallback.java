package com.android.loushi.loushi.callback;

import com.android.loushi.loushi.jsonbean.TopicGroupJson;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.google.gson.Gson;

import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/25 0025.
 */
public abstract  class TopicCallback extends EncryptCallback<TopicJson>{
    @Override
    public TopicJson parseNetworkResponse(Response response) throws Exception {
        return new Gson().fromJson(response.body().string(),TopicJson.class);
    }
}
