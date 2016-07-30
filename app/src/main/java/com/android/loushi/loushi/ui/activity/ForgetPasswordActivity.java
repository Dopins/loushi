package com.android.loushi.loushi.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.util.UnderLineEditText;

public class ForgetPasswordActivity extends AppCompatActivity {


    // Content View Elements

    private Toolbar toolbar;
    private ImageButton btn_return;
    private TextView text_phone;
    private UnderLineEditText edit_phone;
    private TextView text_checkword;
    private UnderLineEditText regist_edit_checkword;
    private Button btn_getcheckword;
    private TextView text_new_keyword;
    private UnderLineEditText edit_newpassword;
    private TextView text_newkeyword_confirm;
    private UnderLineEditText edit_newkeyword_confirm;
    private Button btn_finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        bindViews();

    }


    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.Toolbar);
        btn_return = (ImageButton) findViewById(R.id.btn_return);
        text_phone = (TextView) findViewById(R.id.text_phone);
        edit_phone = (UnderLineEditText) findViewById(R.id.edit_phone);
        text_checkword = (TextView) findViewById(R.id.text_checkword);
        regist_edit_checkword = (UnderLineEditText) findViewById(R.id.regist_edit_checkword);
        btn_getcheckword = (Button) findViewById(R.id.btn_getcheckword);
        text_new_keyword = (TextView) findViewById(R.id.text_new_keyword);
        edit_newpassword = (UnderLineEditText) findViewById(R.id.edit_newpassword);
        text_newkeyword_confirm = (TextView) findViewById(R.id.text_newkeyword_confirm);
        edit_newkeyword_confirm = (UnderLineEditText) findViewById(R.id.edit_newkeyword_confirm);
        btn_finish = (Button) findViewById(R.id.btn_finish);
    }
}
