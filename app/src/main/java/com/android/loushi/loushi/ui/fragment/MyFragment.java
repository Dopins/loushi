package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.util.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MyFragment extends BaseFragment {
    private Toolbar mToolbar;
    private TextView mTv_index;

    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("登录", "注册");
    private ViewPagerIndicator mIndicator;
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

        initView();
        initDatas();
        //设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);

        return view;
    }

    private void initDatas() {

        for (String data : mDatas) {
            switch (data) {
                case "登录":
                    LoginFragment lgFragment = LoginFragment.newInstance(data);
                    mTabContents.add(lgFragment);
                    break;
                case "注册":
                    RegistFragment rgFragment = RegistFragment.newInstance(data);
                    mTabContents.add(rgFragment);
                    break;
            }

        }

        mAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_vp);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
    }
}
