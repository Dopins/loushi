package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.UserInfoJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/21.
 */
public abstract class UserInfoCallBack  extends Callback<UserInfoJson> {
    @Override
    public UserInfoJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        UserInfoJson responseJson = new Gson().fromJson(string, UserInfoJson.class);
        return responseJson;
    }
}
