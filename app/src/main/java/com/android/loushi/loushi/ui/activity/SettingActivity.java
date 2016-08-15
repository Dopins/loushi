package com.android.loushi.loushi.ui.activity;

import android.Manifest;
import android.app.Dialog;
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
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.UpdateVersionJson;
import com.android.loushi.loushi.util.DataCleanManager;
import com.android.loushi.loushi.util.ToastUtils;
import com.lzy.okhttputils.OkHttpUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/4.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private Toolbar program_toolbar;
    private ImageView back;
    private LinearLayout ll_update;
    private TextView tv_updateversion;
    private LinearLayout ll_clear_cache;
    private LinearLayout ll_feedback;
    private LinearLayout ll_about_us;
    private String url="";
    private TextView tv_cache;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        program_toolbar = (Toolbar) findViewById(R.id.program_toolbar);
        back = (ImageView) program_toolbar.findViewById(R.id.back);
        ll_update = (LinearLayout) findViewById(R.id.ll_update);
        tv_updateversion = (TextView) findViewById(R.id.tv_updateversion);
        tv_updateversion.setText(String.format("%s%s","当前版本",GetVersionCode()));
        ll_clear_cache = (LinearLayout) findViewById(R.id.ll_clear_cache);
        ll_clear_cache.setOnClickListener(this);
        ll_feedback = (LinearLayout) findViewById(R.id.ll_feedback);
        ll_about_us = (LinearLayout) findViewById(R.id.ll_about_us);
        back.setOnClickListener(this);
        ll_update.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
        tv_cache=(TextView)findViewById(R.id.tv_cache);
        try {
            tv_cache.setText(DataCleanManager.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("caerror",Log.getStackTraceString(e));
        }
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.back:
                 finish();
                 break;
             case R.id.ll_update:
                 CheckUpdate();
                 break;
             case R.id.ll_feedback:
                 Intent intent = new Intent(SettingActivity.this,FeedActivity.class);
                 startActivity(intent);
                 break;
             case R.id.ll_clear_cache:
                 new AlertDialog.Builder(SettingActivity.this)
                         .setTitle("清除缓存")
                         .setMessage("确认清除吗")
                         .setNegativeButton("取消",new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 Log.e("splash", "选择取消");
                                 //CheckPermission();
                                 //Toast.makeText(SettingActivity.this,"已清除缓存",Toast.LENGTH_SHORT).show();

                             }
                         })
                         .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 Log.e("splash", "选择确定");
                                 DataCleanManager.clearAllCache(SettingActivity.this);
                                 tv_cache.setText("0K");
                                 ToastUtils.show(SettingActivity.this,"已清除缓存",Toast.LENGTH_SHORT);
                             }
                         })
                         .create()
                         .show();

                 break;
             case R.id.ll_about_us:
                 break;

         }
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
                new AlertDialog.Builder(SettingActivity.this)
                        .setTitle("检测到新版本")
                        .setMessage("message")
                        .setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("splash", "选择取消");
                                //CheckPermission();
                                Toast.makeText(SettingActivity.this,"取消更新",Toast.LENGTH_SHORT).show();
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
                downloadApp(url);
            }else{
                //用户不同意，自行处理即可
                //InitTaobao();

                Toast.makeText(SettingActivity.this,"取消更新",Toast.LENGTH_SHORT).show();
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

        sPreferences.edit().putLong("plato", refernece).commit();

        //InitTaobao();
        //继续执行
    }

}
