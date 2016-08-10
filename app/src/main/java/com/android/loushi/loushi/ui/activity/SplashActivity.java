package com.android.loushi.loushi.ui.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.CorrectionInfo;
import android.widget.Toast;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.TradeConfigs;
import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.jsonbean.UpdateVersionJson;
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.util.CurrentAccount;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.taobao.tae.sdk.callback.InitResultCallback;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/6.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        InitTaobao();
        CheckUpdate();
        CheckCanLogin();


    }
    private void CheckCanLogin(){
        if(CurrentAccount.isLoginOrNot()){
            Log.e("splash","可登陆");
            if(CurrentAccount.isThird()){
                Log.e("splash","disanfang");
                LoginThird();
            }
            else{
                Log.e("splash","feidisanfang");
               LoginNotThird();
            }

        }
        else
            GoMainACtivity();
    }
    private void LoginThird(){
        final String account=CurrentAccount.getAccount();
        String password=CurrentAccount.getPassword();
        final String type =CurrentAccount.Third_type;

        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
                .params("account", account)
                .params("type", type)
                .params("token", password)
                .params("isThird", "true")
                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                         Log.e("splash",new Gson().toJson(userLoginJson));
                        if (userLoginJson.getState()) {

                            //CurrentAccount.setLoginOrNot(true);//登录成功，设置登录状态
                            String code = userLoginJson.getCode();
                            if (code != null && code.equals("3")) {

                            } else {
                                BaseActivity.user_id =userLoginJson.getBody() +"";
                                CurrentAccount.setUser_id(userLoginJson.getBody() + "");
                                getUserInfo(userLoginJson.getBody());

                            }

                        } else {
                            CurrentAccount.setLoginOrNot(false);
                            Log.e("splashthirdlogin", "登录失败！");
                        }
                        GoMainACtivity();

                    }

                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Log.e("splash", "loginerror");
                        GoMainACtivity();
                    }
                });
    }
    private void LoginNotThird(){
        final String account=CurrentAccount.getAccount();
        String password=CurrentAccount.getPassword();
        Log.e("splashaccount",account+password);
        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
                .params("mobile_phone", account)
                .params("password", password)
                .params("isThird", "false")
                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                        Log.e("splash","loginresponse");
                        if (userLoginJson.getState()) {
                            Log.e("splashnotthird", "登录成功！");

                            BaseActivity.user_id = userLoginJson.getBody() + ""; //冗余
                            CurrentAccount.setUser_id(userLoginJson.getBody() + "");
                            getUserInfo(userLoginJson.getBody());


                        } else {
                            CurrentAccount.setLoginOrNot(false);
                            Log.e("splashnotthirdlogin", "登录失败！");
                        }
                        //SystemClock.sleep(2000);
                        GoMainACtivity();
                    }
                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Log.e("splash", "loginerror");
                        GoMainACtivity();
                    }
                });
    }
    private void getUserInfo(int id) {
        //Log.e(TAG, "getUserInfo");
        String user_id = id + "";
        Log.e("BIG ", user_id);
        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userinfo.action")
                .params("user_id", user_id)
                .execute(new JsonCallback<UserInfoJson>(UserInfoJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserInfoJson userInfoJson, Request request, @Nullable Response response) {
                        if (userInfoJson.isState()) {
                            Log.e("splashgetinfo","getinfo");
                            CurrentAccount.storeDatas(userInfoJson);
                            EventBus.getDefault().post(new MainEvent(MainEvent.LOGIN_UPDATEINFO));
                            //transferMyFragmentToPersonalFragment();
                        }
                        //GoMainACtivity();
                    }
                });
    }
    private void InitTaobao() {
        TradeConfigs.defaultTaokePid = "mm_114880276_0_0";
        AlibabaSDK.asyncInit(this, new InitResultCallback() {

            @Override
            public void onSuccess() {
//                Toast.makeText(getApplicationContext(), "初始化成功", Toast.LENGTH_SHORT)
//                        .show();
                Log.e("splash", "success");
                //showItemDetailPage(ll);
            }

            @Override
            public void onFailure(int code, String message) {
//                Toast.makeText(getApplicationContext(), "初始化异常", Toast.LENGTH_SHORT)
//                        .show();
                Log.e("splash", "nosuccess" + message);
            }

        });
    }
    private boolean hasLogin() {
        String phone = CurrentAccount.getMobile_phone();
        String password = CurrentAccount.getPassword();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password))
            return false;
        else
            return true;
    }

    private void CheckUpdate(){
        OkHttpUtils.post(BaseActivity.url_update).execute(new JsonCallback<UpdateVersionJson>(UpdateVersionJson.class) {
            @Override
            public void onResponse(boolean b, UpdateVersionJson updateVersionJson, Request request, Response response) {
                if(updateVersionJson.isState()){
                    pareseXMLWithPull(updateVersionJson.getBody());
                }
            }
        });
    }

    private void pareseXMLWithPull(String xmlData){
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser =factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String version="";
            String url = "";
            String description="";
            while(eventType!= XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:{
                        if("version".equals(nodeName)){
                            version = xmlPullParser.nextText();
                            Log.e("splash", version);
                        }
                        else if("url".equals(nodeName)){
                            url = xmlPullParser.nextText();
                            Log.e("splash",url);
                        }
                        else if ("description".equals(nodeName)){
                            description = xmlPullParser.nextText();
                            Log.e("splash",description);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();

            }
            Log.e("splash",version+"|"+GetVersionCode());
            if (!version.equals(GetVersionCode())){
                new AlertDialog.Builder(SplashActivity.this)
                        .setTitle("检测到新版本")
                        .setMessage("message")
                        .setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("splash", "选择取消");
                                //CheckPermission();
                                Toast.makeText(SplashActivity.this, "取消更新", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("splash","选择确定");
                                CheckPermission();
                            }
                        })
                        .create()
                        .show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private String GetVersionCode(){
        PackageManager pm = this.getPackageManager();//context为当前Activity上下文
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(this.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    private void CheckPermission(){
        if(Build.VERSION.SDK_INT>=23) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                downloadApp();
            }
        }
        else{
            downloadApp();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1){
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //用户同意使用write
                downloadApp();
            }else{
                //用户不同意
                //InitTaobao();
                Toast.makeText(SplashActivity.this,"取消更新",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void downloadApp(){
        DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("http://dingphone.ufile.ucloud.com.cn/apk/guanwang/time2plato.apk");

        DownloadManager.Request request = new DownloadManager.Request(uri);

        // 设置下载路径和文件名

        request.setDestinationInExternalPublicDir("download", "time2plato.apk");

        request.setDescription("陋室新版本下载");

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        request.setMimeType("application/vnd.android.package-archive");

        // 设置为可被媒体扫描器找到

        request.allowScanningByMediaScanner();

        // 设置为可见和可管理

        request.setVisibleInDownloadsUi(true);


        //dManager.enqueue(request);

        final long refernece = dManager.enqueue(request);

        // 把当前下载的ID保存起来

        SharedPreferences sPreferences = getSharedPreferences("downloadplato", 0);

        sPreferences.edit().putLong("plato", refernece).commit();

        //InitTaobao();
        //继续执行
    }
    private void GoMainACtivity(){
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
