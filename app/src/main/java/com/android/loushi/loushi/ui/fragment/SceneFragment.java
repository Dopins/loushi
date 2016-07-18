package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.ui.activity.BaseActivity;

/**
 * Created by Administrator on 2016/7/18.
 */

//场景
public class SceneFragment extends BaseFragment {
    private View rootView;
    private Toolbar mToolbar;
    private TextView mTv_index;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setContentView(findViewById(R.layout.fragment_scene));
        mToolbar=(Toolbar)getView().findViewById(R.id.program_toolbar);
        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mTv_index=(TextView)mToolbar.findViewById(R.id.toolbar_index);
        mTv_index.setText("场景");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_scene, null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {

            parent.removeView(rootView);
        }
        return rootView;


    }


    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);


    }
}
