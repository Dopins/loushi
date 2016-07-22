package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TipsRecycleViewAdapter;
import com.android.loushi.loushi.util.SpaceItemDecoration;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TipsFragment extends BaseFragment {

    private RecyclerView recycleView_tips;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);  recycleView_tips
        View view=inflater.inflate(R.layout.fragment_tips,container,false);
        initView(view);
        return view;
    }
    private void initView(View view){
        recycleView_tips= (RecyclerView) view.findViewById(R.id.recycleView_tips);
        TipsRecycleViewAdapter adapter=new TipsRecycleViewAdapter(getContext());
        recycleView_tips.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView_tips.addItemDecoration(new SpaceItemDecoration(getContext(),10));
        recycleView_tips.setAdapter(adapter);

    }

}
