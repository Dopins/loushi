package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.SceneRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dopin on 2016/7/17.
 */
public class RecommendFragment extends LazyFragment {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private SceneRecycleViewAdapter sceneRecycleViewAdapter;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_recommend);
        init();
    }
    private void init(){
        initData();
        sceneRecycleViewAdapter = new SceneRecycleViewAdapter(getContext(),mDatas);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(sceneRecycleViewAdapter);

    }
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }
}
