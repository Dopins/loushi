package com.android.loushi.loushi.ui.fragment;

import android.graphics.Color;
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
import com.android.loushi.loushi.adapter.PersonCollectTabAdapter;
import com.android.loushi.loushi.adapter.ViewPagerAdapter;
import com.android.loushi.loushi.util.SlidingTabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */

//个人中心
public class PersonFragment extends BaseFragment {

    private Toolbar mToolbar;
    private TextView mTv_index;
    //private TabLayout mtoorbar_tab;
    private SlidingTabLayout mtoorbar_tab;
                            //定义viewPager
    private PersonCollectTabAdapter personCollectTabAdapter;                               //定义adapter
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_cate;
    private List<String> list_count;
    //private CategoryFragment categoryFragment;
    private CollectGoodFragment collectGoodFragment;
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
        collectGoodFragment=new CollectGoodFragment();
        list_fragment.add(collectGoodFragment);
        collectGoodFragment=new CollectGoodFragment();
        list_fragment.add(collectGoodFragment);
        collectGoodFragment=new CollectGoodFragment();
        list_fragment.add(collectGoodFragment);
        list_cate = new ArrayList<>();
        list_count = new ArrayList<>();
        list_cate.add("场景");
        list_cate.add("指南");
        list_cate.add("单品");
        list_count.add("32");

        list_count.add("32");
        list_count.add("32");

        personCollectTabAdapter = new PersonCollectTabAdapter(getChildFragmentManager(),list_fragment,list_cate,list_count,getContext());
        mViewPager.setAdapter(personCollectTabAdapter);


        mtoorbar_tab.setTabStripWidth(120);


        //mtoorbar_tab.setSelectedIndicatorColors(R.color.colorPrimary);
        mtoorbar_tab.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.rgb(105,184,187);
            }
        });
        mtoorbar_tab.setDistributeEvenly(true);
        mtoorbar_tab.setCustomTabView(R.layout.tab_item_view_collect, R.id.tv_tab_view_count);

        //mtoorbar_tab.setCustomTabView(R.layout.tab_item_view_collect, 0);
        mtoorbar_tab.setViewPager(mViewPager);

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
        mtoorbar_tab=(SlidingTabLayout)getView().findViewById(R.id.toolbar_tab);
    }

}
