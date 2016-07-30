package com.android.loushi.loushi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class CategoryViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public CategoryViewPagerAdapter(FragmentManager fm,List<String> titleList ,List<Fragment> fragmentList){
        super(fm);
        this.mFragmentList=fragmentList;
        this.mTitleList=titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
