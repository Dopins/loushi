package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.GuideRecyclerViewAdapter;
import com.android.loushi.loushi.adapter.SceneRecyclerViewAdapter;
import com.android.loushi.loushi.jsonbean.GuideJson;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.android.loushi.loushi.ui.activity.TopicItemActivity;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dopin on 2016/7/30.
 */
public class GuideListFragment extends LazyFragment {

    protected RecyclerView mRecyclerView;
    protected List<GuideJson.BodyBean> bodyBeanList;
    protected GuideRecyclerViewAdapter guideRecyclerViewAdapter;
    protected SwipeRefreshLayout swipeRefreshLayout;  //下拉刷新组件

    protected final int oneTakeNum=10;
    protected int get_total;
    protected boolean has_data;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_scene_list);
        init();
    }


    protected void init() {
        boolean have_data=false;
        if(bodyBeanList==null){
            get_total=0;
            has_data=true;
            bodyBeanList = new ArrayList<>();
        }else have_data=true;

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_widget);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setProgressViewOffset(false, 0, 24);


        guideRecyclerViewAdapter = new GuideRecyclerViewAdapter(getContext(), bodyBeanList);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 10));//设置recycleView间距
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(guideRecyclerViewAdapter);

        setClickListener();
        setRefreshingListener();
        setLoadMoreListener();
        if(have_data){
            guideRecyclerViewAdapter.notifyDataSetChanged();
        }else{
            addSomething2Scene();
        }
    }
    protected void setClickListener(){
        guideRecyclerViewAdapter.setOnItemClickListener(new GuideRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                clickTopicItem(position);
            }
        });
    }
    private void clickTopicItem(int position){
        Intent intent=new Intent(getContext(), TopicItemActivity.class);
        int topicId=bodyBeanList.get(position).getId();
        String topicName=bodyBeanList.get(position).getName();
        intent.putExtra(KeyConstant.TOPIC_ID,topicId);
        intent.putExtra(KeyConstant.TOPIC_NAME,topicName);
        startActivity(intent);
    }
    protected void setLoadMoreListener() {
        mRecyclerView.addOnScrollListener(new MyRecyclerOnScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                if (has_data)
                addSomething2Scene();
            }
        });
    }

    protected void setRefreshingListener(){

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initSearchList();

            }
        });
    }

    protected void initSearchList(){
        get_total = 0;
        bodyBeanList.clear();
        has_data=true;
        addSomething2Scene();
    }
    public void addSomething2Scene() {
        GetSomeScene(oneTakeNum, get_total);
    }
    protected void GetSomeScene(int take, int skip) {
    }
}
