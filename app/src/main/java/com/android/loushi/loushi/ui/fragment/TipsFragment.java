package com.android.loushi.loushi.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TipsRecycleViewAdapter;
import com.android.loushi.loushi.callback.StrategyCallBack;
import com.android.loushi.loushi.json.Strategyjson;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TipsFragment extends BaseFragment {

    private static final String TAG="TipsFragment";

    private RecyclerView recycleView_tips;
    private SwipeRefreshLayout swipeRefreshLayout_tips;

    private String tempUserID="32";
    private String tempPsw="mtf071330";

    private Integer skip=0;
    private List<Strategyjson.BodyBean> mTipsList;
    private TipsRecycleViewAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.e(TAG,"onActivityCreated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Log.e(TAG,"onCreateView");
//        return super.onCreateView(inflater, container, savedInstanceState);  recycleView_tips
        View view=inflater.inflate(R.layout.fragment_tips,container,false);
        initView(view);
        loadData();
        return view;
    }

    private void loadData(){

        OkHttpUtils.post()
                .url("http://119.29.187.58/LouShi/base/strategy.action")
                .addParams("user_id", tempUserID)
                .addParams("skip", skip.toString())
                .addParams("take", "20")
                .build()
                .execute(new StrategyCallBack() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Log.e(TAG,"error");
                    }

                    @Override
                    public void onResponse(Strategyjson response) {
                        Log.e(TAG,"success"+response.toString());
                        for (int i = 0; i < response.getBody().size(); i++) {
                            Strategyjson.BodyBean temp = response.getBody().get(i);
                            mTipsList.add(temp);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }

    private void initView(View view){
        mTipsList=new ArrayList<Strategyjson.BodyBean>();
        swipeRefreshLayout_tips= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout_tips);
        swipeRefreshLayout_tips.setColorSchemeColors(Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW);
        swipeRefreshLayout_tips.setSize(SwipeRefreshLayout.LARGE);
//        swipeRefreshLayout_tips.setOnRefreshListener();
        recycleView_tips= (RecyclerView) view.findViewById(R.id.recycleView_tips);
        mAdapter=new TipsRecycleViewAdapter(getContext(),mTipsList);
        recycleView_tips.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView_tips.addItemDecoration(new SpaceItemDecoration(getContext(),10));
        recycleView_tips.setAdapter(mAdapter);

    }

}
