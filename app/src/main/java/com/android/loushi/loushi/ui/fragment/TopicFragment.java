package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TopicRecycleViewAdapter;
import com.android.loushi.loushi.ui.activity.TopicItemActivity;
import com.android.loushi.loushi.util.SpaceItemDecoration;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TopicFragment extends BaseFragment{

    private static final String TAG="TopicFragment";

    private TextView textView_title;
    private RecyclerView recyclerView;
    private TopicRecycleViewAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
//        Log.e(TAG,"onActivityCreated");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Log.e(TAG,"onCreateView");
        View view=inflater.inflate(R.layout.fragment_topic, container, false);
        initView(view);
        return view;
    }


    private void initView(View view){

        textView_title= (TextView) view.findViewById(R.id.textView_topicTitle);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycleView);
        mAdapter=new TopicRecycleViewAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.addItemDecoration(new SpaceItemDecoration(getContext(),16));
        mAdapter.setmOnItemClickListener(new TopicRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(),""+position,Toast.LENGTH_SHORT).show();
                clickTopicItem(position);
            }
        });
        recyclerView.setAdapter(mAdapter);

    }

    private void clickTopicItem(int position){
        Intent intent=new Intent(getContext(), TopicItemActivity.class);
        intent.putExtra(TopicItemActivity.TOPIC_ID,position);
        startActivity(intent);
    }



}
