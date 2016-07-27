package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TopicItemRecycleViewAdapter;
import com.android.loushi.loushi.callback.TopicCallback;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.android.loushi.loushi.util.UrlConstant;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/24 0024.
 */
public class TopicItemActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "TopicItemActivity";
    public static final String TOPIC_ID = "topic_id";

    private static final String[] titlesName = {"文艺清新仓", "科技数码仓", "美食味道仓", "怪咖另类仓"
            , "欧式复古仓", "追星必备仓", "收纳储物仓", "次元趣味仓"};

    private String tempUserID = "32";
    private String tempPsw = "mtf071330";

    private Integer mSkip = 0; //数据从哪里开始取
    private Integer mTake = 20;   //一次加载多少item
    private List<TopicJson.BodyBean> mTopicList;
    private Integer mTopic_id = 0;     //专题分类的item id

    private RecyclerView recyclerView;
    private ImageView imageViewSearch;
    private TextView textViewTitle;
    private ImageView imageViewBack;

    private TopicItemRecycleViewAdapter mAdapter;

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
        mTopic_id = getIntent().getIntExtra(TOPIC_ID, 0);
    }

    private void initView() {
        //init toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        //init title
        textViewTitle = (TextView) findViewById(R.id.textView_title);
        textViewTitle.setText(titlesName[mTopic_id]);
        //init imageview
        imageViewSearch = (ImageView) findViewById(R.id.imageView_search);
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);
        imageViewSearch.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TopicItemRecycleViewAdapter(this, mTopicList);
        recyclerView.addItemDecoration(new SpaceItemDecoration(this, 10));
        recyclerView.setAdapter(mAdapter);
        loadSomeData(tempUserID, mTopic_id, mSkip, mTake);
    }

    private void loadSomeData(String userID, Integer groupId, Integer skip, Integer take) {
//        OkHttpUtils.post("http://119.29.187.58:10000/LouShi/user/userLogin.action")
//                // 请求方式和请求url
//                .tag(this).params("mobile_phone", "13750065622").params("password", "mtf071330")
//                .params("isThird", "false")
//                        // 请求的 tag, 主要用于取消对应的请求
//                        // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                        // 缓存模式，详细请看缓存介绍
//
//                        //有的操作要设置缓存，有的不要 这个号供测试用
//
//                .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
//                    @Override
//                    public void onResponse(boolean b, UserLoginJson userLoginJson, Request request, Response response) {
//                        Log.e(TAG,request.header("contentType"));
//                        //这里现在是48
//
//                    }
//                });
        OkHttpUtils.post(UrlConstant.TOPICURL)
                .tag(this)
                .params("user_id", userID)
                .params("topic_group_id", groupId.toString())
                .params("skip", skip.toString())
                .params("take", take.toString())
                .execute(new TopicCallback() {

                    @Override
                    public void onResponse(boolean isFromCache, TopicJson topicJson, Request request, @Nullable Response response) {
                        if (topicJson == null || topicJson.getBody() == null)
                            return;
                        mTopicList.addAll(topicJson.getBody());
                        mAdapter.notifyDataSetChanged();
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
                break;
        }
        Toast.makeText(this, "on click", Toast.LENGTH_SHORT).show();
    }
}
