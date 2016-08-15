package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TopicRecycleViewAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.TopicGroupJson;
import com.android.loushi.loushi.ui.activity.TopicItemActivity;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.android.loushi.loushi.util.UrlConstant;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TopicFragment extends LazyFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG="TopicFragment";

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TopicRecycleViewAdapter mAdapter;

    private List<TopicGroupJson.BodyBean> topicList=new ArrayList<TopicGroupJson.BodyBean>();

    private boolean isFirstShow = true;  //判断是否是第一次加载

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_topic);
        initView();
        if (isFirstShow) {
            swipeRefreshLayout.setRefreshing(true);
            loadTopicGroup();
            isFirstShow = false;
        }
    }

    private void loadTopicGroup(){
        loadTopicGroup(false);
    }

    private void loadTopicGroup(final boolean isClean){
        OkHttpUtils.post(UrlConstant.TOPICGROUPURL)
                .tag(this)
                .cacheKey("topic")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new JsonCallback<TopicGroupJson>(TopicGroupJson.class){
                    @Override
                    public void onResponse(boolean isFromCache, TopicGroupJson topicGroupJson, Request request, @Nullable Response response) {
                        if(topicGroupJson.getState()){
                            if(isClean)
                                topicList.clear();
                            topicList.addAll(topicGroupJson.getBody());
                            mAdapter.notifyDataSetChanged();
                        }else
                            Toast.makeText(context,topicGroupJson.getReturn_info(),Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

    }

    private void initView(){
        //init swipe
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setProgressViewOffset(
                true,
                0,
                (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));
        swipeRefreshLayout.setOnRefreshListener(this);
        //init recyclerview
        recyclerView= (RecyclerView) findViewById(R.id.recycleView);
        mAdapter=new TopicRecycleViewAdapter(getContext(),topicList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.addItemDecoration(new SpaceItemDecoration(getContext(),16));
        mAdapter.setmOnItemClickListener(new TopicRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                clickTopicItem(position);
            }
        });
        recyclerView.setAdapter(mAdapter);

    }

    private void clickTopicItem(int position){
        Intent intent=new Intent(getContext(), TopicItemActivity.class);
        int topicId=topicList.get(position).getId();
        String topicName=topicList.get(position).getName();
        intent.putExtra(KeyConstant.TOPIC_ID,topicId);
        intent.putExtra(KeyConstant.TOPIC_NAME,topicName);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        loadTopicGroup(true);
    }
}
