package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.UserMessageJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/11.
 */
public abstract  class UserMessageCallBack  extends Callback<UserMessageJson> {
    @Override
    public UserMessageJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        UserMessageJson responseJson = new Gson().fromJson(string, UserMessageJson.class);
        return responseJson;
    }
}
