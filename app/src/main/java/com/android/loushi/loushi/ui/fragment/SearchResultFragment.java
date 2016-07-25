package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.ViewPagerAdapter;
import com.android.loushi.loushi.ui.activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dopin on 2016/7/21.
 */
public class SearchResultFragment extends Fragment {
    private View rootView;

    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;                            //定义TabLayout
    private ViewPager viewPager;                             //定义viewPager
    private List<android.support.v4.app.Fragment> list_fragment;    //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private SceneListFragment sceneFragment;
    private GuideListFragment guideFragment;
    private CollectGoodFragment goodsFragment;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewPager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_search_result, null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }


        return rootView;
    }

    private void initViewPager() {

        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_FindFragment_title);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);

        //初始化各fragment
        sceneFragment = new SceneListFragment();
        guideFragment = new GuideListFragment();
        goodsFragment = new CollectGoodFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(sceneFragment);
        list_fragment.add(guideFragment);
        list_fragment.add(goodsFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("场景");
        list_title.add("指南");
        list_title.add("单品");

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(2)));

        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        viewPager.setAdapter(viewPagerAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
        //tab_FindFragment_title.set
    }
}
