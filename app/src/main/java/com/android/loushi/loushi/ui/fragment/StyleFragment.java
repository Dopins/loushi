package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.SceneRecyclerViewAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.SceneDetailActivity;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpacesItemDecoration;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by dopin on 2016/7/17.
 */
public class StyleFragment extends LazyFragment {

    private RecyclerView mRecyclerView;
    private List<SceneJson.BodyBean> bodyBeanList = new ArrayList<SceneJson.BodyBean>();
    private SceneRecyclerViewAdapter sceneRecyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;  //下拉刷新组件


    private int get_total=0;
    private final int oneTakeNum=10;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_scene_list);
        init();
    }


    private void init() {
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_widget);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        sceneRecyclerViewAdapter = new SceneRecyclerViewAdapter(getContext(), bodyBeanList);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(0,0,0,10));//设置recycleview间距
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(sceneRecyclerViewAdapter);

        setClickListener();
        setRefreshingListener();
        setLoadMoreListener();
        addSomething2Scene();
    }
    private void setClickListener(){
        sceneRecyclerViewAdapter.setOnItemClickListener(new SceneRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "点击item" + position, Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getActivity(), SceneDetailActivity.class);
                intent.putExtra(SceneDetailActivity.SCENE_ID,Integer.toString(bodyBeanList.get(position).getId()));
                startActivity(intent);
//                Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                //intent.putExtra
//                //传入参数 给webview Post
//                int pos = position;
//                if (tabIndex == 0)
//                    pos = position - 1;
//                //pos -=1;
//                intent.putExtra(WebViewActivity.TYPE, "0");
//                //将scene以json格式传入
//                intent.putExtra(WebViewActivity.SCENE, new Gson().toJson(bodyBeanList.get(pos)));
//
//
//                startActivityForResult(intent, 2);
            }
        });
    }
    private void setLoadMoreListener() {
        mRecyclerView.addOnScrollListener(new MyRecyclerOnScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                addSomething2Scene();
            }
        });
    }

    private void setRefreshingListener(){

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Handler handler = new Handler();
                //handler.sendEmptyMessageAtTime(0, 1000);
                /* do some thing here*/
                get_total = 0;
                bodyBeanList.clear();
                addSomething2Scene();
                swipeRefreshLayout.setRefreshing(false);
                sceneRecyclerViewAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "下拉更新完成", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addSomething2Scene() {
        GetSomeScene(oneTakeNum, get_total);

    }
    private void GetSomeScene(int take, int skip) {

        OkHttpUtils.post(BaseActivity.url + "/base/scene")
                // 请求方式和请求url
                .tag(this).params("user_id", BaseActivity.user_id)
                .params("scene_group_id",2+"")
                .params("skip", skip+"")
                .params("take",take+"")
                .execute(new JsonCallback<SceneJson>(SceneJson.class) {
                    @Override
                    public void onResponse(boolean b, SceneJson sceneJson, Request request, Response response) {
                        if (sceneJson.getState()) {
                            bodyBeanList.addAll(sceneJson.getBody());
                            get_total+=oneTakeNum;
                            sceneRecyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            Log.d("error", sceneJson.getReturn_info());
                        }
                    }
                });
    }
}
