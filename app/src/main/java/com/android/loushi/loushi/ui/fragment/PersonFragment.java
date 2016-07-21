package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */

//个人中心
public class PersonFragment extends BaseFragment {

    private Toolbar mToolbar;
    private TextView mTv_index;
    private TabLayout mtoorbar_tab;
    private ViewPager vp_FindFragment_pager;                             //定义viewPager
    private ViewPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private CategoryFragment categoryFragment;
    private ViewPager mViewPager;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
//        mToolbar=(Toolbar)getView().findViewById(R.id.program_toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
//        mToolbar.setTitle("");
//        mTv_index=(TextView)mToolbar.findViewById(R.id.toolbar_index);
//        mTv_index.setText("我的");
        initView();
        //mToolbar.setTitle("loushi");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, null);


        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    public void initView(){


        initToolBar();
        initTablayout();
    }

    private void initTablayout() {
        mViewPager = (ViewPager)getView().findViewById(R.id.main_vp_container);
        list_fragment = new ArrayList<>();
        categoryFragment=new CategoryFragment();
        list_fragment.add(categoryFragment);
        categoryFragment=new CategoryFragment();
        list_fragment.add(categoryFragment);
        categoryFragment=new CategoryFragment();
        list_fragment.add(categoryFragment);
        list_title = new ArrayList<>();
        list_title.add("场景");
        list_title.add("指南");
        list_title.add("单品");
        mtoorbar_tab.setTabMode(TabLayout.MODE_FIXED);
        mtoorbar_tab.addTab(mtoorbar_tab.newTab().setText(list_title.get(0)));
        mtoorbar_tab.addTab(mtoorbar_tab.newTab().setText(list_title.get(1)));
        mtoorbar_tab.addTab(mtoorbar_tab.newTab().setText(list_title.get(2)));
        fAdapter = new ViewPagerAdapter(getChildFragmentManager(),list_fragment,list_title);
        mViewPager.setAdapter(fAdapter);
        mtoorbar_tab.setupWithViewPager(mViewPager);
        AppBarLayout app_bar_layout = (AppBarLayout)getView(). findViewById(R.id.app_bar_layout);
        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("vertical", Integer.toString(verticalOffset));
                if (verticalOffset <= -732) {
                    //mToolbar.setLogo(R.mipmap.ic_launcher);
                } else {

                }
            }
        });
    }

    public void initToolBar(){
        mToolbar=(Toolbar)getView().findViewById(R.id.program_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        mtoorbar_tab=(TabLayout)getView().findViewById(R.id.toolbar_tab);
    }

}
