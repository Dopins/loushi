package com.android.loushi.loushi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.loushi.loushi.jsonbean.UserAreaJson;
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;

/**
 * Created by Administrator on 2016/8/6.
 */
public class CurrentAccount {

    private static final String TAG = "CurrentAccount";
    private static final String TABLENAME = "UserInfo";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String IS_THIRD = "Third";
    public static final String THIRD_TYPE = "Third_type";
    public static final String PROVINCE = "province";
    public static final String DISTRICT = "district";
    public static final String CITY = "city";
    public static final String NICKNAME = "nickname";
    public static final String MOBILE_PHONE = "mobile_phone";
    public static final String HEAD_IMG_URL = "headImgUrl";
    public static final String EMAIL = "email";
    public static final String SCHOOL_NAME = "schoolName";
    public static final String SEX = "sex";
    public static final String MESSAGE_COUNT = "messageCount";
    public static final String USER_ID = "user_id";
    public static final String LOGIN_OR_NOT = "LoginOrNot";
    public static final String REFRESH = "reFresh";



     private static final String SEX_MEN="男";
     private static final String SEX_WOMEN="女";

    private static Context context;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public static void init(Context context) {
        CurrentAccount.context = context;
        sp = context.getSharedPreferences(TABLENAME, Context.MODE_PRIVATE);
        editor = sp.edit();
        //getDatas();
    }

    public static void storeAccountInfo(String user_id, String account, String password, Boolean Third, String Third_type) {
        if (!Third) {
            editor.putString(MOBILE_PHONE, account);
        }
        editor.putString(USER_ID, user_id);
        editor.putString(ACCOUNT, account);
        editor.putString(PASSWORD, password);
        editor.putBoolean(IS_THIRD, Third);
        editor.putString(THIRD_TYPE, Third_type);
        BaseActivity.user_id=user_id;
        editor.commit();
    }

    public static void storeUserInfo(UserAreaJson userAreaJson) {
        Log.e("BIG ", "initDatas");
        UserAreaJson.BodyBean body = (UserAreaJson.BodyBean) userAreaJson.getBody();
        if (body.getProvince() != null) editor.putString(PROVINCE, body.getProvince());
        if (body.getDistrict() != null) editor.putString(DISTRICT, body.getDistrict());
        if (body.getCity() != null) editor.putString(CITY, body.getCity());
        editor.commit();
        Log.e(TAG, "将数据存储至 SharedPreferences ");
    }

    public static void storeUserInfo(UserInfoJson userInfoJson) {
        if (userInfoJson == null) Log.e("BIG ", "null json");
        if (context == null) Log.e("BIG ", "null context");
        UserInfoJson.BodyBean body = userInfoJson.getBody();
        Log.e(TAG, "current prase" + body.toString());
        editor.putString(EMAIL, body.getEmail());
        editor.putString(HEAD_IMG_URL, body.getHeadImgUrl());
        editor.putString(USER_ID,body.getUserID()+"");
        editor.putString(MESSAGE_COUNT, body.getMessageCount()+"");
        editor.putString(MOBILE_PHONE, body.getMobilePhone());
        editor.putString(NICKNAME, body.getNickname());
        if(body.getSchool()!=null)
            editor.putString(SCHOOL_NAME, body.getSchool().getName());
        editor.putString(SEX, body.isSex()?SEX_MEN:SEX_WOMEN);
        editor.commit();
        Log.e(TAG, "将数据存储至 SharedPreferences ");
    }

    public static void storeUserInfo(String nickname, String headImgUrl, String sex,String province,String city,String schoolName) {
        editor.putString(NICKNAME, nickname);
        editor.putString(HEAD_IMG_URL, headImgUrl);
        editor.putString(SEX, sex);
        editor.putString(PROVINCE, province);
        editor.putString(CITY, city);
        editor.putString(SCHOOL_NAME, schoolName);
        editor.commit();
    }

    public static void set(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }
    public static void set(String key,boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }
    public static String get(String key,String defvalue){
        return sp.getString(key,defvalue);
    }
    public static boolean get(String key,boolean defvalue){
        return sp.getBoolean(key,defvalue);
    }

    public static boolean isThird(){
        return get(IS_THIRD,false);
    }
    public static String getAccount(){
        return get(ACCOUNT,"");
    }
    public static String getPassword(){
        return get(PASSWORD,"");
    }
    public static String getThirdType(){
        return get(THIRD_TYPE,"");
    }
    public static void setThirdType(String thirdType){
        set(THIRD_TYPE,thirdType);
    }

    public static void setUserId(String user_id){
        set(USER_ID,user_id);
        BaseActivity.user_id=user_id;
    }
    public static void setLoginOrNot(boolean loginOrNot){
        set(LOGIN_OR_NOT,loginOrNot);
    }
    public static void setIsThird(boolean isThird){
        set(IS_THIRD,isThird);
    }
    public static void setHeadImgUrl(String url){
        set(HEAD_IMG_URL,url);
    }
    public static void setNickname(String name){
        set(NICKNAME,name);
    }
    public static void setReFresh(boolean reFresh){
        set(REFRESH,reFresh);
    }

    public static void setMessageCount(String messageCount){
        set(MESSAGE_COUNT,messageCount);
    }

    public static boolean getReFresh(){
        return get(REFRESH,false);
    }

    public static String getMessageCount(){
        return get(MESSAGE_COUNT,0+"");
    }
    public static String getMobilePhone(){
        return get(MOBILE_PHONE,"");
    }
    public static boolean getLoginOrNot(){
        return get(LOGIN_OR_NOT,false);
    }
    public static String getHeadImgUrl(){
        return get(HEAD_IMG_URL,"");
    }
    public static String getNickname(){
        return get(NICKNAME,"");
    }
    public static String getSex(){
        return get(SEX,"男");
    }
    public static String getUserId(){
        return get(USER_ID,"");
    }


}
