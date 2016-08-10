package com.android.loushi.loushi.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.ui.fragment.LoginFragment;
import com.android.loushi.loushi.ui.fragment.PersonFragment;
import com.android.loushi.loushi.ui.fragment.RegistFragment;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.ViewPagerIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginFirstActivity extends AppCompatActivity {
    public static final String TAG = "LoginFirstActivity";


    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("登录", "注册");
    private ViewPagerIndicator mIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first);

        initView();
        initDatas();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    public void Gone() {
        findViewById(R.id.image_top).setVisibility(View.GONE);
        return;
    }

    public void Visible() {
        findViewById(R.id.image_top).setVisibility(View.VISIBLE);
        return;
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

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_vp);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
        Visible();
    }

    @Subscribe
    public void onEventMainThread(MyfragmentEvent event) {
        Log.e(TAG, event.getmMsg());
        if (event.getmMsg().equals("Finish LoginFirstActivity")) {
            finish();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
