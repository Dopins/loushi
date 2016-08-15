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
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.sdk.android.AlibabaSDK;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.jsonbean.UpdateVersionJson;
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.fragment.CategoryFragment;
import com.android.loushi.loushi.ui.fragment.MyFragment;
import com.android.loushi.loushi.ui.fragment.PersonFragment;
import com.android.loushi.loushi.ui.fragment.SceneFragment;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.ToastUtils;
import com.android.loushi.loushi.util.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    public FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {SceneFragment.class, CategoryFragment.class, MyFragment.class};
    private String mTextviewArray[] = {"场景", "指南", "我的"};
    String url = "";
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {
            R.drawable.tab_scene_button,
            R.drawable.tab_category_button,
            R.drawable.tab_my_button};

    private final static int FLAG_LOGIN = 1;
    private final static int FLAG_GET_USRINFO = 2;
    private final static int DELAYTIME_LOGIN = 29 * 60 * 1000;
    private final static int DELAYTIME_USERINFO = 60 * 1000;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FLAG_LOGIN:
                    autoLogin();
                    handler.sendEmptyMessageDelayed(FLAG_LOGIN, DELAYTIME_LOGIN);
                    break;
                case FLAG_GET_USRINFO:
                    updateUserInfo();
                    handler.sendEmptyMessageDelayed(FLAG_GET_USRINFO, DELAYTIME_USERINFO);
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }



        initView();
        CheckUpdate();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        startHandler();
    }

    private void startHandler() {
        handler.sendEmptyMessageDelayed(FLAG_LOGIN, DELAYTIME_LOGIN);
        handler.sendEmptyMessageDelayed(FLAG_GET_USRINFO, DELAYTIME_USERINFO);
    }


    private void updateUserInfo() {
        if (!hasLogin()||MyMessageActivity.hasNewMessage())
            return;
        OkHttpUtils.post(UrlConstant.USERINFOURL)
                .params(KeyConstant.USER_ID, MainActivity.user_id)
                .execute(new JsonCallback<UserInfoJson>(UserInfoJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserInfoJson userInfoJson, Request request, @Nullable Response response) {
                        if (userInfoJson.isState()) {
//                            if(userInfoJson.getBody().getMessageCount()==0)
//                                return ;
                            Log.i(TAG, "getMessageCount==" + userInfoJson.getBody().getMessageCount());
                            CurrentAccount.storeUserInfo(userInfoJson);
                            EventBus.getDefault().post(new MainEvent(MainEvent.UPDATE_USERINFO));
                        }
                    }
                });
    }

    private boolean hasLogin() {
        return CurrentAccount.getLoginOrNot();
    }

    private void autoLogin() {

        if (!hasLogin())
            return;
        if(!CurrentAccount.isThird()) {
            String phone = CurrentAccount.getAccount();
            String password = CurrentAccount.getPassword();
            OkHttpUtils.post(UrlConstant.USERLOGINURL)
                    .params(KeyConstant.MOBILE_PHONE, phone)
                    .params(KeyConstant.PASSWORD, password)
                    .params(KeyConstant.ISTHIRD, "false")
                    .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                        @Override
                        public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                            if (userLoginJson.getState()) {
                                CurrentAccount.setUserId(userLoginJson.getBody()+"");
                                CurrentAccount.setLoginOrNot(true);
                                Log.e(TAG, "autoLogin 登录成功！");
                            } else {
                                Log.e(TAG, "autoLogin 登录失败！");
                                CurrentAccount.setLoginOrNot(false);
                            }
                        }
                    });
        }
        else{
            final String account=CurrentAccount.getAccount();
            String password=CurrentAccount.getPassword();
            final String type =CurrentAccount.getThirdType();

            OkHttpUtils.post(UrlConstant.USERLOGINURL)
                    .params(KeyConstant.ACCOUNT, account)
                    .params(KeyConstant.TYPE, type)
                    .params(KeyConstant.TOKEN, password)
                    .params(KeyConstant.ISTHIRD, "true")
                    .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                        @Override
                        public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                            Log.e("splash", new Gson().toJson(userLoginJson));
                            if (userLoginJson.getState()) {
                                //CurrentAccount.setLoginOrNot(true);//登录成功，设置登录状态
                                String code = userLoginJson.getCode();
                                if (code != null && code.equals("3")) {
                                } else {
                                    CurrentAccount.setUserId(userLoginJson.getBody()+"");
                                }
                                CurrentAccount.setLoginOrNot(true);

                            } else {
                                CurrentAccount.setLoginOrNot(false);
                                Log.e("splashthirdlogin", "登录失败！");
                            }
                        }
                    });
        }

    }

    private void initView() {
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.content);
        int count = fragmentArray.length;


        for (int i = 0; i < count; i++) {

            //为每一个Tab按钮设置图标、文字和内容

            final TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            if(i==2) {
                if(!CurrentAccount.getLoginOrNot())
                mTabHost.addTab(tabSpec, fragmentArray[i], null);
                else
                    mTabHost.addTab(tabSpec, PersonFragment.class, null);
            }
            else
                mTabHost.addTab(tabSpec, fragmentArray[i], null);

        }


    }


    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    private void InitTaobao() {
       /* TradeConfigs.defaultTaokePid = "mm_114880276_0_0";
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

        });*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MainEvent event) {
        Log.e("mainevent",event.getMsg()+"");
        switch (event.getMsg()){
            case MainEvent.NEED_LOGIN:
                Intent intent =new Intent(MainActivity.this,LoginFirstActivity.class);
                startActivity(intent);
                break;
            case MainEvent.STATE_CONNECT_FAIL:
                ToastUtils.show(MainActivity.this, "网络错误,请检查网络设置", ToastUtils.LENGTH_LONG);
                break;
            case MainEvent.LOGIN_UPDATEINFO:
                Log.e("person", "接收消息" + MainEvent.LOGIN_UPDATEINFO + "");
                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        handler.removeCallbacksAndMessages(null);
        handler = null;
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
                final String finalUrl = url;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("检测到新版本")
                        .setMessage(description)
                        .setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("splash", "选择取消");
                                //CheckPermission();
                                Toast.makeText(MainActivity.this, "取消更新", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("splash","选择确定");
                                CheckPermission(finalUrl);
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
    private void CheckPermission(String url){
        if(Build.VERSION.SDK_INT>=23) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                downloadApp(url);
            }
        }
        else{
            downloadApp(url);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1){
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //用户同意使用write
                downloadApp("http://119.147.33.13/beta.myapp.com/myapp/rdmexp/exp/file/comandroidloushi_10_48b260d2-f18e-4da6-9edd-8a055868a975.apk?mkey=57afd712c45c7184&f=8c5d&c=0&p=.apk");
            }else{
                //用户不同意
                //InitTaobao();
                Toast.makeText(MainActivity.this,"取消更新",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void downloadApp(String url){
        DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);

        // 设置下载路径和文件名

        request.setDestinationInExternalPublicDir("download", "loushiv"+GetVersionCode()+".apk");

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
        Log.e("splashid",refernece+"");

        sPreferences.edit().putLong("plato", refernece).commit();

        //InitTaobao();
        //继续执行
    }


}
