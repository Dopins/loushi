package com.android.loushi.loushi.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.GuideRecyclerViewAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.GuideJson;
import com.android.loushi.loushi.jsonbean.RecommendJson;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.jsonbean.SearchJson;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.SearchActivity;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpacesItemDecoration;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by dopin on 2016/7/24.
 */
public class SearchResultGuideFragment extends GuideListFragment {

    private boolean has_no_topic;
    private boolean has_no_strategy;
    private static LocalBroadcastManager localBroadcastManager;
    private BroadcastReceiver mBroadcastReceiver;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        has_no_strategy=false;
        has_no_topic=false;

        mBroadcastReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                has_no_strategy=false;
                has_no_topic=false;
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
        if(has_no_topic&&has_no_strategy) {
            has_data=true;
            swipeRefreshLayout.setRefreshing(false);
            return;
        }

        take=take/2;

        OkHttpUtils.post(BaseActivity.url + "base/search")     //搜索专题
                // 请求方式和请求url
                .tag(this).params("user_id", BaseActivity.user_id)
                .params("type", 1 + "")
                .params("keyword", SearchActivity.keyword)
                .params("skip", skip + "")
                .params("take",take+"")
                .execute(new JsonCallback<SearchJson>(SearchJson.class) {
                    @Override
                    public void onResponse(boolean b, SearchJson searchJson, Request request, Response response) {
                        if (searchJson.getState()) {

                            getTopicBodyBeanList(searchJson.getBody());

                            get_total += bodyBeanList.size();

                            if (bodyBeanList.size() < oneTakeNum/2) has_no_topic = true;

                            guideRecyclerViewAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.d("error", searchJson.getReturn_info());
                        }
                    }
                });

        OkHttpUtils.post(BaseActivity.url + "base/search") //搜索攻略
                // 请求方式和请求url
                .tag(this).params("user_id", BaseActivity.user_id)
                .params("type", 2 + "")
                .params("keyword", SearchActivity.keyword)
                .params("skip", skip + "")
                .params("take",take+"")
                .execute(new JsonCallback<SearchJson>(SearchJson.class) {
                    @Override
                    public void onResponse(boolean b, SearchJson searchJson, Request request, Response response) {
                        if (searchJson.getState()) {

                            getStrategyBodyBeanList(searchJson.getBody());

                            get_total += bodyBeanList.size();

                            if (bodyBeanList.size() < oneTakeNum/2) has_no_strategy = true;

                            guideRecyclerViewAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.d("error", searchJson.getReturn_info());
                        }
                    }
                });
    }
    private void getTopicBodyBeanList(List<SearchJson.BodyBean> searchResultList){
        for(int i=0;i<searchResultList.size(); i++) {
            GuideJson.BodyBean body=searchResultList.get(i).getTopic();
            body.setType(1);
            bodyBeanList.add(body);
        }
    }
    private void getStrategyBodyBeanList(List<SearchJson.BodyBean> searchResultList){
        for(int i=0;i<searchResultList.size(); i++) {
            GuideJson.BodyBean body=searchResultList.get(i).getStrategy();
            body.setType(2);
            bodyBeanList.add(body);
        }
    }
}

