package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.CollectGoodAdapter;


import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.jsonbean.UserCollectionsJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

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
    private List<UserCollectionsJson.BodyBean.GoodsBean> goodsBeanList;
    private UserCollectionsJson.BodyBean.GoodsBean goodsBean;
    private CollectGoodAdapter collectGoodAdapter;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_collect_good);
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
    private void initview(){
        goodsBeanList = new ArrayList<>();
        collectGoodAdapter =new CollectGoodAdapter(getContext(),goodsBeanList);
        addSomething2Good();
        addSomething2Good();
        addSomething2Good();
        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(collectGoodAdapter);
    }
    private void addSomething2Good(){

        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollect.action")
                .tag(getContext())

                .params("user_id","48")
                .params("pid","1").params("type","3")
                .execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, ResponseJson responseJson, Request request, Response response) {
                        Log.e("res", new Gson().toJson(responseJson));
                    }
                });
                OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollections")
                // 请求方式和请求url
                .tag(this).params("type", "3").params("user_id", "48")
                .params("skip", "0").params("take", "5")
                        // 请求的 tag, 主要用于取消对应的请求
                   // 缓存模式，详细请看缓存介绍
                .execute(new JsonCallback<UserCollectionsJson>(UserCollectionsJson.class) {
                    @Override
                    public void onResponse(boolean b, UserCollectionsJson userCollectionsJson, Request request, Response response) {
                        Log.e("tes",new Gson().toJson(userCollectionsJson));
                        for (int i = 0; i < userCollectionsJson.getBody().size(); i++) {
                        goodsBeanList.add(userCollectionsJson.getBody().get(i).getGoods());


                    }
                        Log.e("te",goodsBeanList.size()+"");
                    collectGoodAdapter.notifyDataSetChanged();
                    }




                });
    }
}
