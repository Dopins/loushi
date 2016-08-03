package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.CollectGoodAdapter;
import com.android.loushi.loushi.adapter.SceneRecyclerViewAdapter;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.jsonbean.UserCollectionsJson;
import com.android.loushi.loushi.ui.activity.SceneDetailActivity;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.android.loushi.loushi.util.SpacesItemDecoration;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dopin on 2016/8/3.
 */
public class GoodsListFragment extends LazyFragment {

    protected RecyclerView mRecyclerView;
    protected List<UserCollectionsJson.BodyBean> bodyBeanList = new ArrayList<>();
    protected CollectGoodAdapter collectGoodAdapter;
    protected SwipeRefreshLayout swipeRefreshLayout;  //下拉刷新组件

    protected final int oneTakeNum=10;
    protected int get_total=0;
    protected boolean has_data=true;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_scene_list);
        init();
    }


    protected void init() {
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_widget);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setProgressViewOffset(false, 0, 24);


        collectGoodAdapter = new CollectGoodAdapter(getContext(), bodyBeanList,"3");
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);

        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(getContext(),5);
        mRecyclerView.addItemDecoration(spaceItemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(collectGoodAdapter);

        setClickListener();
        setRefreshingListener();
        setLoadMoreListener();
        addSomething2Scene();
    }
    protected void setClickListener(){
//        collectGoodAdapter.setOnItemClickListener(new CollectGoodAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                //Toast.makeText(getContext(), "点击item" + position, Toast.LENGTH_SHORT).show();
//                Intent intent= new Intent(getActivity(), SceneDetailActivity.class);
//                String sceneJsonString=new Gson().toJson(bodyBeanList.get(position));
//                intent.putExtra("SCENE_STRING",sceneJsonString);
//                startActivity(intent);
//                Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                //intent.putExtra
//                //传入参数 给webview Post
//                int pos = position;
//                if (tabIndex == 0)
//                    pos = position - 1;
//                //pos -=1;
//                intent.putExtra(WebViewActivity.TYPE, "0");
//                //将scene以json格式传入
//                intent.putExtra(WebViewActivity.SCENE, new Gson().toJson(bodyBeanList.get(pos)));
//
//
//                startActivityForResult(intent, 2);
//            }
//        });
    }
    protected void setLoadMoreListener() {
        mRecyclerView.addOnScrollListener(new MyRecyclerOnScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                if(has_data)
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
        swipeRefreshLayout.setRefreshing(true);
        GetSomeScene(oneTakeNum, get_total);
    }
    protected void GetSomeScene(int take, int skip) {

    }
}