package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.CategoryViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CategoryFragment extends BaseFragment {

    private static final String TAG="CategoryFragment";

    private View rootView;
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
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.fragment_category, null);

        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，
        // 要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void initView(){

        mTitleList=new ArrayList<String>();
        mTitleList.add("专题");
        mTitleList.add("贴士");
        mFragmentList=new ArrayList<Fragment>();
        mFragmentList.add(new TopicFragment());
        mFragmentList.add(new TipsFragment());
//        mFragmentList.add(new StyleFragment());


        toolbar= (Toolbar) rootView.findViewById(R.id.toolbar);
        tablayout_category= (TabLayout) rootView.findViewById(R.id.tablayout_category);
        tablayout_category.setTabMode(TabLayout.MODE_FIXED);
        viewPager_category= (ViewPager) rootView.findViewById(R.id.viewPager_category);

        for(String title:mTitleList){
            tablayout_category.addTab(tablayout_category.newTab().setText(title));
        }

        mAdapter=new CategoryViewPagerAdapter(
                getChildFragmentManager(),mTitleList,mFragmentList);
//                getActivity().getSupportFragmentManager(),mTitleList,mFragmentList);
        viewPager_category.setAdapter(mAdapter);
        tablayout_category.setupWithViewPager(viewPager_category);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
