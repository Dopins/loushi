package com.android.loushi.loushi.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.DialogCallback;
import com.android.loushi.loushi.event.ReceiveSmsEvent;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.util.UnderLineEditText;
import com.lzy.okhttputils.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Request;
import okhttp3.Response;

import static cn.smssdk.SMSSDK.getVerificationCode;
import static cn.smssdk.SMSSDK.submitVerificationCode;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {


    // Content View Elements

    private Toolbar toolbar;
    private ImageButton btn_return;
    private TextView text_phone;
    private EditText edit_phone;
    private TextView text_checkword;
    private EditText regist_edit_checkword;
    private Button btn_getcheckword;
    private TextView text_new_keyword;
    private EditText edit_newpassword;
    private TextView text_newkeyword_confirm;
    private EditText edit_newkeyword_confirm;
    private Button btn_finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        bindViews();
        EventBus.getDefault().register(this);
        initSDK();
    }


    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.Toolbar);
        btn_return = (ImageButton) findViewById(R.id.btn_return);
        text_phone = (TextView) findViewById(R.id.text_phone);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        text_checkword = (TextView) findViewById(R.id.text_checkword);
        regist_edit_checkword = (EditText) findViewById(R.id.regist_edit_checkword);
        btn_getcheckword = (Button) findViewById(R.id.btn_getcheckword);
        text_new_keyword = (TextView) findViewById(R.id.text_new_keyword);
        edit_newpassword = (EditText) findViewById(R.id.edit_newpassword);
        text_newkeyword_confirm = (TextView) findViewById(R.id.text_newkeyword_confirm);
        edit_newkeyword_confirm = (EditText) findViewById(R.id.edit_newkeyword_confirm);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(this);
        btn_getcheckword.setOnClickListener(this);
    }
    private void initSDK() {
        // 初始化短信SDK
        SMSSDK.initSDK(ForgetPasswordActivity.this, "15bf245471948", "584e01d3ab9afb7bcb9e1d0120037cdf");
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }
    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    EventBus.getDefault().post(
                            new ReceiveSmsEvent(1));
                    //验证码验证成功
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    EventBus.getDefault().post(
                            new ReceiveSmsEvent(2));

                    //获取验证码成功
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    EventBus.getDefault().post(
                            new ReceiveSmsEvent(3));
                    //返回支持发送验证码的国家列表
                }
            }else{
                EventBus.getDefault().post(
                        new ReceiveSmsEvent(4));
                //Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                ((Throwable)data).printStackTrace();
                Log.e("regist", Log.getStackTraceString((Throwable) data));
            }
            //Looper.loop();
        }
    };

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
    private String generateToken(String account) {
        char[] charTmp = account.toCharArray();
        int[] intTmp = new int[account.length()];
        for (int i = 0; i < intTmp.length; i++) {
            intTmp[i] = charTmp[i] - i % 10;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < intTmp.length; i++) {
            stringBuilder.append(Integer.toHexString(intTmp[i]));
        }
        return stringBuilder.toString().toUpperCase();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReceiveSmsEvent event) {
        if(event.getMsg()==1){
            Log.e("event", "1");
            Toast.makeText(ForgetPasswordActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
            //生成Token
            String token = generateToken(edit_phone.getText().toString()+regist_edit_checkword.getText().toString());
            //发送请求
            OkHttpUtils.post(String.format("%s%s",BaseActivity.url,"user/userForgetPassword")).params("mobile_phone", edit_phone.getText().toString())
                    .params("newpassword", edit_newpassword.getText().toString())
                    .params("verify_code", regist_edit_checkword.getText().toString())
                    .params("token", token).execute(new DialogCallback<ResponseJson>(this, ResponseJson.class) {
                @Override
                public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
                    if(responseJson.getState()){
                        Toast.makeText(ForgetPasswordActivity.this,"修改密码成功",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(ForgetPasswordActivity.this,responseJson.getReturn_info(),Toast.LENGTH_SHORT).show();
                }

            });


        }
        if(event.getMsg()==2){
            Toast.makeText(ForgetPasswordActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
        }
        if(event.getMsg()==4){
            Toast.makeText(ForgetPasswordActivity.this, "失败", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        SMSSDK.unregisterEventHandler(eh);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_getcheckword:
                if(!isMobileNO(edit_phone.getText().toString()))
                    Toast.makeText(ForgetPasswordActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                else
                    getVerificationCode("86", edit_phone.getText().toString());
                break;
            case R.id.btn_finish:
                if(edit_newpassword.getText().toString().length()<6)
                    Toast.makeText(ForgetPasswordActivity.this,"密码不能小于6位",Toast.LENGTH_SHORT).show();
                else if(!edit_newpassword.getText().toString().equals(edit_newkeyword_confirm.getText().toString()))
                    Toast.makeText(ForgetPasswordActivity.this,"两次密码输入不同",Toast.LENGTH_SHORT).show();
                else
                submitVerificationCode("86", edit_phone.getText().toString(), regist_edit_checkword.getText().toString());
                break;
        }
    }
}
