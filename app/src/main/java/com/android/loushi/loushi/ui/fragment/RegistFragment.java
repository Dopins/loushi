package com.android.loushi.loushi.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.DialogCallback;
import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.event.ReceiveSmsEvent;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.LoginFirstActivity;
import com.android.loushi.loushi.ui.activity.MainActivity;
import com.android.loushi.loushi.ui.activity.PersonalInformationActivity;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.UnderLineEditText;
import com.android.loushi.loushi.util.UrlConstant;
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

/**
 * Created by Administrator on 2016/7/19.
 */
public class RegistFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";
    public static final String TAG = "RegistFragment";

    private TimeCount checking_time;

    // Content View Elements

    private View view;
    private TextView text_phone;
    private EditText regist_edit_phone;
    private TextView text_keyword;
    private EditText regist_edit_password;
    private TextView text_cheakword;
    private EditText regist_edit_checkword;
    private Button btn_getcheckword;
    private Button btn_finish;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

        initEvent();
        initSDK();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_regist, container, false);
            bindViews();
            initView();

        }//EventBus.getDefault().register(this);
        return view;
    }

    private void initEvent() {
        regist_edit_phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }
        });
        regist_edit_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }
        });
        regist_edit_checkword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //提交验证码
                submitVerificationCode("86", regist_edit_phone.getText().toString(), regist_edit_checkword.getText().toString());
                //在最下面的eventbus回调中执行注册登陆操作
            }
        });


        btn_getcheckword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMobileNO(regist_edit_phone.getText().toString()))
                    Toast.makeText(getContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                else {
                    if (regist_edit_password.getText().toString().length() < 6) {
                        Toast.makeText(getContext(), "密码不能少于6位", Toast.LENGTH_SHORT).show();
                    } else
                        //获取验证码
                    {
                        initTimeCount();
                        getVerificationCode("86", regist_edit_phone.getText().toString());
                    }
                }
            }
        });
    }

    private void initView() {
    }
    private void initTimeCount(){
        checking_time = new TimeCount(60000, 1000);
        checking_time.start();
    }

    private void bindViews() {

        text_phone = (TextView) view.findViewById(R.id.text_phone);
        regist_edit_phone = (EditText) view.findViewById(R.id.regist_edit_phone);
        text_keyword = (TextView) view.findViewById(R.id.text_keyword);
        regist_edit_password = (EditText) view.findViewById(R.id.regist_edit_password);
        text_cheakword = (TextView) view.findViewById(R.id.text_cheakword);
        regist_edit_checkword = (EditText) view.findViewById(R.id.regist_edit_checkword);
        btn_getcheckword = (Button) view.findViewById(R.id.btn_getcheckword);
        btn_finish = (Button) view.findViewById(R.id.btn_finish);

    }


    public static RegistFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(BUNDLE_TITLE, title);
        RegistFragment fragment = new RegistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initSDK() {
        // 初始化短信SDK
        SMSSDK.initSDK(getContext(), "15bf245471948", "584e01d3ab9afb7bcb9e1d0120037cdf");
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    EventBus.getDefault().post(
                            new MainEvent(1));
                    //验证码验证成功
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    EventBus.getDefault().post(
                            new MainEvent(2));

                    //获取验证码成功
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    EventBus.getDefault().post(
                            new MainEvent(3));
                    //返回支持发送验证码的国家列表
                }
            } else {
                EventBus.getDefault().post(
                        new MainEvent(4));
                //Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                ((Throwable) data).printStackTrace();
                Log.e("regist", Log.getStackTraceString((Throwable) data));
            }
            //Looper.loop();
        }
    };

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //SMSSDK.unregisterEventHandler(eh);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MainEvent event) {
        if (event.getMsg() == 1) {
            Log.e("event", "1");
            //Toast.makeText(getContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
            //生成Token
            String token = generateToken(regist_edit_phone.getText().toString() + regist_edit_checkword.getText().toString());
            //发送注册请求
            OkHttpUtils.post(BaseActivity.url + "user/userRegisterAndroid").
                    params("mobile_phone", regist_edit_phone.getText().toString()).
                    params("password", regist_edit_password.getText().toString()).
                    params("verify_code", regist_edit_checkword.getText().toString()).
                    params("token", token).execute(new DialogCallback<ResponseJson>((AppCompatActivity) getActivity(), ResponseJson.class) {
                @Override
                public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
                    if (responseJson.getState()) {
                        //执行登陆操作，存下user_id
                        //并跳到完善信息页面
                        //完善信息后发送event通知转换界面
                        Log.e(TAG, regist_edit_phone.getText().toString());
                        Log.e(TAG, regist_edit_password.getText().toString());
                        OkHttpUtils.post(UrlConstant.USERLOGINURL)
                                .params(KeyConstant.MOBILE_PHONE, regist_edit_phone.getText().toString())
                                .params(KeyConstant.PASSWORD, regist_edit_password.getText().toString())
                                .params(KeyConstant.ISTHIRD, "false")
                                .execute(new DialogCallback<UserLoginJson>((AppCompatActivity) getActivity(), UserLoginJson.class) {
                                    @Override
                                    public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                                        Log.e(TAG, request.toString());
                                        Log.e(TAG, response.toString());
                                        if (userLoginJson.getState()) {
                                            Log.e(TAG, "注册-登录成功！");
                                            CurrentAccount.storeAccountInfo(userLoginJson.getBody() + "", regist_edit_phone.getText().toString(), regist_edit_password.getText().toString(), false, "0");
                                            transferMyFragmentToPersonalInformationActivity();


                                        } else {
                                            Log.e(TAG, "登录失败！");
                                        }
                                    }
                                });


                    }
                }
            });

        }
        if (event.getMsg() == 2) {
            Toast.makeText(getContext(), "获取验证码成功", Toast.LENGTH_SHORT).show();
        }
        if (event.getMsg() == 4) {
            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
        }

    }

    public void transferMyFragmentToPersonalInformationActivity() {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        Intent intent = new Intent(getContext(), PersonalInformationActivity.class);
        getContext().startActivity(intent);

        transferMyFragmentToPersonalFragment();

    }

    public void transferMyFragmentToPersonalFragment() {

        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        if (getActivity() instanceof MainActivity)
            EventBus.getDefault().post(new MyfragmentEvent("Transfer MyFragment to PersonalFragment!"));
        if (getActivity() instanceof LoginFirstActivity) {
            EventBus.getDefault().post(new MyfragmentEvent("Transfer MyFragment to PersonalFragment!"));
            EventBus.getDefault().post(new MyfragmentEvent("Finish LoginFirstActivity"));
        }
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            btn_getcheckword.setText("重新验证");
            btn_getcheckword.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            btn_getcheckword.setClickable(false);
            btn_getcheckword.setText(millisUntilFinished / 1000 + "秒");
        }


    }
}