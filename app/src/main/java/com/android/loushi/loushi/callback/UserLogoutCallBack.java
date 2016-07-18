package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.UserLogoutJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by dell-pc on 2016/4/25.
 */
public abstract class UserLogoutCallBack  extends Callback<UserLogoutJson> {
    @Override
    public UserLogoutJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        UserLogoutJson responseJson = new Gson().fromJson(string, UserLogoutJson.class);
        return responseJson;
    }
}