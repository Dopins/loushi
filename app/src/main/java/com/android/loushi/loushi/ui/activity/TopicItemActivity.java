package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.TipsRecycleViewAdapter;
import com.android.loushi.loushi.adapter.TopicItemRecycleViewAdapter;
import com.android.loushi.loushi.callback.TopicCallBack;
import com.android.loushi.loushi.callback.TopicGroupCallBack;
import com.android.loushi.loushi.json.Strategyjson;
import com.android.loushi.loushi.json.TopicGroupjson;
import com.android.loushi.loushi.json.Topicjson;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by binpeiluo on 2016/7/24 0024.
 */
public class TopicItemActivity extends BaseActivity {

    private static final String TAG = "TopicItemActivity";
    public static final String TOPIC_ID = "topic_id";
    private static final String tempUrl = "www.loushi666.com/LouShi/base/topic.action";
    private static final String topicUrl = "http://119.29.187.58/LouShi/base/topic.action";

    private String tempUserID = "32";
    private String tempPsw = "mtf071330";

    private Integer mSkip = 0; //数据从哪里开始取
    private Integer mTake = 20;   //一次加载多少item
    private List<Topicjson.BodyBean> mTopicList;
    private Integer topic_id = 0;     //专题分类的item id

    private RecyclerView recyclerView;
    private ImageView imageViewSearch;
    private TextView textViewTitle;

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
        mTopicList = new ArrayList<Topicjson.BodyBean>();
        topic_id = getIntent().getIntExtra(TOPIC_ID, 0);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView_topic);
        imageViewSearch = (ImageView) findViewById(R.id.imageView_search);
        textViewTitle = (TextView) findViewById(R.id.textView_title);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TopicItemRecycleViewAdapter mAdapter = new TopicItemRecycleViewAdapter(this, mTopicList);
        recyclerView.addItemDecoration(new SpaceItemDecoration(this, 10));
        recyclerView.setAdapter(mAdapter);
        loadSomeData(tempUserID, mSkip, mTake);
    }

    private void loadSomeData(String userID, Integer skip, Integer take) {

//        OkHttpUtils.post()
//                .url(topicUrl)
//                .build()
//                .execute(new TopicGroupCallBack() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(TopicGroupjson response) {
//                        for (int i = 0; i < response.getBody().size(); i++) {
//                            topic = new Topic();
//                            TopicGroupjson.BodyBean temp = response.getBody().get(i);
//                            topic.setImgUrl(temp.getImgUrl());
//                            topic.setTitle(temp.getName());
//                            topic.setId(temp.getId());
//                            topic.setTopicNum(temp.getTopicNum());
//                            topics.add(topic);
//                        }
//                        topicAdapter.notifyDataSetChanged();
//                    }
//                });
    }

}
