package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TopicRecycleViewAdapter;
import com.android.loushi.loushi.util.SpaceItemDecoration;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TopicFragment extends BaseFragment{

    private TextView textView_title;
    private RecyclerView recyclerView;
    private TopicRecycleViewAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_category_topic, container, false);
        initView(view);
        return view;
    }


    private void initView(View view){

        textView_title= (TextView) view.findViewById(R.id.textView_topicTitle);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycleView_topic);
        mAdapter=new TopicRecycleViewAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.addItemDecoration(new SpaceItemDecoration(getContext(),16));
        recyclerView.setAdapter(mAdapter);

    }
}
