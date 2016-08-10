package com.android.loushi.loushi.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/7/18.
 */
public  class  BaseFragment extends Fragment {
    public static String url = "";
    protected LayoutInflater inflater;
    private View contentView;
    private Context context;
    private ViewGroup container;


    public BaseFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this.getActivity().getApplicationContext();
    }

    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.onCreateView(savedInstanceState);
        return this.contentView == null?super.onCreateView(inflater, container, savedInstanceState):this.contentView;
    }

    protected void onCreateView(Bundle savedInstanceState) {
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.contentView = null;
        this.container = null;
        this.inflater = null;
    }

    public Context getApplicationContext() {
        return this.context;
    }

    public void setContentView(int layoutResID) {
        this.setContentView((ViewGroup)this.inflater.inflate(layoutResID, this.container, false));
    }

    public void setContentView(View view) {
        this.contentView = view;
    }

    public View getContentView() {
        return this.contentView;
    }

    public View findViewById(int id) {
        return this.contentView != null?this.contentView.findViewById(id):null;
    }

    public void onDetach() {
        super.onDetach();

        try {
            Field e = Fragment.class.getDeclaredField("mChildFragmentManager");
            e.setAccessible(true);
            e.set(this, (Object)null);
        } catch (NoSuchFieldException var2) {
            throw new RuntimeException(var2);
        } catch (IllegalAccessException var3) {
            throw new RuntimeException(var3);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getContext());
    }

}

