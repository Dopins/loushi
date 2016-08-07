package com.android.loushi.loushi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.audiofx.LoudnessEnhancer;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.lzy.okhttputils.OkHttpUtils;

import javax.security.auth.login.LoginException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/6.
 */
public class CurrentAccount {

    public static final String TAG = "CurrentAccount";

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static String user_id;
    public static String password;
    public static String LoginOrNot;

    public static String nickname;
    public static String mobile_phone;
    public static String headImgUrl;
    public static String email;
    public static String schoolName;
    public static Boolean sex;
    public static int messageCount;



    public static void initDatas(UserInfoJson userInfoJson, Context context) {
        if(userInfoJson == null) Log.e("BIG ", "null json" );
        if(context == null) Log.e("BIG ", "null context" );
        Log.e("BIG ", "initDatas");
        sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        UserInfoJson.BodyBean body = userInfoJson.getBody();
        editor.putString("nickname", body.getNickname());
        editor.putString("mobile_phone", body.getMobilePhone());
        editor.putString("email", body.getEmail());
        editor.putString("headImgUrl", body.getHeadImgUrl());
        editor.putString("schoolName", body.getSchool().getName());
        editor.putBoolean("sex", body.isSex());
        editor.putInt("messageCount", body.getMessageCount());
        editor.commit();
        Log.e(TAG, "将数据存储至 SharedPreferences ");
        getDatas(context);
    }

    public static void getDatas(Context context)
    {
        sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Log.e(TAG, "getDatas");
        setLoginOrNot(sharedPreferences.getString("LoginOrNot","null"));
        setUser_id(sharedPreferences.getString("user_id","null"));
        setPassword(sharedPreferences.getString("password","null"));
        setNickname(sharedPreferences.getString("nickname","null"));
        setMobile_phone(sharedPreferences.getString("mobilePhone","null"));
        setEmail(sharedPreferences.getString("email","null"));
        setHeadImgUrl(sharedPreferences.getString("headImgUrl","null"));
        setSchoolName(sharedPreferences.getString("schoolName","null"));
        setSex(sharedPreferences.getBoolean("sex",true));
        setMessageCount(sharedPreferences.getInt("messageCount",0));
    }

    public static String getSchoolName() {
        return schoolName;
    }

    public static void setSchoolName(String schoolName) {
        CurrentAccount.schoolName = schoolName;
    }

    public static String getHeadImgUrl() {
        return headImgUrl;
    }

    public static void setHeadImgUrl(String headImgUrl) {
        CurrentAccount.headImgUrl = headImgUrl;
    }

    public static int getMessageCount() {
        return messageCount;
    }

    public static void setMessageCount(int messageCount) {
        CurrentAccount.messageCount = messageCount;
    }

    public static String getNickname() {
        return nickname;
    }

    public static void setNickname(String nickname) {
        CurrentAccount.nickname = nickname;
    }


    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        CurrentAccount.user_id = user_id;
    }


    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CurrentAccount.password = password;
    }

    public static String getLoginOrNot() {
        return LoginOrNot;
    }

    public static void setLoginOrNot(String loginOrNot) {
        LoginOrNot = loginOrNot;
        editor.putString("LoginOrNot",LoginOrNot);
        editor.commit();
    }

    public static String getMobile_phone() {
        return mobile_phone;
    }

    public static void setMobile_phone(String mobile_phone) {
        CurrentAccount.mobile_phone = mobile_phone;
    }




    public static Boolean isSex() {
        return sex;
    }

    public static void setSex(Boolean sex) {
        CurrentAccount.sex = sex;
    }



    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        CurrentAccount.email = email;
    }


}
