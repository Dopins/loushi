package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.RecommendRecycleViewAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.RecommendJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.CategoryDetailActivity;
import com.android.loushi.loushi.ui.activity.SceneDetailActivity;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpacesItemDecoration;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dopin on 2016/7/17.
 */
public class RecommendFragment extends LazyFragment {

    private RecyclerView mRecyclerView;
    private List<RecommendJson.BodyBean> bodyBeanList;
    private RecommendRecycleViewAdapter recommendRecycleViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;  //下拉刷新组件

    private String rDate;
    private final int oneTakeNum = 3 ;

    private boolean has_data;
    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_recommend);
        init();
    }
//    protected void onDestroyViewLazy() {
//        Log.e("tag","onDestroyViewLazy");
//    }
    private void init() {
        boolean have_data=false;
        if(bodyBeanList==null){
            has_data=true;
            bodyBeanList = new ArrayList<>();
            Date date=new Date();
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            rDate=simpleDateFormat.format(date.getTime()).substring(0,10);
        }else{
            have_data=true;
        }
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_widget);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        if(recommendRecycleViewAdapter==null)
        recommendRecycleViewAdapter = new RecommendRecycleViewAdapter(getContext(), bodyBeanList);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 10));//设置recycleview间距
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(recommendRecycleViewAdapter);

        setClickListener();
        setRefreshingListener();
        setLoadMoreListener();
        if(have_data){
            recommendRecycleViewAdapter.notifyDataSetChanged();
        }else{
            addSomething2Scene();
        }
    }
    private void setClickListener(){
        recommendRecycleViewAdapter.setOnItemClickListener(new RecommendRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getContext(), "点击item" + position, Toast.LENGTH_SHORT).show();
                Log.e("pos", position + "");
                if (recommendRecycleViewAdapter.getItemViewType(position) == 0) {
                    Intent intent = new Intent(getActivity(), SceneDetailActivity.class);
                    String sceneJsonString = new Gson().toJson(bodyBeanList.get(position / 4).getScene());
                    intent.putExtra("SCENE_STRING", sceneJsonString);
                    startActivity(intent);
                }
                if (recommendRecycleViewAdapter.getItemViewType(position) == 1) {
                    Intent intent = new Intent(getActivity(), CategoryDetailActivity.class);
                    String jsonString = new Gson().toJson(bodyBeanList.get(position / 4).getTopic());
                    Log.e("jsonstring", jsonString);
                    intent.putExtra(CategoryDetailActivity.TYPE, "1");
                    intent.putExtra(CategoryDetailActivity.JSONSTRING, jsonString);
                    startActivity(intent);
                }
                if (recommendRecycleViewAdapter.getItemViewType(position) == 2) {
                    Intent intent = new Intent(getActivity(), CategoryDetailActivity.class);
                    String jsonString = new Gson().toJson(bodyBeanList.get(position / 4).getStrategy());
                    Log.e("jsonstring", jsonString);
                    intent.putExtra(CategoryDetailActivity.TYPE, "2");
                    intent.putExtra(CategoryDetailActivity.JSONSTRING, jsonString);
                    startActivity(intent);
                }

            }
        });
    }
    private void setLoadMoreListener() {
        mRecyclerView.addOnScrollListener(new MyRecyclerOnScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                if (has_data)
                    addSomething2Scene();
            }
        });
    }

    private void setRefreshingListener(){

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Date date = new Date();
                rDate = simpleDateFormat.format(date.getTime()).substring(0, 10);

                bodyBeanList.clear();
                addSomething2Scene();
                recommendRecycleViewAdapter.notifyDataSetChanged();
                //Toast.makeText(getContext(), "下拉更新完成", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addSomething2Scene() {

            GetSomeScene(oneTakeNum);

    }
    private void GetSomeScene(int take) {

        swipeRefreshLayout.setRefreshing(true);
        OkHttpUtils.post(BaseActivity.url+"base/recommendation")
                // 请求方式和请求url
                .params("user_id",BaseActivity.user_id)
                .params("rdate", rDate)
                .params("take", take + "").cacheKey("recommend").cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new JsonCallback<RecommendJson>(RecommendJson.class) {
                    @Override
                    public void onResponse(boolean b, RecommendJson recommendJson, Request request, Response response) {
                        if (recommendJson.getState()) {

                            if (recommendJson.getBody().size() < oneTakeNum) has_data = false;

                            if (isEmpty(recommendJson)) {
                                has_data = false;
                            } else {
                                bodyBeanList.addAll(recommendJson.getBody());
                                rDate = bodyBeanList.get(bodyBeanList.size() - 1).getRDate().substring(0, 10);
                                recommendRecycleViewAdapter.notifyDataSetChanged();
                            }
                            swipeRefreshLayout.setRefreshing(false);

                        } else {
                            Log.d("error", recommendJson.getReturn_info());
                        }
                    }
                });
    }
    private boolean isEmpty(RecommendJson recommendJson){
        for(int i=0;i<recommendJson.getBody().size();i++){
            RecommendJson.BodyBean body=recommendJson.getBody().get(i);
            if(body.getScene()==null&&body.getStrategy()==null&&body.getTopic()==null) return true;
        }
        return false;
    }
}