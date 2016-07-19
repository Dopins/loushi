package com.android.loushi.loushi.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.HotWordRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dopin on 2016/7/18.
 */
public class SearchActivity extends Activity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HotWordRecycleViewAdapter hRecycleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }
    private void init(){
        initData();
        hRecycleAdapter= new HotWordRecycleViewAdapter(SearchActivity.this,mDatas);
        mRecyclerView = (RecyclerView)findViewById(R.id.hotWordRecycleView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(hRecycleAdapter);
    }
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 5; i++)
        {
            mDatas.add("热搜词"+(i+1));
        }
    }
}
