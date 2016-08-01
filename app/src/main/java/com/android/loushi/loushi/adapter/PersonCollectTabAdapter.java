package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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
    public PersonCollectTabAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_count,Context context) {
        super(fm);
        this.list_fragment = list_fragment;

        this.list_count=list_count;
        this.context = context;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
    @Override
    public Fragment getItem(int position) {
//        CollectGoodFragment collectGoodFragment = new CollectGoodFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(CollectGoodFragment.TYPE,position+"");
//        collectGoodFragment.setArguments(bundle);
//        return collectGoodFragment;

        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {

        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollections")
                // 请求方式和请求url
                .tag(this).params("type", position+"").params("user_id", BaseActivity.user_id)
                .params("skip", "0").params("take", "1")

                // 请求的 tag, 主要用于取消对应的请求
                // 缓存模式，详细请看缓存介绍
                .execute(new JsonCallback<UserCollectionsJson>(UserCollectionsJson.class) {
                             @Override
                             public void onResponse(boolean b, UserCollectionsJson userCollectionsJson, Request request, Response response) {
                                 count=userCollectionsJson.getReturn_info();

                             }

                         }

                );
        return count;
        //return list_count.get(position);
    }
}
