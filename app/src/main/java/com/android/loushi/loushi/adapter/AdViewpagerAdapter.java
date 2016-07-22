package com.android.loushi.loushi.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by dopin on 2016/7/18.
 */
public class AdViewpagerAdapter extends PagerAdapter{

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private ArrayList<ImageView> viewlist;
    private OnItemClickListener itemClickListener;
    public AdViewpagerAdapter(ArrayList<ImageView> viewlist) {
        this.viewlist = viewlist;

    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getCount() {
        //设置成最大，使用户看不到边界
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        //Warning：不要在这里调用removeView
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //对ViewPager页号求模取出View列表中要显示的项

        position %= viewlist.size();
        if (position<0){
            position = viewlist.size()+position;
        }
        final int temp_position=position;

        ImageView view = viewlist.get(position);

        view.setOnClickListener(new View.OnClickListener() {    //监听点击事件
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v,temp_position);
                }
            }
        });

        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp =view.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(view);
        }
        container.addView(view);
        //add listeners here if necessary
        return view;
    }

}