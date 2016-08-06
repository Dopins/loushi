package com.android.loushi.loushi.ui.fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.callback.UserCollectCallback;
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.thirdlogin.LoginApi;
import com.android.loushi.loushi.thirdlogin.OnLoginListener;
import com.android.loushi.loushi.thirdlogin.Tool;
import com.android.loushi.loushi.thirdlogin.UserInfo;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.ForgetPasswordActivity;
import com.android.loushi.loushi.ui.activity.MainActivity;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.UnderLineEditText;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;

import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/19.
 */
public class LoginFragment extends Fragment {
    public static final String TAG = "LoginFragment";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    private AlertDialog dialog;

    // Content View Elements

    private View view;
    private UnderLineEditText login_edit_phone;
    private UnderLineEditText login_edit_password;
    private Button btn_login;
    private ImageButton btn_xinlang;
    private ImageButton btn_weixin;
    private ImageButton btn_qq;
    private Button btn_forgetpassword;
    private ArrayList<ImageButton> imgbtn;
    // End Of Content View Elements


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setLoginInfoFromCache();
        initPlatformList();
    }

    private void setLoginInfoFromCache() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone","");
        String password = sharedPreferences.getString("password","");
        login_edit_phone.setText(phone);
        login_edit_password.setText(password);
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

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                dialog = builder.create();
//                dialog.show();
                Log.e(TAG, "login_edit_phone.length() : " + login_edit_phone.length());
                Log.e(TAG, "login_edit_password.length() : " + login_edit_password.length());
                if (login_edit_phone.length() != 11 || login_edit_password.length() == 0) {
                    Log.e(TAG, "请输入有效的电话号码和密码 !");
                } else {
                    Log.e(TAG, login_edit_phone.getText().toString());
                    Log.e(TAG, login_edit_password.getText().toString());
                    OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
                            .params("mobile_phone", login_edit_phone.getText().toString())
                            .params("password", login_edit_password.getText().toString())
                            .params("isThird", "false")
                            .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                                @Override
                                public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                                    Log.e(TAG, request.toString());
                                    Log.e(TAG, response.toString());
                                    if (userLoginJson.getState()) {
                                        Log.e(TAG, "登录成功！");

                                        sharedPreferences = getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
                                        editor = sharedPreferences.edit();
                                        editor.putBoolean("LoginOrNot", true);
                                        editor.putString("phone", login_edit_phone.getText().toString());
                                        editor.putString("password", login_edit_password.getText().toString());
                                        editor.commit();
                                        BaseActivity.user_id=userLoginJson.getBody()+"";
                                        getUserInfo(userLoginJson.getBody());

                                        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                                        transferMyFragmentToPersonalFragment();
                                    } else {
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

    private void getUserInfo(int id) {
        Log.e(TAG, "getUserInfo");
        final String user_id = id + "";
        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userinfo")
                .params("user_id", user_id)
                .execute(new JsonCallback<UserInfoJson>(UserLoginJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserInfoJson userInfoJson, Request request, @Nullable Response response) {
                        Log.e(TAG, "onResponse");
                        Log.e(TAG, request.toString());
                        Log.e(TAG, response.toString());
                        UserInfoJson.BodyBean body = userInfoJson.getBody();
                        sharedPreferences = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("nickname", body.getNickname());
                        editor.putString("mobilePhone", body.getMobilePhone());
                        editor.putString("email", body.getEmail());
                        editor.putString("headImgUrl", body.getHeadImgUrl());
                        editor.putString("schoolName", body.getSchool().getName());
//                        editor.putInt("sex", body.getSex());
                        editor.putInt("messageCount", body.getMessageCount());
                        editor.putInt("userID", body.getUserID());
                        editor.commit();
                        Log.e(TAG, "onResponse: " + body.getMobilePhone());

                    }
                });
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


    private void initPlatformList() {

        ShareSDK.initSDK(getContext());
        //初始化
        Platform[] Platformlist = ShareSDK.getPlatformList();
        //平台列表
        if (Platformlist != null) {
            imgbtn = new ArrayList<ImageButton>(3);
            Log.e("TAG", "创建了img数组");
            btn_xinlang=(ImageButton)getView().findViewById(R.id.btn_xinlang);
            btn_qq =(ImageButton)getView().findViewById(R.id.btn_qq);
            btn_weixin =(ImageButton)getView().findViewById(R.id.btn_weixin);
            Log.e("TAG", "获取到1");
            imgbtn.add(btn_xinlang);
            imgbtn.add(btn_weixin);
            imgbtn.add(btn_qq);
            //总共三个


            int i=0;
            for (Platform platform : Platformlist) {
                if (!Tool.canGetUserInfo(platform)) {
                    i++;
                    continue;

                }

                if (platform instanceof CustomPlatform) {
                    i++;
                    continue;
                }



                imgbtn.get(i).setTag(platform);
                imgbtn.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object tag = v.getTag();
                        if (tag != null) {
                            Platform platform = (Platform) tag;
                            String name = platform.getName();
                            Thirdlogin(name);
                        }
                    }
                });
                i++;
                Log.e("TAG",Integer.toString(i));
            }
        }


    }
    private void Thirdlogin(String platformName) {

        LoginApi api = new LoginApi(this.getActivity());
        //设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        //dialog.dismiss();
        //第三方登陆设置监听
        api.setOnLoginListener(new OnLoginListener() {
            public boolean onLogin(String plate, HashMap<String, Object> res) {

                String plat = plate;
                final Platform platform = ShareSDK.getPlatform(plat);
                //得到第三方登陆的信息
                //userId是第三方登陆的id
                //Icon是头像
                //gender是性别 m代表男f代表女
                //获取第三方的用户名
                Log.e("LoginAPP", platform.getDb().getUserId());
                Log.e("LoginAPP", platform.getDb().getUserIcon());
                Log.e("LoginAPP", platform.getDb().getUserGender());
                Log.e("LoginAPP", platform.getDb().getUserName());

                String account = platform.getDb().getUserId();
                String token = generateToken(account);
                //token是接口需要
                String type;
                //判断类型
                //在这里执行登陆操作  并存储个人信息，判断是否是第一次用这个第三方登陆，
                // 如果不是则不用存储，直接调用服务器原有信息
                //存储个人信息可不用post学校参数
                if (plat.equals("SinaWeibo")) {
                    type = "0";
                    Log.e("LoginAPPId", res.get("id").toString());
                    Log.e("LoginAPPname", res.get("name").toString());
                    Log.e("LoginAPPurl", res.get("profile_image_url").toString());
                } else if (plat.equals("Wechat")) {
                    type = "1";
                } else {
                    type = "2";
                }



                return false;
            }

            @Override
            public boolean onRegister(UserInfo info) {
                //这个是让用户第三方登陆完后注册我们的app
                return true;
            }


        });
        api.login(getContext());
        //dialog.dismiss();

        //Log.e("TAG",Boolean.toString(api.getCanLogin()));

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

}
