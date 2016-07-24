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
import com.android.loushi.loushi.callback.StrategyCallBack;
import com.android.loushi.loushi.json.Strategyjson;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
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

    private static final String tipsUrl="http://119.29.187.58/LouShi/base/strategy.action";

    private RecyclerView recycleView_tips;
    private SwipeRefreshLayout swipeRefreshLayout_tips;

    private String tempUserID="32";
    private String tempPsw="mtf071330";

    private Integer mSkip=0; //数据从哪里开始取
    private Integer mTake=20;   //一次加载多少item
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
        loadData(tempUserID,mSkip,mTake);
        return view;
    }

    private void loadData(String userId,Integer skip,Integer take){
        OkHttpUtils.post()
                .url(tipsUrl)
                .addParams("user_id", tempUserID)
                .addParams("skip", skip.toString())
                .addParams("take", take.toString())
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
        mSkip+=mTake;
    }

    private void initView(View view){
        mTipsList=new ArrayList<Strategyjson.BodyBean>();
        swipeRefreshLayout_tips= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout_tips);
        swipeRefreshLayout_tips.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout_tips.setSize(SwipeRefreshLayout.DEFAULT);
//        swipeRefreshLayout_tips.setOnRefreshListener();
        recycleView_tips= (RecyclerView) view.findViewById(R.id.recycleView_tips);
        mAdapter=new TipsRecycleViewAdapter(getContext(),mTipsList);
        recycleView_tips.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView_tips.addItemDecoration(new SpaceItemDecoration(getContext(),10));
        recycleView_tips.setAdapter(mAdapter);

        addRefreshListener();
        addOnBottomListener();
        addItemClickListener();

    }

    private void addOnBottomListener(){
        recycleView_tips.addOnScrollListener(new MyRecyclerOnScrollListener(){
            @Override
            public void onBottom() {
                loadData(tempUserID,mSkip,mTake);
                Toast.makeText(getContext(), "底部加载成功, [mSkip,mTake]==["+mSkip+","+mTake+"]", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addItemClickListener(){
        mAdapter.setmOnItemClickListener(new TipsRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(),""+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addRefreshListener(){
        swipeRefreshLayout_tips.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSkip=0;
                mTipsList.clear();
                loadData(tempUserID,mSkip,mTake);
                mAdapter.notifyDataSetChanged();
                swipeRefreshLayout_tips.setRefreshing(false);
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
