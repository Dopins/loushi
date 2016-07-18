package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.UserForgetPasswordJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by dell-pc on 2016/4/25.
 */
public abstract class UserForgetPasswordCallBack  extends Callback<UserForgetPasswordJson> {
    @Override
    public UserForgetPasswordJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        UserForgetPasswordJson responseJson = new Gson().fromJson(string, UserForgetPasswordJson.class);
        return responseJson;
    }
}