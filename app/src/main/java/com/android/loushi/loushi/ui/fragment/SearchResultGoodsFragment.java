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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dopin on 2016/8/3.
 */
public class SearchResultGoodsFragment extends GoodsListFragment {

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe
    public void onEventMainThread(String event) {

        initSearchList();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
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

                            if(userCollectionsJson.getBody().size()>0)
                            collectGoodAdapter.notifyDataSetChanged();

                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.d("error", userCollectionsJson.getReturn_info());
                        }
                    }
                });
    }

}
