package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.MyMessageAdapter;
import com.android.loushi.loushi.callback.UserMessageCallBack;
import com.android.loushi.loushi.json.UserMessageJson;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class MyMessageActivity extends BaseActivity{

    private TextView textView_toolbar;//标题
    private SwipeRefreshLayout swipeRefreshLayout_message;
    private RecyclerView recycleView_newComment;
    private RecyclerView recycleView_oldComment;
    private Button btn_cleanComment;
    private Toolbar toolbar;

    private MyMessageAdapter mMessageAdapter;
    private List<UserMessageJson.BodyBean> mBodyBeanList=new ArrayList<UserMessageJson.BodyBean>() ;

    //TODO
    private String temp_id="22";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mymessage;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        loadMessage();

    }

    private void initView(){

        textView_toolbar= (TextView) findViewById(R.id.toolbar_index);
        textView_toolbar.setText("");
        getSupportActionBar().setTitle("我的消息");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        swipeRefreshLayout_message= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout_message);
        recycleView_newComment= (RecyclerView) findViewById(R.id.recycleView_newComment);
        recycleView_oldComment= (RecyclerView) findViewById(R.id.recycleView_oldComment);
        btn_cleanComment= (Button) findViewById(R.id.btn_cleanComment);

        mMessageAdapter=new MyMessageAdapter(this,mBodyBeanList);
        mMessageAdapter.setmOnItemClickListener(new MyMessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {
                Toast.makeText(MyMessageActivity.this, ""+postion, Toast.LENGTH_SHORT).show();
            }
        });
        recycleView_newComment.setLayoutManager(new LinearLayoutManager(this));
        recycleView_newComment.setItemAnimator(new DefaultItemAnimator());
        recycleView_newComment.setAdapter(mMessageAdapter);

    }

    private void loadMessage(){
        OkHttpUtils.post().url("http://119.29.187.58:10000/LouShi/user/userMessage")
                .addParams("user_id",temp_id).build().execute(new UserMessageCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e("mymessage",Log.getStackTraceString(e));
            }

            @Override
            public void onResponse(UserMessageJson userMessageJson) {
                if(userMessageJson.isState()) {
                    Log.e("message",new Gson().toJson(userMessageJson));
                    mBodyBeanList.addAll(userMessageJson.getBody());
                    mMessageAdapter.notifyDataSetChanged();
                }

            }
        });
    }

}
