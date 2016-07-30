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
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by dopin on 2016/7/17.
 */
public class SearchResultSceneFragment extends SceneListFragment {
    private static LocalBroadcastManager localBroadcastManager;
    private BroadcastReceiver mBroadcastReceiver;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);

        mBroadcastReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                bodyBeanList.clear();
                get_total=0;
                addSomething2Scene();
            }

        };
        IntentFilter intentFilter = new IntentFilter("search");

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(mBroadcastReceiver);
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

                            get_total += bodyBeanList.size();

                            if(bodyBeanList.size()<oneTakeNum) has_data=false;

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
