package com.android.loushi.loushi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TopicItemAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.android.loushi.loushi.util.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/24 0024.
 */
public class TopicItemActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "TopicItemActivity";

    private static final String[] titlesName = {"文艺清新仓", "科技数码仓", "美食味道仓", "怪咖另类仓"
            , "欧式复古仓", "追星必备仓", "收纳储物仓", "次元趣味仓"};

    private Integer mSkip = 0; //数据从哪里开始取
    private Integer mTake = 20;   //一次加载多少item
    private List mTopicList;
    private Integer mTopic_id = 0;     //专题分类的item id
    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private ImageView imageViewSearch;
    private TextView textViewTitle;
    private ImageView imageViewBack;
    private SwipeRefreshLayout swipeRefreshLayout;

    private TopicItemAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic_item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initView();
    }

    //初始化变量
    private void initVariables() {
        mTopicList = new ArrayList<TopicJson.BodyBean>();
        mTopic_id = getIntent().getIntExtra(KeyConstant.TOPIC_ID, 0);
    }

    private void initView() {

        initToolbar();
        initSwipeLayout();
        initRecycleView();
        loadData(false);
    }

    private void loadData(boolean isClean) {
        loadSomeData(MainActivity.user_id, mTopic_id, mSkip, mTake,isClean);
    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TopicItemAdapter(this, mTopicList, TopicItemAdapter.AdapterType.TOPIC);
        recyclerView.addItemDecoration(new SpaceItemDecoration(this, 10));
        recyclerView.setAdapter(mAdapter);
        addItemClickListener();
    }

    private void initSwipeLayout() {
        //init swipe
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setColorSchemeColors(this.getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initToolbar() {
        //init toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        //init title
        textViewTitle = (TextView) findViewById(R.id.textView_title);
        textViewTitle.setText(titlesName[mTopic_id - 1]);//init imageview
        imageViewSearch = (ImageView) findViewById(R.id.imageView_search);
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);
        imageViewSearch.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
    }

    private void loadSomeData(String userID, Integer groupId, Integer skip, Integer take,final boolean isClean) {
        OkHttpUtils.post(UrlConstant.TOPICURL)
                .tag(this)
                .params("user_id", userID)
                .params("topic_group_id", groupId.toString())
                .params("skip", skip.toString())
                .params("take", take.toString())
                .execute(new JsonCallback<TopicJson>(TopicJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, TopicJson topicJson, Request request, @Nullable Response response) {
                        if (topicJson.getState()) {
                            if(isClean)
                                mTopicList.clear();
                            mTopicList.addAll(topicJson.getBody());
                            mAdapter.notifyDataSetChanged();
                            mSkip += topicJson.getBody().size();
                        }else
                            Toast.makeText(mContext,topicJson.getReturn_info(),Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
//                        if (topicJson == null || topicJson.getBody() == null)
//                            return;
//                        mTopicList.addAll(topicJson.getBody());
//                        mAdapter.notifyDataSetChanged();
//                        mSkip+=topicJson.getBody().size();
                    }
                });
    }

    private void addItemClickListener() {
        mAdapter.setmOnItemClickListener(new TopicItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TopicItemActivity.this, CategoryDetailActivity.class);
                String jsonString = new Gson().toJson(mTopicList.get(position));
                Log.e("jsonstring", jsonString);
                intent.putExtra(CategoryDetailActivity.TYPE, "1");
                intent.putExtra(CategoryDetailActivity.JSONSTRING, jsonString);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        //TODO  页面跳转
        switch (v.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.imageView_search:
                entryToSearch();
                break;
        }
    }

    private void entryToSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mSkip=0;
        loadData(true);
    }
}
