package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.AdViewpagerAdapter;
import com.android.loushi.loushi.adapter.ViewPagerAdapter;
import com.android.loushi.loushi.ui.fragment.CollectGoodFragment;
import com.android.loushi.loushi.ui.fragment.SceneDetailDesignFragment;
import com.android.loushi.loushi.ui.fragment.SceneDetailGoodFragment;
import com.android.loushi.loushi.viewpager.CarouselViewPager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneDetailActivity extends  BaseActivity {
    private CarouselViewPager carouselViewPager;
    private SceneDetailGoodFragment sceneDetailGoodFragment;
    private SceneDetailDesignFragment sceneDetailDesignFragment;
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private TabLayout tabLayout;
    private Toolbar mToolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    public static  String SCENE_ID="SCENE_ID";


    public  String scene_id="1";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scene_detail;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_detail);
        scene_id = getIntent().getStringExtra(SCENE_ID);

        initView();
        initTablayout();
    }

    private void initView() {
        carouselViewPager =(CarouselViewPager)findViewById(R.id.ad_viewPager);
        viewPager = (ViewPager)findViewById(R.id.main_vp_container);
        ImageView view1 = (ImageView) LayoutInflater.from(
                getApplicationContext()).inflate(R.layout.ad_image, null);
        ImageView view2 = (ImageView) LayoutInflater.from(
                getApplicationContext()).inflate(R.layout.ad_image, null);
        ImageView view3 = (ImageView) LayoutInflater.from(
                getApplicationContext()).inflate(R.layout.ad_image, null);
        ImageView view4 = (ImageView) LayoutInflater.from(
                getApplicationContext()).inflate(R.layout.ad_image, null);
        ArrayList<ImageView> views = new ArrayList<ImageView>();
        //ImageView img = new ImageView(getApplicationContext());

        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);


        AdViewpagerAdapter adViewpagerAdapter = new AdViewpagerAdapter(views);

        carouselViewPager.setAdapter(adViewpagerAdapter);
        Picasso.with(getApplicationContext()).load("http://119.29.187.58:8080/loushi/image/scene/guangwai001.jpeg").into(view1);
        Picasso.with(getApplicationContext()).load("http://119.29.187.58:8080/loushi/image/scene/guangwai001.jpeg").into(view2);
        Picasso.with(getApplicationContext()).load("http://119.29.187.58:8080/loushi/image/scene/guangwai001.jpeg").into(view3);
        Picasso.with(getApplicationContext()).load("http://119.29.187.58:8080/loushi/image/scene/guangwai001.jpeg").into(view4);
    }
    private void initTablayout(){
        tabLayout=(TabLayout)findViewById(R.id.toolbar_tab);
        viewPager = (ViewPager)findViewById(R.id.main_vp_container);
        sceneDetailGoodFragment = new SceneDetailGoodFragment();
        Bundle bundle = new Bundle();
        bundle.putString("SCENE_ID",scene_id);
        sceneDetailGoodFragment.setArguments(bundle);
        sceneDetailDesignFragment = new SceneDetailDesignFragment();
        sceneDetailDesignFragment.setArguments(bundle);
        list_fragment = new ArrayList<>();
        list_title = new ArrayList<>();
        list_fragment.add(sceneDetailDesignFragment);

        list_fragment.add(sceneDetailGoodFragment);
        //list_fragment.add(collectGoodFragment);

        list_title.add("设计");
        list_title.add("购买");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText("设计"));
        tabLayout.addTab(tabLayout.newTab().setText("购买"));
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list_fragment, list_title);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
