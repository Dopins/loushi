package com.android.loushi.loushi.callback;

import com.google.gson.Gson;
import com.android.loushi.loushi.json.ResponseJson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/18.
 */
public abstract class NormalCallBack extends Callback<ResponseJson> {
    @Override
    public ResponseJson parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        ResponseJson responseJson = new Gson().fromJson(string, ResponseJson.class);
        return responseJson;
    }

}
