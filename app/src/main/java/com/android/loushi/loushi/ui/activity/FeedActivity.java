package com.android.loushi.loushi.ui.activity;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.util.UnderLineEditText;
import com.lzy.okhttputils.OkHttpUtils;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/31.
 */
public class FeedActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button btn_finsh;
    private EditText et_question;
    private EditText et_phone;
    private ImageView back;

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
        btn_finsh=(Button)findViewById(R.id.btn_finish);
        back=(ImageView)toolbar.findViewById(R.id.back);
        back.setOnClickListener(this);
        btn_finsh.setOnClickListener(this);

    }
    //手机号码正则匹配
    public boolean isMobileNO(String mobiles) {
		/*
		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finish:
                if(!isMobileNO(et_phone.getText().toString()))
                    Toast.makeText(FeedActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                else
                SubmitFeed();
                break;
            case R.id.back :
                finish();
                break;


        }
    }

    private void SubmitFeed(){
         OkHttpUtils.post(String.format("%s%s",BaseActivity.url,"user/userFeedback"))
                 .params("user_id",BaseActivity.user_id).
                 params("content",et_question.getText().toString())
                 .params("contact_way",et_phone.getText().toString()).execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
             @Override
             public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
                 if(responseJson.getState()){
                     Toast.makeText(FeedActivity.this,"反馈成功",Toast.LENGTH_SHORT).show();
                     finish();
                 }
                 else{
                     Toast.makeText(FeedActivity.this,responseJson.getReturn_info(),Toast.LENGTH_SHORT).show();
                 }
             }
         });
    }
}
