package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TopicItemAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.ui.activity.CategoryDetailActivity;
import com.android.loushi.loushi.ui.activity.MainActivity;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.jsonbean.StrategyJson;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.android.loushi.loushi.util.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TipsFragment extends LazyFragment {

    private static final String TAG = "TipsFragment";

    private RecyclerView recycleViewTips;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Integer mSkip = 0;
    private Integer mTake = 20;
    private List mTipsList = new ArrayList<>();
    private TopicItemAdapter mAdapter;

    private boolean isFirstShow = true;  //判断是否是第一次加载

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tips);
        initView();
        if (isFirstShow) {
            loadData();
            isFirstShow = false;
        }
    }

    private void loadData() {
        loadData(MainActivity.user_id, mSkip, mTake);
    }


    private void loadData(String userId, Integer skip, final Integer take) {

        Log.i("test", "tips load --skip,take==" + skip + "," + take + ",,,isFirstShow==" + isFirstShow);
        OkHttpUtils.post(UrlConstant.TIPSCURL)
                .tag(this)
                .params(KeyConstant.USER_ID, userId)
                .params(KeyConstant.SKIP, skip.toString())
                .params(KeyConstant.TAKE, take.toString())
                .execute(new JsonCallback<StrategyJson>(StrategyJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, StrategyJson strategyJson, Request request, @Nullable Response response) {

                        Log.i(TAG, "onResponse-- " + new Gson().toJson(strategyJson));
                        if (strategyJson.getState()) {
                            mTipsList.addAll(strategyJson.getBody());
                            mAdapter.notifyDataSetChanged();
//                            mSkip += mTake;
                            mSkip += strategyJson.getBody().size();
                        } else
                            Toast.makeText(getContext(), "" + strategyJson.getReturn_info(), Toast.LENGTH_SHORT).show();
//                        Log.e(TAG,bodyBean.getBody().size()+"");
                    }
                });
    }

    private void initView() {

        initRecycleView();

        addRefreshListener();
        addOnBottomListener();
        addItemClickListener();

    }

    private void initRecycleView() {

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        recycleViewTips = (RecyclerView) findViewById(R.id.recycleView);
        mAdapter = new TopicItemAdapter(getContext(),
                mTipsList,
                TopicItemAdapter.AdapterType.TIPS);
        recycleViewTips.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleViewTips.addItemDecoration(new SpaceItemDecoration(getContext(), 10));
        recycleViewTips.setAdapter(mAdapter);
    }

    private void addOnBottomListener() {
        recycleViewTips.addOnScrollListener(new MyRecyclerOnScrollListener() {
            @Override
            public void onBottom() {
//                loadData(tempUserID,mSkip,mTake);
            }
        });

    }

    private void addItemClickListener() {
        mAdapter.setmOnItemClickListener(new TopicItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CategoryDetailActivity.class);
                String jsonString = new Gson().toJson(mTipsList.get(position));
                Log.e("jsonstring", jsonString);
                intent.putExtra(CategoryDetailActivity.TYPE, "2");
                intent.putExtra(CategoryDetailActivity.JSONSTRING, jsonString);
                startActivity(intent);
            }
        });
    }

    private void addRefreshListener() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSkip = 0;
                mTipsList.clear();
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


}
