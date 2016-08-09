package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.UserCollectionsJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.fragment.CollectGoodFragment;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/21.
 */
public class PersonCollectTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;                         //fragment列表


    List<String> list_count;
    private Context context;
    private String count="0";
    private FragmentManager fm;
    public PersonCollectTabAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_count,Context context) {
        super(fm);
        this.list_fragment = list_fragment;

        this.list_count=list_count;
        this.context = context;
        this.fm=fm;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
    @Override
    public Fragment getItem(int position) {
//        CollectGoodFragment collectGoodFragment = new CollectGoodFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(CollectGoodFragment.TYPE,position+"");
//        collectGoodFragment.setArguments(bundle);
//        return collectGoodFragment;

        return list_fragment.get(position%list_fragment.size());
    }
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
////得到缓存的fragment
//        Fragment fragment = (Fragment) super.instantiateItem(container,
//                position);
////得到tag，这点很重要
//        String fragmentTag = fragment.getTag();
//
//
//        if (1>0) {
////如果这个fragment需要更新
//
//            FragmentTransaction ft = fm.beginTransaction();
////移除旧的fragment
//            ft.remove(fragment);
////换成新的fragment
//            fragment = list_fragment.get(position % list_fragment.size());
////添加新fragment时必须用前面获得的tag，这点很重要
//            ft.add(container.getId(), fragment, fragmentTag);
//            ft.attach(fragment);
//            ft.commit();
//
////复位更新标志
//            //fragmentsUpdateFlag[position % fragmentsUpdateFlag.length] = false;
//        }
//
//
//        return fragment;
//    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {


        return list_count.get(position % list_count.size());

        //return list_count.get(position);
    }
    public void setListCount(List<String> list_count){
        this.list_count=list_count;
    }





}
