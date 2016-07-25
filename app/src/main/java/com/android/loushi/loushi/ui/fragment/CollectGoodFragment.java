package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.CollectGoodAdapter;


import com.android.loushi.loushi.jsonbean.UserCollectionsJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

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
//        OkHttpUtils.post().url("http://119.29.187.58:10000/LouShi/user/userCollections").addParams("user_id", "32")
//                .addParams("type", "3").addParams("skip","0")
//                .addParams("take","3").build().execute(new CollecttionsCallBack() {
//            @Override
//            public void onError(Call call, Exception e) {
//                Log.e("CollectScene", Log.getStackTraceString(e));
//            }
//
//            @Override
//            public void onResponse(CollectionsJson collectionsJson) {
//                if (collectionsJson.isState()) {
//                    for (int i = 0; i < collectionsJson.getBody().size(); i++) {
//                        goodsBeanList.add(collectionsJson.getBody().get(i).getGoods());
//                        Log.e("goodfra",new Gson().toJson(collectionsJson.getBody().get(i).getGoods()));
//
//                    }
//                    collectGoodAdapter.notifyDataSetChanged();
//                    //Log.e("CollectScene",collectionsJson.getBody().get(0).getScene().getName());
//                    //Toast.makeText(getContext(), collectionsJson.getBody().get(0).getScene().getName(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}
