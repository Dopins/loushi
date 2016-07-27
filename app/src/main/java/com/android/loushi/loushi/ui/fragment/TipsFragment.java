package com.android.loushi.loushi.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TipsRecycleViewAdapter;
import com.android.loushi.loushi.adapter.TopicItemRecycleViewAdapter;
import com.android.loushi.loushi.callback.EncryptCallback;
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

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TipsFragment extends BaseFragment {

    private static final String TAG = "TipsFragment";

    private RecyclerView recycleView_tips;
    private SwipeRefreshLayout swipeRefreshLayout_tips;

    private Integer mSkip = 0;
    private Integer mTake = 0;
    private List mTipsList;
    private TopicItemRecycleViewAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);  recycleView_tips
        Log.i(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        initView(view);
        loadData(MainActivity.user_id,mSkip,mTake);
        return view;
    }

    private void loadData(String userId, Integer skip, final Integer take){

        //TODO  数据为空
        OkHttpUtils.post(UrlConstant.TIPSCURL)
                .tag(this)
                .params(KeyConstant.USER_ID, userId)
                .params(KeyConstant.SKIP,skip.toString())
                .params(KeyConstant.TAKE,take.toString())
                .execute(new EncryptCallback<StrategyJson>() {
                    @Override
                    public StrategyJson parseNetworkResponse(Response response) throws Exception {
                        Log.i(TAG,response.toString());
                        return new Gson().fromJson(response.body().string(),StrategyJson.class);
                    }

                    @Override
                    public void onResponse(boolean isFromCache, StrategyJson bodyBean, Request request, @Nullable Response response) {
                        Log.i(TAG,new Gson().toJson(bodyBean));
                        Log.e(TAG,bodyBean.getBody().size()+"");
                        mTipsList.addAll(bodyBean.getBody());
                        mAdapter.notifyDataSetChanged();
                        mSkip+=take;
                    }
                });
//        OkHttpUtils.post()
//                .url(tipsUrl)
//                .addParams("user_id", tempUserID)
//                .addParams("skip", skip.toString())
//                .addParams("take", take.toString())
//                .build()
//                .execute(new StrategyCallBack() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        Log.e(TAG,"error");
//                    }
//
//                    @Override
//                    public void onResponse(Strategyjson response) {
//                        Log.e(TAG,"success"+response.toString());
//                        for (int i = 0; i < response.getBody().size(); i++) {
//                            Strategyjson.BodyBean temp = response.getBody().get(i);
//                            mTipsList.add(temp);
//                        }
//                        mAdapter.notifyDataSetChanged();
//                    }
//                });
//        mSkip+=mTake;

    }

    private void initView(View view) {
        mTipsList = new ArrayList();
        swipeRefreshLayout_tips = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout_tips.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout_tips.setSize(SwipeRefreshLayout.DEFAULT);
//        swipeRefreshLayout_tips.setOnRefreshListener();
        recycleView_tips = (RecyclerView) view.findViewById(R.id.recycleView);
        mAdapter = new TopicItemRecycleViewAdapter(getContext(),
                mTipsList,
                TopicItemRecycleViewAdapter.AdapterType.TIPS);
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
        mAdapter.setmOnItemClickListener(new TopicItemRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
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
                mAdapter.notifyDataSetChanged();
                swipeRefreshLayout_tips.setRefreshing(false);
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
