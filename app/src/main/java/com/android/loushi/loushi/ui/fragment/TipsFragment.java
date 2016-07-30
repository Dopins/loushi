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
import com.android.loushi.loushi.ui.activity.CategoryDetailActivity;
import com.android.loushi.loushi.ui.activity.MainActivity;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.jsonbean.StrategyJson;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.android.loushi.loushi.util.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TipsFragment extends LazyFragment {

    private static final String TAG = "TipsFragment";

    private RecyclerView recycleView_tips;
    private SwipeRefreshLayout swipeRefreshLayout_tips;

    private Integer mSkip = 0;
    private Integer mTake = 20;
    private List mTipsList= new ArrayList<>();
    private TopicItemAdapter mAdapter;

    private boolean isFirstShow=true;  //判断是否是第一次加载

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tips);
        initView();
        if(isFirstShow){
            loadData(MainActivity.user_id, mSkip, mTake);
            isFirstShow=false;
        }
    }

    private void loadData(String userId, Integer skip, final Integer take){
        Log.i("test","GetSomeScene--skip,take=="+skip+","+take+",,,isFirstShow=="+isFirstShow);
        OkHttpUtils.post(UrlConstant.TIPSCURL)
                .tag(this)
                .params(KeyConstant.USER_ID, userId)
                .params(KeyConstant.SKIP,skip.toString())
                .params(KeyConstant.TAKE,take.toString())
                .execute(new AbsCallback<StrategyJson>() {
                    @Override
                    public StrategyJson parseNetworkResponse(Response response) throws Exception {
                        return new Gson().fromJson(response.body().string(),StrategyJson.class);
                    }
                    @Override
                    public void onResponse(boolean isFromCache, StrategyJson bodyBean, Request request, @Nullable Response response) {
                        Log.i(TAG,"onResponse-- "+new Gson().toJson(bodyBean));
                        if(bodyBean.getState()){
                            mSkip+=mTake;
                            mTipsList.addAll(bodyBean.getBody());
                            mAdapter.notifyDataSetChanged();
                        }else
                            Toast.makeText(getContext(),""+bodyBean.getReturn_info(),Toast.LENGTH_SHORT).show();
//                        Log.e(TAG,bodyBean.getBody().size()+"");
                    }
                });

    }

    private void initView() {
        swipeRefreshLayout_tips = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout_tips.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout_tips.setSize(SwipeRefreshLayout.DEFAULT);
        recycleView_tips = (RecyclerView) findViewById(R.id.recycleView);
        mAdapter = new TopicItemAdapter(getContext(),
                mTipsList,
                TopicItemAdapter.AdapterType.TIPS);
        recycleView_tips.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView_tips.addItemDecoration(new SpaceItemDecoration(getContext(), 10));
        recycleView_tips.setAdapter(mAdapter);

        addRefreshListener();
        addOnBottomListener();
        addItemClickListener();

    }

    private void addOnBottomListener() {
        recycleView_tips.addOnScrollListener(new MyRecyclerOnScrollListener() {
            @Override
            public void onBottom() {
//                loadData(tempUserID,mSkip,mTake);
                Toast.makeText(getContext(), "底部加载成功, [mSkip,mTake]==[" + mSkip + "," + mTake + "]", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addItemClickListener() {
        mAdapter.setmOnItemClickListener(new TopicItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getActivity(), CategoryDetailActivity.class);
                String jsonString=new Gson().toJson(mTipsList.get(position));
                Log.e("jsonstring",jsonString);
                intent.putExtra(CategoryDetailActivity.TYPE,"2");
                intent.putExtra(CategoryDetailActivity.JSONSTRING,jsonString);
                startActivity(intent);
            }
        });
    }

    private void addRefreshListener() {
        swipeRefreshLayout_tips.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSkip = 0;
                mTipsList.clear();
                loadData(MainActivity.user_id, mSkip, mTake);
                swipeRefreshLayout_tips.setRefreshing(false);
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
