package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MyFragment extends BaseFragment {
    private Toolbar mToolbar;
    private TextView mTv_index;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
//        mToolbar=(Toolbar)getView().findViewById(R.id.program_toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
//        mToolbar.setTitle("");
//        mTv_index=(TextView)mToolbar.findViewById(R.id.toolbar_index);
//        mTv_index.setText("我的");

        //mToolbar.setTitle("loushi");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);


        return inflater.inflate(R.layout.fragment_my, container, false);
    }
}
