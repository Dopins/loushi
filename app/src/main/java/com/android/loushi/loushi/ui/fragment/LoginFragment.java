package com.android.loushi.loushi.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.greenrobot.eventbus.EventBus;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.DialogCallback;
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
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.ToastUtils;
import com.android.loushi.loushi.util.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;

import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/19.
 */
public class LoginFragment extends Fragment {

    public static final String TAG = "LoginFragment";

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
        initPlatformList();
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

                if (!isMobileNO(login_edit_phone.getText().toString())) {
                    ToastUtils.show(getActivity(), "请输入有效的电话号码!", ToastUtils.LENGTH_SHORT);
                    Log.e(TAG, "请输入有效的电话号码!");
                }
                if (login_edit_password.getText().toString().length() < 6) {
                    ToastUtils.show(getActivity(), "密码不能少于6位", ToastUtils.LENGTH_SHORT);
                } else {


                    Log.e(TAG, login_edit_phone.getText().toString());
                    Log.e(TAG, login_edit_password.getText().toString());
                    OkHttpUtils.post(UrlConstant.USERLOGINURL)
                            .params(KeyConstant.MOBILE_PHONE, login_edit_phone.getText().toString())
                            .params(KeyConstant.PASSWORD, login_edit_password.getText().toString())
                            .params(KeyConstant.ISTHIRD, "false")
                            .execute(new DialogCallback<UserLoginJson>((AppCompatActivity) getActivity(), UserLoginJson.class) {
                                @Override
                                public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                                    Log.e(TAG, request.toString());
                                    Log.e(TAG, response.toString());
                                    if (userLoginJson.getState()) {
                                        Log.e(TAG, "登录成功！");
                                        BaseActivity.user_id = userLoginJson.getBody() + ""; //冗余
                                        Log.e(TAG, userLoginJson.getBody() + "");
                                        CurrentAccount.setLoginOrNot(true);
                                        CurrentAccount.storeAccountInfo(
                                                userLoginJson.getBody()+"" ,
                                                login_edit_phone.getText().toString().trim(),
                                                login_edit_password.getText().toString().trim(),
                                                false,
                                                "0");
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
        OkHttpUtils.post(UrlConstant.USERINFOURL)
                .params(KeyConstant.USER_ID, user_id)
                .execute(new DialogCallback<UserInfoJson>((AppCompatActivity) getActivity(), UserInfoJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserInfoJson userInfoJson, Request request, @Nullable Response response) {
                        if (userInfoJson.isState()) {
                            Log.e(TAG, "成功获取用户信息");
                            Log.e(TAG, request.toString());
                            Log.e(TAG, response.toString());

                            UserInfoJson.BodyBean body = userInfoJson.getBody();
                            //CurrentAccount.storeUserInfo(userInfoJson);
                            Log.e(TAG, "1 :" + body.getNickname());
                            Log.e(TAG, "2 :" + body.getMobilePhone());
                            Log.e(TAG, "3 :" + body.getHeadImgUrl());
                            Log.e(TAG, "4 :" + body.getEmail());
//                            Log.e(TAG,"5 :"+ body.getSchool().getName());
                            Log.e(TAG, "6 :" + body.isSex());
                            Log.e(TAG, "7 :" + body.getMessageCount());

                            CurrentAccount.storeUserInfo(userInfoJson);
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

                final String account = platform.getDb().getUserId();
                final String token = generateToken(account);
                Log.e("LoginAPP", token);
                //token是接口需要
                final String type;
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

                OkHttpUtils.post(UrlConstant.USERLOGINURL)
                        .params(KeyConstant.ACCOUNT, account)
                        .params(KeyConstant.TYPE, type)
                        .params(KeyConstant.TOKEN, token)
                        .params(KeyConstant.ISTHIRD, "true")
                        .execute(new DialogCallback<UserLoginJson>((AppCompatActivity) getActivity(), UserLoginJson.class) {
                            @Override
                            public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                                Log.e("loginthird",new Gson().toJson(userLoginJson));
                                if (userLoginJson.getState()) {

                                    CurrentAccount.setLoginOrNot(true);//登录成功，设置登录状态
                                    CurrentAccount.storeAccountInfo(
                                            userLoginJson.getBody() + "",
                                            account,
                                            token,
                                            true,
                                            type);
                                    String code = userLoginJson.getCode();


                                    if (code != null && code.equals("3")) {
                                        Log.e(TAG, "第三方登陆的第一次登陆");
                                        CurrentAccount.setLoginOrNot(true);
                                        CurrentAccount.storeAccountInfo(
                                                userLoginJson.getBody() + "",
                                                account,
                                                token,
                                                true,
                                                type);
                                        //transferMyFragmentToPersonalInformationActivity();
                                        CurrentAccount.setHeadImgUrl(platform.getDb().getUserIcon());
                                        CurrentAccount.setNickname(platform.getDb().getUserName());
                                        String sex= "男";
                                        String sexBool = "true";
                                        if (!TextUtils.isEmpty(platform.getDb().getUserGender()) && platform.getDb().getUserGender().equals("f")) {
                                            sexBool = "false";
                                            sex = "女";
                                        }
                                        CurrentAccount.storeUserInfo(platform.getDb().getUserName(),
                                                platform.getDb().getUserIcon(),
                                                sex,"","","");
                                        postUserInfo(userLoginJson.getBody() + "", platform.getDb().getUserName(), platform.getDb().getUserIcon(), sexBool);
                                        transferMyFragmentToPersonalFragment();

                                    } else {
                                        CurrentAccount.setLoginOrNot(true);
                                        CurrentAccount.storeAccountInfo(userLoginJson.getBody() + "", account, token, true, type);
                                        getUserInfo(userLoginJson.getBody());

                                    }
                                    MobclickAgent.onProfileSignIn(platform.getName(), account);

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

    private void postUserInfo(String user_id, String nickname, String headImgUrl, String sex) {
        OkHttpUtils.post(UrlConstant.USERINFOALTURL)
                .params(KeyConstant.USER_ID, user_id)
                .params(KeyConstant.NICKNAME, nickname)
                .params(KeyConstant.HEADIMGURL, headImgUrl)
                .params(KeyConstant.SEX, sex)
                .execute(new JsonCallback<UserInfoJson>(UserInfoJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserInfoJson userInfoJson, Request request, @Nullable Response response) {
                        Log.e(TAG, "UserInfoJson.onResponse: " + response.toString());
                        Log.e(TAG, "userInfoJson.getState: " + userInfoJson.isState());
                        if (userInfoJson.isState()) {
                            Log.e(TAG, "onResponse: 资料提交成功 ！");
                            CurrentAccount.setReFresh(true);
                        }
                    }
                });
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
