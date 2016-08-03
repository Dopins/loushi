package com.android.loushi.loushi.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.SearchJson;
import com.android.loushi.loushi.jsonbean.UserCollectionsJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.SearchActivity;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dopin on 2016/8/3.
 */
public class SearchResultGoodsFragment extends GoodsListFragment {
    private static LocalBroadcastManager localBroadcastManager;
    private BroadcastReceiver mBroadcastReceiver;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);

        mBroadcastReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                initSearchList();
            }

        };
        IntentFilter intentFilter = new IntentFilter("search");

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void GetSomeScene(int take, int skip) {

        OkHttpUtils.post(BaseActivity.url + "base/search")
                // 请求方式和请求url
                .tag(this).params("user_id", BaseActivity.user_id)
                .params("type", 3 + "")
                .params("keyword", SearchActivity.keyword)
                .params("skip", skip + "")
                .params("take",take+"")
                .execute(new JsonCallback<UserCollectionsJson>(UserCollectionsJson.class) {
                    @Override
                    public void onResponse(boolean b, UserCollectionsJson userCollectionsJson, Request request, Response response) {
                        if (userCollectionsJson.getState()) {

                            bodyBeanList.addAll(userCollectionsJson.getBody());

                            get_total += userCollectionsJson.getBody().size();

                            if(userCollectionsJson.getBody().size()<oneTakeNum) has_data=false;

                            collectGoodAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.d("error", userCollectionsJson.getReturn_info());
                        }
                    }
                });
    }

}
