package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.ViewPagerAdapter;
import com.android.loushi.loushi.ui.activity.SearchActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */

//场景
public class SceneFragment extends BaseFragment {

    private static final String TAG="SceneFragment";

    private View rootView;
    private Toolbar mToolbar;
    private TextView mTv_index;

    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;                            //定义TabLayout
    private ViewPager viewPager;                             //定义viewPager
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private RecommendFragment recommendFragment;
    private SceneListInterestFragment sceneListInterestFragment;
    private SceneListStyleFragment sceneListStyleFragment;
    private SceneListHabitFragment sceneListHabitFragment;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setContentView(findViewById(R.layout.fragment_scene));
        mToolbar = (Toolbar) getView().findViewById(R.id.program_toolbar);
        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mTv_index = (TextView) mToolbar.findViewById(R.id.toolbar_index);
        mTv_index.setText("场景");

        LinearLayout btn_search=(LinearLayout)getView().findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        initViewPager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_scene, null);
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
        recommendFragment = new RecommendFragment();
        sceneListInterestFragment = new SceneListInterestFragment();
        sceneListStyleFragment = new SceneListStyleFragment();
        sceneListHabitFragment = new SceneListHabitFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(recommendFragment);
        list_fragment.add(sceneListInterestFragment);
        list_fragment.add(sceneListStyleFragment);
        list_fragment.add(sceneListHabitFragment);


        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("推荐");
        list_title.add("兴趣");
        list_title.add("风格");
        list_title.add("习惯");

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(3)));

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        viewPager.setAdapter(viewPagerAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
        //tab_FindFragment_title.set
    }

    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

}
