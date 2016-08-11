package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.CollectGoodAdapter;


import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.jsonbean.UserCollectionsGood;
import com.android.loushi.loushi.jsonbean.UserCollectionsJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/22.
 */
public class CollectGoodFragment extends LazyFragment {
    private RecyclerView recyclerView;
    private List<UserCollectionsJson.BodyBean> beanList=new ArrayList<UserCollectionsJson.BodyBean>();
    private UserCollectionsJson.BodyBean.GoodsBean goodsBean;
    private CollectGoodAdapter collectGoodAdapter;
    public static String TYPE="TYPE";
    private String type="3";
    private Boolean is_all=false;
    private Boolean is_tip_all;
    private int skip=0;
    private int skip_s;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_collect_good);
        type=getArguments().getString(TYPE);
        if(type.equals("1")) {
            if(is_tip_all==null) {
                is_tip_all = false;
                skip_s = 0;
            }
        }
        initview();
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }
//@Override
//public void onActivityCreated(Bundle savedInstanceState) {
//    // TODO Auto-generated method stub
//    super.onActivityCreated(savedInstanceState);
//    initview();
////        Log.e(TAG,"onActivityCreated");
//}
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view=inflater.inflate(R.layout.fragment_collect_good,null);
//
//        return view;
//    }
    private void initview(){
        //beanList = new ArrayList<UserCollectionsJson.BodyBean>();
        Log.e("tes1", beanList.size() + "");
        collectGoodAdapter =new CollectGoodAdapter(getContext(),beanList,type);
        addSomething2Good();
        if(type.equals("1"))
            addStrategy();

        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(getContext(),5);
        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(collectGoodAdapter);
        recyclerView.addItemDecoration(spaceItemDecoration);
        recyclerView.addOnScrollListener(new MyRecyclerOnScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                if(!is_all) {

                    addSomething2Good();
                }
                else {
                    if (type.equals("1")){
                        if(!is_tip_all)
                            addStrategy();
                    }
                }
            }
        });
    }
    private void addSomething2Good(){

                OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollections")
                // 请求方式和请求url
                .tag(this).params("type", type).params("user_id", BaseActivity.user_id)
                .params("skip", skip + "").params("take", "6")


                        // 请求的 tag, 主要用于取消对应的请求
                   // 缓存模式，详细请看缓存介绍
                .execute(new JsonCallback<UserCollectionsJson>(UserCollectionsJson.class) {
                             @Override
                             public void onResponse(boolean b, UserCollectionsJson userCollectionsJson, Request request, Response response) {
                                 if(b){
                                     if(beanList.size()!=0)
                                         return;
                                 }
                                 if(userCollectionsJson.getState()) {
                                     skip+=6;
                                     if(skip>Integer.parseInt(userCollectionsJson.getReturn_info()))
                                         is_all=true;
                                     beanList.addAll(userCollectionsJson.getBody());

                                     collectGoodAdapter.notifyDataSetChanged();
                                 }
                             }

                         }

                );


    }
    private void addStrategy(){
        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollections")
                // 请求方式和请求url
                .tag(this).params("type", "2").params("user_id", BaseActivity.user_id)
                .params("skip", skip+"").params("take", "6")

                // 请求的 tag, 主要用于取消对应的请求
                // 缓存模式，详细请看缓存介绍
                .execute(new JsonCallback<UserCollectionsJson>(UserCollectionsJson.class) {
                             @Override
                             public void onResponse(boolean b, UserCollectionsJson userCollectionsJson, Request request, Response response) {
                                 if (userCollectionsJson.getState()) {
                                     skip_s += 6;
                                     if (skip > Integer.parseInt(userCollectionsJson.getReturn_info()))
                                         is_tip_all = true;
                                     beanList.addAll(userCollectionsJson.getBody());


                                     collectGoodAdapter.notifyDataSetChanged();
                                 }
                             }

                         }

                );
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MainEvent event) {
        Log.e("COLLECTGOOD", "接收消息");

        switch (event.getMsg()) {
            case MainEvent.UPDATE_COLLECT:
                Log.e("COLLECTGOOD", "接收消息" + MainEvent.UPDATE_COLLECT + "");
                updateCollect();
                break;


        }
    }

    private void updateCollect() {
        beanList = new ArrayList<UserCollectionsJson.BodyBean>();
        skip=0;
        skip_s=0;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
