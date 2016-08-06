package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.UpdateVersionJson;
import com.lzy.okhttputils.OkHttpUtils;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/4.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private Toolbar program_toolbar;
    private ImageView back;
    private LinearLayout ll_update;
    private TextView updateversion;
    private LinearLayout ll_clear_cache;
    private LinearLayout ll_feedback;
    private LinearLayout ll_about_us;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        program_toolbar = (Toolbar) findViewById(R.id.program_toolbar);
        back = (ImageView) program_toolbar.findViewById(R.id.back);
        ll_update = (LinearLayout) findViewById(R.id.ll_update);
        updateversion = (TextView) findViewById(R.id.updateversion);
        ll_clear_cache = (LinearLayout) findViewById(R.id.ll_clear_cache);
        ll_feedback = (LinearLayout) findViewById(R.id.ll_feedback);
        ll_about_us = (LinearLayout) findViewById(R.id.ll_about_us);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.back:
                 finish();
                 break;
             case R.id.ll_update:
                 break;


         }
    }
    private void CheckUpdate(){
        OkHttpUtils.post(BaseActivity.url_update).execute(new JsonCallback<UpdateVersionJson>(UpdateVersionJson.class) {
            @Override
            public void onResponse(boolean b, UpdateVersionJson updateVersionJson, Request request, Response response) {

            }
        });
    }
}
