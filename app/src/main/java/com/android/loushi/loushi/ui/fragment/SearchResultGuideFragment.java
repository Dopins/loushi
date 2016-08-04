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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        has_no_strategy=false;
        has_no_topic=false;
    }
    @Subscribe
    public void onEventMainThread(String event) {
        has_no_strategy=false;
        has_no_topic=false;
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
        if(has_no_topic&&has_no_strategy) {
            has_data=false;
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        swipeRefreshLayout.setRefreshing(true);

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

                            get_total += searchJson.getBody().size();

                            if (searchJson.getBody().size() < oneTakeNum/2) has_no_topic = true;

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

                            get_total += searchJson.getBody().size();

                            if (searchJson.getBody().size() < oneTakeNum / 2)
                                has_no_strategy = true;

                            guideRecyclerViewAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.d("error", searchJson.getReturn_info());
                        }
                    }
                });
    }
    @Override
    protected void setRefreshingListener(){

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                get_total = 0;
                bodyBeanList.clear();
                has_data = true;
                has_no_topic=false;
                has_no_strategy=false;
                addSomething2Scene();

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

