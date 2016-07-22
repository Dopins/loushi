package com.android.loushi.loushi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class PersonCollectTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;                         //fragment列表
    private List<String> list_cate;                              //tab名的列表
    List<String> list_count;
    public PersonCollectTabAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_cate,List<String> list_count) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_cate = list_cate;
        this.list_count=list_count;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return 0;
    }
}
