package com.android.loushi.loushi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.audiofx.LoudnessEnhancer;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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

    public static Context context;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static String user_id;
    public static String account;

    public static String password;
    public static boolean LoginOrNot;
    public static boolean Third;
    public static String Third_type;

    public static boolean ReFresh;


    //userInfoJson里面的数据
    public static String nickname;
    public static String mobile_phone;
    public static String headImgUrl;
    public static String email;
    public static String schoolName;
    public static String sex;
    public static int messageCount;

    public static void init(Context context) {
        CurrentAccount.context = context;
        sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getDatas();
    }

    public static void storeAccountInfo(String user_id, String account, String password, Boolean Third, String Third_type) {
        CurrentAccount.user_id = user_id;
        CurrentAccount.account = account;
        CurrentAccount.password = password;
        CurrentAccount.Third = Third;
        CurrentAccount.Third_type = Third_type;
        if(!Third){
            CurrentAccount.mobile_phone = account;
            editor.putString("mobile_phone", account);
        }
        editor.putString("user_id", user_id);
        editor.putString("account", account);
        editor.putString("password", password);
        editor.putBoolean("Third", Third);
        editor.putString("Third_type", Third_type);
        editor.commit();
    }

    public static void storeDatas(UserInfoJson userInfoJson) {
        if (userInfoJson == null) Log.e("BIG ", "null json");
        if (context == null) Log.e("BIG ", "null context");
        Log.e("BIG ", "initDatas");

        UserInfoJson.BodyBean body = userInfoJson.getBody();
        Log.e(TAG, "current prase" + body.toString());
        if (body.getNickname() != null) editor.putString("nickname", body.getNickname());
        if (body.getMobilePhone() != null) editor.putString("mobile_phone", body.getMobilePhone());
        if (body.getHeadImgUrl() != null) editor.putString("headImgUrl", body.getHeadImgUrl());
        if (body.getEmail() != null) editor.putString("email", body.getEmail());
        if (body.getSchool() != null)
            editor.putString("schoolName", body.getSchool().getName());
        if (body.isSex()) editor.putString("sex", "1");
        else editor.putString("sex", "0");
        editor.putInt("messageCount", body.getMessageCount());
        editor.commit();
        Log.e(TAG, "将数据存储至 SharedPreferences ");
        getDatas();
    }

    public static void storeDatas(String nickname, String headImgUrl, String schoolName, String sex) {
        CurrentAccount.nickname = nickname;
        CurrentAccount.headImgUrl = headImgUrl;
        CurrentAccount.schoolName = schoolName;
        CurrentAccount.sex = sex;
        editor.putString("nickname", nickname);
        editor.putString("headImgUrl", headImgUrl);
        editor.putString("schoolName", schoolName);
        editor.putString("sex", sex);
        editor.commit();
    }

    public static void getDatas() {
        Log.e(TAG, "getDatas");
        setUser_id(sharedPreferences.getString("user_id", "null"));
        setPassword(sharedPreferences.getString("password", "null"));
        setLoginOrNot(sharedPreferences.getBoolean("LoginOrNot", false));
        setThird(sharedPreferences.getBoolean("Third", false));
        setNickname(sharedPreferences.getString("nickname", "null"));
        setMobile_phone(sharedPreferences.getString("mobile_phone", "null"));
        setEmail(sharedPreferences.getString("email", "null"));
        setHeadImgUrl(sharedPreferences.getString("headImgUrl", "null"));
        setSchoolName(sharedPreferences.getString("schoolName", "null"));
        setSex(sharedPreferences.getString("sex", "null"));
        setMessageCount(sharedPreferences.getInt("messageCount", 0));
        setThird_type(sharedPreferences.getString("Third_type", "0"));
        setAccount(sharedPreferences.getString("account","null"));
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
        editor.putString("headImgUrl", headImgUrl);
        editor.commit();
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

    public static boolean isLoginOrNot() {
        return LoginOrNot;
    }

    public static void setLoginOrNot(boolean loginOrNot) {
        LoginOrNot = loginOrNot;
        editor.putBoolean("LoginOrNot", loginOrNot);
        editor.commit();
    }

    public static String getMobile_phone() {
        return mobile_phone;
    }

    public static void setMobile_phone(String mobile_phone) {
        CurrentAccount.mobile_phone = mobile_phone;
    }


    public static String getSex() {
        return sex;
    }

    public static void setSex(String sex) {
        CurrentAccount.sex = sex;
    }


    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        CurrentAccount.email = email;
    }

    public static boolean isThird() {
        return Third;
    }

    public static void setThird(boolean Third) {
        Third = Third;
    }

    public static boolean isReFresh() {
        return ReFresh;
    }

    public static void setReFresh(boolean reFresh) {
        ReFresh = reFresh;
    }

    public static String getThird_type() {
        return Third_type;
    }

    public static void setThird_type(String third_type) {
        Third_type = third_type;
    }

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        CurrentAccount.account = account;
    }

}
