package com.android.loushi.loushi.ui.fragment;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.ViewPagerIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MyFragment extends BaseFragment {
    public static final String TAG = "MyFragment";
    private Toolbar mToolbar;
    private TextView mTv_index;

    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("登录", "注册");
    private ViewPagerIndicator mIndicator;
    public static View view;



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        Log.e("Test: " + TAG, "onActivityCreated");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        Boolean LoginOrNot = sharedPreferences.getBoolean("LoginOrNot",false);
        if(LoginOrNot) {
            transferToPersonalFragment();
        }else {
            Log.e(TAG, " Have not login !");
        }

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
        if (view == null) {
            Log.e("Test: " + TAG, "onCreateView");
            view = inflater.inflate(R.layout.fragment_my, null);
            initView(view);
            initDatas();
            EventBus.getDefault().register(this);
        }
        Visible();
        return view;
    }

    static public void Gone() {
        view.findViewById(R.id.image_top).setVisibility(View.GONE);
        return;
    }

    static public void Visible() {
        Log.d("Visible", view.toString());
        Log.d("Visible", view.toString());
        view.findViewById(R.id.image_top).setVisibility(View.VISIBLE);
        return;
    }

    public void transferToPersonalFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.content,new PersonFragment())
                .commit();
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

        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);
    }

    private void initView(View view) {
        Log.d("initView", "initView");
        mViewPager = (ViewPager) view.findViewById(R.id.id_vp);
        mIndicator = (ViewPagerIndicator) view.findViewById(R.id.id_indicator);
        Visible();
    }

    @Subscribe
    public void onEventMainThread(MyfragmentEvent event){
        Log.e(TAG,event.getmMsg());
        transferToPersonalFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Test: " + TAG, "onDestroy");
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
