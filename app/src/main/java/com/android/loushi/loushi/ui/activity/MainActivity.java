package com.android.loushi.loushi.ui.activity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.TradeConfigs;
import com.android.loushi.loushi.R;
import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.event.ReceiveSmsEvent;
import com.android.loushi.loushi.ui.fragment.CategoryFragment;
import com.android.loushi.loushi.ui.fragment.MyFragment;
import com.android.loushi.loushi.ui.fragment.PersonFragment;
import com.android.loushi.loushi.ui.fragment.SceneFragment;
import com.taobao.tae.sdk.callback.InitResultCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {
    public FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {SceneFragment.class,CategoryFragment.class,MyFragment.class};
    private String mTextviewArray[] = {"场景", "指南", "我的"};
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_scene_button, R.drawable.tab_category_button,R.drawable.tab_my_button
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        InitTaobao();
        if (!EventBus.getDefault().isRegistered(this))
        EventBus.getDefault().register(this);
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
    }
    private void initView(){
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.content);
        int count = fragmentArray.length;
        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容

            final TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
                mTabHost.addTab(tabSpec, fragmentArray[i], null);

        }

    }


    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }


protected void onSaveInstanceState(Bundle outState) {
     //super.onSaveInstanceState(outState);


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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MainEvent event) {
        if(event.getMsg()==MainEvent.NEED_LOGIN){
//            Intent intent =new Intent(MainActivity.this,FeedActivity.class);
//            startActivity(intent);

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
