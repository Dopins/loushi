package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.loushi.loushi.R;

/**
 * Created by Administrator on 2016/7/17.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String url="";
    public static String user_id="32";
    public Toolbar mToolbar;
    protected abstract int getLayoutId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }


}
