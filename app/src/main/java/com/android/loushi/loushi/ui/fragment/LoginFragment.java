package com.android.loushi.loushi.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.callback.UserCollecCallback;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.activity.ForgetPasswordActivity;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.UnderLineEditText;
import com.lzy.okhttputils.OkHttpUtils;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/19.
 */
public class LoginFragment extends Fragment {
    public static final String TAG = "LoginFragment";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // Content View Elements

    private View view;
    private UnderLineEditText login_edit_phone;
    private UnderLineEditText login_edit_password;
    private Button btn_login;
    private ImageButton btn_xinlang;
    private ImageButton btn_weixin;
    private ImageButton btn_qq;
    private Button btn_forgetpassword;

    // End Of Content View Elements


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logJudge();
    }

    private void logJudge() {
        sharedPreferences = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Boolean log = sharedPreferences.getBoolean("LoginOrNot",false);
        if (log){
            String phone = sharedPreferences.getString("phone","10086");
            String password = sharedPreferences.getString("password","10086");
            OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
                    .params("mobile_phone",phone)
                    .params("password",password)
                    .params("isThird", "false")
                    .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                        @Override
                        public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request,Response response) {
                            transferMyFragmentToPersonalFragment();
                        }
                    });
        }else {
            Log.e(TAG, " Have not login !");
        }
    }

    public void transferMyFragmentToPersonalFragment() {
        EventBus.getDefault().post(new MyfragmentEvent("Transfer MyFragment to PersonalFragment!"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_login, container, false);
            bindViews();
            initView();
            initEvent();
        }
        return view;
    }

    private void initEvent() {

        login_edit_phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }
        });

        login_edit_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }

        });

        login_edit_phone.setText("13750065622");
        login_edit_password.setText("mtf071330");

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                Log.e(TAG,"login_edit_phone.length() : "+login_edit_phone.length());
                Log.e(TAG,"login_edit_password.length() : "+login_edit_password.length());
                if(login_edit_phone.length() != 11||login_edit_password.length() == 0){
                    Log.e(TAG, "请输入有效的电话号码和密码 !");
                }else {
                    Log.e(TAG,login_edit_phone.getText().toString());
                    Log.e(TAG,login_edit_password.getText().toString());
                    OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
                            .params("mobile_phone",login_edit_phone.getText().toString())
                            .params("password",login_edit_password.getText().toString())
                            .params("isThird", "false")
                            .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                                @Override
                                public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request,Response response) {
                                    Log.e(TAG, request.toString());
                                    Log.e(TAG,response.toString());
                                    if(userLoginJson.getState()){
                                        Log.e(TAG, "登录成功！");
                                        getUserInfo();
                                        editor.putString("phone",login_edit_phone.getText().toString());
                                        editor.putString("password",login_edit_password.getText().toString());
                                        editor.commit();


                                        InputMethodManager imm = (InputMethodManager) view.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
                                        imm.hideSoftInputFromWindow( view.getApplicationWindowToken( ) , 0 );
                                        transferMyFragmentToPersonalFragment();
                                    }else {
                                        Log.e(TAG, "登录失败！");
                                    }
                                }
                            });

                }
            }
        });

        btn_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ForgetPasswordActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

    private void getUserInfo() {
        OkHttpUtils.post()
    }

    private void initView() {


        SpannableString ss_keyword = new SpannableString(" 密码");
        AbsoluteSizeSpan ass_keyword = new AbsoluteSizeSpan(12, true);
        ss_keyword.setSpan(ass_keyword, 0, ss_keyword.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login_edit_password.setHint(ss_keyword);

        SpannableString ss_phone = new SpannableString(" 手机");
        AbsoluteSizeSpan ass_phone = new AbsoluteSizeSpan(12, true);
        ss_phone.setSpan(ass_phone, 0, ss_phone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login_edit_phone.setHint(ss_phone);

    }


    private void bindViews() {

        login_edit_phone = (UnderLineEditText) view.findViewById(R.id.login_edit_phone);
        login_edit_password = (UnderLineEditText) view.findViewById(R.id.login_edit_password);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_xinlang = (ImageButton) view.findViewById(R.id.btn_xinlang);
        btn_weixin = (ImageButton) view.findViewById(R.id.btn_weixin);
        btn_qq = (ImageButton) view.findViewById(R.id.btn_qq);
        btn_forgetpassword = (Button) view.findViewById(R.id.btn_forgetpassword);
    }

    public static LoginFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(TAG, title);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
