package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.SceneDetailGoodAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.SceneGoodJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneDetailGoodFragment extends  LazyFragment {
    private RecyclerView recyclerView;
    private SceneDetailGoodAdapter sceneDetailGoodAdapter;
    private List<SceneGoodJson.BodyBean> bodyBeanList=new ArrayList<SceneGoodJson.BodyBean>();
    private String scene_id="1";

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_scene_detail_good);
        scene_id=getArguments().getString("SCENE_ID");
        init();
    }

    private void init() {
        recyclerView = (RecyclerView)getView().findViewById(R.id.recycleView);

        sceneDetailGoodAdapter = new SceneDetailGoodAdapter(getContext(),bodyBeanList);
        getSceneGood();
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        //SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(getContext(),20);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(sceneDetailGoodAdapter);
        //recyclerView.addItemDecoration(spaceItemDecoration);

    }
    private void getSceneGood(){
        OkHttpUtils.post("http://www.loushi666.com/LouShi/base/sceneGoods.action")

                .tag(getContext()).params("user_id", BaseActivity.user_id).params("scene_id", scene_id)
                .execute(new JsonCallback<SceneGoodJson>(SceneGoodJson.class) {
                    @Override
                    public void onResponse(boolean b, SceneGoodJson sceneGoodJson, Request request, Response response) {
                        Log.e("test", new Gson().toJson(sceneGoodJson));
                        if (sceneGoodJson.isState()) {
                            bodyBeanList.addAll(sceneGoodJson.getBody());
                            //Log.e("test22",sceneGoodJson.isState()+"");
                        }
                        Log.e("test222", bodyBeanList.size() + "");
                        sceneDetailGoodAdapter.notifyDataSetChanged();

                    }


                });
    }
}
