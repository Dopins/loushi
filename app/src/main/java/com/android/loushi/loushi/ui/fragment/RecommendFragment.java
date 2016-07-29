package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
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
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpacesItemDecoration;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
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
    private List<RecommendJson.BodyBean> bodyBeanList = new ArrayList<>();
    private RecommendRecycleViewAdapter recommendRecycleViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;  //下拉刷新组件

    private String rDate;
    private final int oneTakeNum = 3 ;

    private boolean has_data=true;
    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_recommend);
        init();
    }
    private void init() {
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_widget);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        recommendRecycleViewAdapter = new RecommendRecycleViewAdapter(getContext(), bodyBeanList);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(0,0,0,10));//设置recycleview间距
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(recommendRecycleViewAdapter);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date=new Date();
        rDate=simpleDateFormat.format(date.getTime()).substring(0,10);

        setClickListener();
        setRefreshingListener();
        setLoadMoreListener();
        addSomething2Scene();
    }
    private void setClickListener(){
        recommendRecycleViewAdapter.setOnItemClickListener(new RecommendRecycleViewAdapter.OnItemClickListener() {
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
//                startActivityForResult(intent, 2);
            }
        });
    }
    private void setLoadMoreListener() {
        mRecyclerView.addOnScrollListener(new MyRecyclerOnScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                if(has_data)
                addSomething2Scene();
            }
        });
    }

    private void setRefreshingListener(){

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Date date=new Date();
                rDate=simpleDateFormat.format(date.getTime()).substring(0,10);

                bodyBeanList.clear();
                addSomething2Scene();
                swipeRefreshLayout.setRefreshing(false);
                recommendRecycleViewAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "下拉更新完成", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addSomething2Scene() {

            GetSomeScene(oneTakeNum);

    }
    private void GetSomeScene(int take) {

        OkHttpUtils.post(BaseActivity.url+"base/recommendation")
                // 请求方式和请求url
                .params("user_id",BaseActivity.user_id)
                .params("rdate", rDate)
                .params("take", take + "")
                .execute(new JsonCallback<RecommendJson>(RecommendJson.class) {
                    @Override
                    public void onResponse(boolean b, RecommendJson recommendJson, Request request, Response response) {
                        if (recommendJson.getState()) {
                            bodyBeanList.addAll(recommendJson.getBody());

                            rDate =bodyBeanList.get(bodyBeanList.size()-1).getRDate().substring(0,10);

                            if(bodyBeanList.size()<10)  if(bodyBeanList.size()<oneTakeNum) has_data=false;
                            recommendRecycleViewAdapter.notifyDataSetChanged();

                        } else {
                            Log.d("error", recommendJson.getReturn_info());
                        }
                    }
                });
    }
}