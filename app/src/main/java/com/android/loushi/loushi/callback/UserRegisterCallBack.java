package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.UserRegisterJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by dell-pc on 2016/4/25.
 */
public abstract class UserRegisterCallBack  extends Callback<UserRegisterJson> {
    @Override
    public UserRegisterJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        UserRegisterJson responseJson = new Gson().fromJson(string, UserRegisterJson.class);
        return responseJson;
    }
}