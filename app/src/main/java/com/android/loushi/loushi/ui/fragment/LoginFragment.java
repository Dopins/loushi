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
import android.text.TextUtils;
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
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.thirdlogin.LoginApi;
import com.android.loushi.loushi.thirdlogin.OnLoginListener;
import com.android.loushi.loushi.thirdlogin.Tool;
import com.android.loushi.loushi.thirdlogin.UserInfo;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.ForgetPasswordActivity;
import com.android.loushi.loushi.ui.activity.LoginFirstActivity;
import com.android.loushi.loushi.ui.activity.MainActivity;
import com.android.loushi.loushi.ui.activity.PersonalInformationActivity;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.UnderLineEditText;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.umeng.analytics.MobclickAgent;

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
    private EditText login_edit_phone;
    private EditText login_edit_password;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy ");
    }

    private void setLoginInfoFromCache() {

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

    public void transferMyFragmentToPersonalInformationActivity() {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        Intent intent = new Intent(getActivity(), PersonalInformationActivity.class);
        getActivity().startActivity(intent);

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
                view.requestFocus();
                return false;
            }
        });

        login_edit_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                view.requestFocus();
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
                if (!isMobileNO(login_edit_phone.getText().toString()) || login_edit_password.length() == 0) {
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

                                        BaseActivity.user_id = userLoginJson.getBody() + ""; //冗余
                                        Log.e(TAG, userLoginJson.getBody() + "");
                                        CurrentAccount.setLoginOrNot(true);
                                        CurrentAccount.storeAccountInfo(userLoginJson.getBody() + "", login_edit_phone.getText().toString(), login_edit_password.getText().toString());
                                        getUserInfo(userLoginJson.getBody());

                                        MobclickAgent.onProfileSignIn(login_edit_phone.getText().toString());

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
        String user_id = id + "";
        Log.e("BIG ", user_id);
        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userinfo.action")
                .params("user_id", user_id)
                .execute(new JsonCallback<UserInfoJson>(UserInfoJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserInfoJson userInfoJson, Request request, @Nullable Response response) {
                        if (userInfoJson.isState()) {
                            Log.e(TAG, "成功获取用户信息");
                            Log.e(TAG, request.toString());
                            Log.e(TAG, response.toString());

                            //CurrentAccount.storeDatas(userInfoJson);
                            Log.e(TAG, CurrentAccount.getHeadImgUrl());
                            Log.e(TAG, CurrentAccount.getNickname());
                            Log.e(TAG, CurrentAccount.getPassword());
                            Log.e(TAG, CurrentAccount.getSchoolName());
                            Log.e(TAG, CurrentAccount.getEmail());

                            CurrentAccount.storeDatas(userInfoJson);
                            transferMyFragmentToPersonalFragment();

                        }
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

        login_edit_phone = (EditText) view.findViewById(R.id.login_edit_phone);
        login_edit_password = (EditText) view.findViewById(R.id.login_edit_password);
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
            btn_xinlang = (ImageButton) getView().findViewById(R.id.btn_xinlang);
            btn_qq = (ImageButton) getView().findViewById(R.id.btn_qq);
            btn_weixin = (ImageButton) getView().findViewById(R.id.btn_weixin);
            Log.e("TAG", "获取到1");
            imgbtn.add(btn_xinlang);
            imgbtn.add(btn_weixin);
            imgbtn.add(btn_qq);
            //总共三个


            int i = 0;
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
                Log.e("TAG", Integer.toString(i));
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

                OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
                        .params("account", account)
                        .params("type", type)
                        .params("token", token)
                        .params("isThird", "true")
                        .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                            @Override
                            public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {

                                if (userLoginJson.getState()) {

                                    CurrentAccount.setLoginOrNot(true);//登录成功，设置登录状态

                                    String code = userLoginJson.getCode();
                                    if (code != null && code == "3") {
                                        Log.e(TAG, "第三方登陆的第一次登陆");
                                        transferMyFragmentToPersonalInformationActivity();
                                        transferMyFragmentToPersonalFragment();
                                    } else {
                                        getUserInfo(userLoginJson.getBody());
                                        transferMyFragmentToPersonalFragment();
                                    }

                                } else {
                                    Log.e(TAG, "登录失败！");
                                }
                            }
                        });


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

}
