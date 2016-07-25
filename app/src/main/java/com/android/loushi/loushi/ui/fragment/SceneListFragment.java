package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.SceneRecyclerViewAdapter;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by dopin on 2016/7/17.
 */
public class SceneListFragment extends LazyFragment {

    private RecyclerView mRecyclerView;
    private List<SceneJson.BodyBean> bodyBeanList = new ArrayList<SceneJson.BodyBean>();
    private SceneRecyclerViewAdapter sceneRecyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;  //下拉刷新组件

    public SceneJson sceneJsons;
    private int tabIndex=1;
    private int get_total=0;
    private String user_id="0";

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
        GetSomeScene(6, user_id, get_total);

    }
    private void GetSomeScene(int take, String user_id, int skip) {

//        OkHttpUtils.post().url("http://119.29.187.58:10000/LouShi/base/scene.action")
//                .addParams("user_id", user_id)
//                .addParams("scene_group_id", Integer.toString(tabIndex))
//                .addParams("skip", Integer.toString(skip))
//                .addParams("take", Integer.toString(take)).build().execute(new SceneCallBack() {
//            @Override
//            public void onError(Call call, Exception e) {
//                e.printStackTrace();
//                Log.d("tag", Log.getStackTraceString(e));
//            }
//
//            @Override
//            public void onResponse(SceneJson sceneJson) {
//                if (sceneJson.isState()) {
//                    Log.e("tag", "加载一些");
//                    sceneJsons = sceneJson;
//                    //sceneAdapter=new SceneAdapter(getContext(),bodyBeanList,null,0);
//                    bodyBeanList.addAll(sceneJson.getBody());
//                    get_total += 5;
//                    //Log.d("tag", Integer.toString(sceneJson.getBody().size()));
//                    sceneRecyclerViewAdapter.notifyDataSetChanged();
//
//                }
//            }
//        });
    }
}
