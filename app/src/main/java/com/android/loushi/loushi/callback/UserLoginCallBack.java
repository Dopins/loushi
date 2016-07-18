package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.UserLoginJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by dell-pc on 2016/4/24.
 */
public abstract class UserLoginCallBack  extends Callback<UserLoginJson> {
    @Override
    public UserLoginJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        UserLoginJson responseJson = new Gson().fromJson(string, UserLoginJson.class);
        return responseJson;
    }
}