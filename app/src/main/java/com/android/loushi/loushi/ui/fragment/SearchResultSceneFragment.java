package com.android.loushi.loushi.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.jsonbean.SearchJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;

import com.android.loushi.loushi.ui.activity.SearchActivity;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.lzy.okhttputils.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by dopin on 2016/7/17.
 */
public class SearchResultSceneFragment extends SceneListFragment {

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

//        mBroadcastReceiver = new BroadcastReceiver(){
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                initSearchList();
//            }
//
//        };
//        IntentFilter intentFilter = new IntentFilter("search");
//
//        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
//        localBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
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
                .params("type", 0 + "")
                .params("keyword", SearchActivity.keyword)
                .params("skip", skip + "")
                .params("take",take+"")
                .execute(new JsonCallback<SearchJson>(SearchJson.class) {
                    @Override
                    public void onResponse(boolean b, SearchJson searchJson, Request request, Response response) {
                        if (searchJson.getState()) {

                            getSceneBodyBeanList(searchJson.getBody());

                            get_total += searchJson.getBody().size();

                            if(searchJson.getBody().size()<oneTakeNum) has_data=false;

                            sceneRecyclerViewAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.d("error", searchJson.getReturn_info());
                        }
                    }
                });
    }
    private void getSceneBodyBeanList(List<SearchJson.BodyBean> searchResultList){
        for(int i=0;i<searchResultList.size(); i++) {
            bodyBeanList.add(searchResultList.get(i).getScene());
        }
    }

}
