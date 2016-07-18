package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.UserFeedbackJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by dell-pc on 2016/4/25.
 */
public abstract class UserFeedbackCallBack  extends Callback<UserFeedbackJson> {
    @Override
    public UserFeedbackJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        UserFeedbackJson responseJson = new Gson().fromJson(string, UserFeedbackJson.class);
        return responseJson;
    }
}