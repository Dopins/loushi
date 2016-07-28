package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.MyMessageAdapter;

import com.android.loushi.loushi.callback.EncryptCallback;
import com.android.loushi.loushi.jsonbean.UserMessageJson;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class MyMessageActivity extends BaseActivity implements View.OnClickListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recycleViewNewComment;
    private RecyclerView recycleViewOldComment;
    private LinearLayout btnCleanComment;
    //toolbar
    private ImageView imageViewBack;
    private TextView textViewTitle;
    private ImageView imageViewSearch;

    private MyMessageAdapter mMessageAdapter;
    private List<UserMessageJson.BodyBean> mBodyBeanList=new ArrayList<UserMessageJson.BodyBean>() ;

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

        imageViewBack= (ImageView) findViewById(R.id.imageView_back);
        textViewTitle= (TextView) findViewById(R.id.textView_title);
        imageViewSearch= (ImageView) findViewById(R.id.imageView_search);

        //init toolbar
        imageViewBack.setOnClickListener(this);
        textViewTitle.setText("我的消息");
        imageViewSearch.setVisibility(View.GONE);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout_message);
        recycleViewNewComment = (RecyclerView) findViewById(R.id.recycleView_newComment);
        recycleViewOldComment = (RecyclerView) findViewById(R.id.recycleView_oldComment);

        btnCleanComment = (LinearLayout) findViewById(R.id.btn_cleanComment);
        btnCleanComment.setOnClickListener(this);
//
        mMessageAdapter=new MyMessageAdapter(this,mBodyBeanList);
        mMessageAdapter.setmOnItemClickListener(new MyMessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {

                Toast.makeText(MyMessageActivity.this, ""+postion, Toast.LENGTH_SHORT).show();
            }
        });
        recycleViewNewComment.setLayoutManager(new LinearLayoutManager(this));
        recycleViewNewComment.setItemAnimator(new DefaultItemAnimator());
        recycleViewNewComment.setAdapter(mMessageAdapter);

    }

    private void loadMessage(){
        OkHttpUtils.post(UrlConstant.MYMESSAGE)
                .params(KeyConstant.USER_ID,MainActivity.user_id)
                .execute(new EncryptCallback<UserMessageJson>() {
                    @Override
                    public UserMessageJson parseNetworkResponse(Response response) throws Exception {
                        return new Gson().fromJson(response.body().string(),UserMessageJson.class);
                    }
                    @Override
                    public void onResponse(boolean isFromCache, UserMessageJson userMessageJson, Request request, @Nullable Response response) {
                        if(userMessageJson.getState()){
                            mBodyBeanList.addAll(userMessageJson.getBody());
                            Log.i("test",""+mBodyBeanList.get(0).getCDate());
                            Log.i("test",""+mBodyBeanList.size());
                            mMessageAdapter.notifyDataSetChanged();
                        }else
                            Toast.makeText(MyMessageActivity.this,"获取消息失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cleanComment:
                Toast.makeText(this,"click clean ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_back:
                finish();
                break;
        }
    }
}
