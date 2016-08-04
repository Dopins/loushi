package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.util.UnderLineEditText;

/**
 * Created by Administrator on 2016/7/31.
 */
public class FeedActivity extends BaseActivity {
    private Toolbar toolbar;
    private Button btn_finsh;
    private EditText et_question;
    private EditText et_phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    private void initView() {
        toolbar = (Toolbar)findViewById(R.id.program_toolbar);
        et_question=(EditText)findViewById(R.id.et_question);
        et_phone=(EditText)findViewById(R.id.et_phone);

    }
}
