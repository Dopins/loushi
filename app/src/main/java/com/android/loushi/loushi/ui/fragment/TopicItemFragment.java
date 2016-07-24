package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;

/**
 * Created by binpeiluo on 2016/7/24 0024.
 */
public class TopicItemFragment extends BaseFragment{

    private RecyclerView recyclerView;
    private ImageView imageViewSearch;
    private TextView textViewTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.activity_topic_item,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycleView_topic);
        imageViewSearch= (ImageView) view.findViewById(R.id.imageView_search);
        textViewTitle= (TextView) view.findViewById(R.id.textView_title);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(){

    }

}
