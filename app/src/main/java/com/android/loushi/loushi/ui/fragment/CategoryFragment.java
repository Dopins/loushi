package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.CategoryRecycleViewAdapter;
import com.android.loushi.loushi.util.SpaceItemDecoration;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CategoryFragment extends BaseFragment {


    private TextView textView_title;
    private RecyclerView recyclerView;
    private CategoryRecycleViewAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        return view;
    }


    private void initView(View view){
        textView_title= (TextView) view.findViewById(R.id.textView_guideTitle);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycleView_guide);
        mAdapter=new CategoryRecycleViewAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.addItemDecoration(new SpaceItemDecoration(getContext(),16));
        recyclerView.setAdapter(mAdapter);

    }
}
