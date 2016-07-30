package com.android.loushi.loushi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.top.TopService;
import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.CommentAdapter;
import com.android.loushi.loushi.callback.EncryptCallback;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.CommentJson;
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
 * Created by binpeiluo on 2016/7/29 0029.
 */
public class CommentActivity extends BaseActivity implements
        View.OnClickListener
        , SwipeRefreshLayout.OnRefreshListener {

    private ImageView imageViewBack;  //imageView_back
    private TextView textViewTitle;  //textView_title
    private ImageView imageViewSearch;  //imageView_search
    private RecyclerView recycleView;  //recycleView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ImageView imageViewComment;//imageView_comment
    private EditText editTextComment;//editText_comment
    private ImageView imageViewSure;//imageView_sure

    private String mType;
    private String mPid;

    private CommentAdapter mAdapter;
    private List<CommentJson.BodyBean> mCommentList=new ArrayList<CommentJson.BodyBean>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        initView();
    }

    private void getData(){
        Intent intent=getIntent();

        mType=intent.getStringExtra(KeyConstant.TYPE);
        mPid=intent.getStringExtra(KeyConstant.PID);

        //TODO
        mType=1+"";
        mPid=233+"";

    }

    private void initView(){
        initToolbar();
        initRecyclerView();
        initBottombar();
        loadComment();
    }

    private void loadComment(){
        loadComment(MainActivity.user_id,mType,mPid);
    }

    private void loadComment(String userId,String type,String pid){
        OkHttpUtils.post(UrlConstant.COMMENTCURL)
                .tag(this)
                .params(KeyConstant.USER_ID,userId)
                .params(KeyConstant.TYPE,type)
                .params(KeyConstant.PID,pid)
                .execute(new JsonCallback<CommentJson>(CommentJson.class) {
//                    @Override
//                    public CommentJson parseNetworkResponse(Response response) throws Exception {
//                        Log.i("test",response.body().string());
//                        return new Gson().fromJson(response.body().string(),CommentJson.class);
//                    }

                    @Override
                    public void onResponse(boolean isFromCache, CommentJson commentJson, Request request, @Nullable Response response) {
                        if(commentJson.getState()){
                            mCommentList.addAll(commentJson.getBody());
                            Log.i("test","commentJson len"+commentJson.getBody().size()+"");
                            mAdapter.notifyDataSetChanged();
                            Log.i("test","mAdapter len"+mAdapter.getItemCount());
                        }else
                            Toast.makeText(CommentActivity.this,"请重试",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initBottombar(){
        imageViewComment= (ImageView) findViewById(R.id.imageView_comment);
        editTextComment= (EditText) findViewById(R.id.editText_comment);
        imageViewSure= (ImageView) findViewById(R.id.imageView_sure);
        imageViewComment.setOnClickListener(this);
        imageViewSure.setOnClickListener(this);
    }

    private void initRecyclerView(){
        recycleView= (RecyclerView) findViewById(R.id.recycleView);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.addItemDecoration(new SpaceItemDecoration(this,18));
        mAdapter=new CommentAdapter(this,mCommentList);
        recycleView.setAdapter(mAdapter);
    }

    private void initToolbar() {
        imageViewBack= (ImageView) findViewById(R.id.imageView_back);
        textViewTitle= (TextView) findViewById(R.id.textView_title);
        imageViewSearch= (ImageView) findViewById(R.id.imageView_search);
        textViewTitle.setText("评论");
        imageViewBack.setImageResource(R.drawable.arrow_back_s);
        imageViewBack.setOnClickListener(this);
        imageViewSearch.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageView_comment:
                break;
            case R.id.imageView_back:
                finish();
                break;
            case R.id.imageView_sure:
                break;

        }
    }

    @Override
    public void onRefresh() {
        loadComment();
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this,"下拉刷新成功",Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();
    }
}
