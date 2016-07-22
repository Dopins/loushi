package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.CategoryViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CategoryFragment extends BaseFragment {

    private Toolbar toolbar;
    private TabLayout tablayout_category;
    private ViewPager viewPager_category;

    private List<String> mTitleList;
    private List<Fragment> mFragmentList;
    private CategoryViewPagerAdapter mAdapter;

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

        mTitleList=new ArrayList<String>();
        mTitleList.add("专题");
        mTitleList.add("贴士");
        mFragmentList=new ArrayList<Fragment>();
        mFragmentList.add(new TopicFragment());
        mFragmentList.add(new TipsFragment());


        toolbar= (Toolbar) view.findViewById(R.id.toolbar);
        tablayout_category= (TabLayout) view.findViewById(R.id.tablayout_category);
        viewPager_category= (ViewPager) view.findViewById(R.id.viewPager_category);

        for(String title:mTitleList){
            tablayout_category.addTab(tablayout_category.newTab().setText(title));
        }

        mAdapter=new CategoryViewPagerAdapter(
                getActivity().getSupportFragmentManager(),mTitleList,mFragmentList);
        viewPager_category.setAdapter(mAdapter);
        tablayout_category.setupWithViewPager(viewPager_category);
    }
}
