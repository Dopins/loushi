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
    private static String TYPE="TYPE";
    private String type="3";
    private int get_total=0;
    private int skip=0;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_collect_good);
        //type = getArguments().getString(TYPE);
        //recyclerView = (RecyclerView)findViewById(R.id.recycleView);
//        for(int i =0;i<=9;i++){
//            goodsBean=new CollectionsJson.BodyBean.GoodsBean();
//            goodsBean.setName("木柜");
//            goodsBean.setPrice(555);
//            goodsBean.setCollectionNum(222);
//            goodsBeanList.add(goodsBean);
//        }
        initview();
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
                addSomething2Good();
            }
        });
    }
    private void addSomething2Good(){

                OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollections")
                // 请求方式和请求url
                .tag(this).params("type", "3").params("user_id", BaseActivity.user_id)
                .params("skip", get_total + "").params("take", "6")

                        // 请求的 tag, 主要用于取消对应的请求
                   // 缓存模式，详细请看缓存介绍
                .execute(new JsonCallback<UserCollectionsJson>(UserCollectionsJson.class) {
                             @Override
                             public void onResponse(boolean b, UserCollectionsJson userCollectionsJson, Request request, Response response) {
                                 for (int i = 0; i < userCollectionsJson.getBody().size(); i++) {
                                     beanList.add(userCollectionsJson.getBody().get(i));
                                     get_total++;
                                 }

                                 collectGoodAdapter.notifyDataSetChanged();
                             }

                         }

                );


    }

}
