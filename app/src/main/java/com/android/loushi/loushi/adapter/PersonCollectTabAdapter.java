package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class PersonCollectTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;                         //fragment列表
    private List<String> list_cate;                              //tab名的列表
    List<String> list_count;
    private Context context;
    public PersonCollectTabAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_cate,List<String> list_count,Context context) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_cate = list_cate;
        this.list_count=list_count;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }
    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.tab_item_view_collect, null);
        TextView tv_collect_count = (TextView) v.findViewById(R.id.tv_tab_view_count);
        TextView tv_collect_cate=(TextView) v.findViewById(R.id.tv_tab_view_cate);
        tv_collect_count.setText(list_count.get(position));
        tv_collect_cate.setText(list_cate.get(position));
        //img.setImageResource(imageResId[position]);
        return v;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return list_count.get(position);
    }
}
