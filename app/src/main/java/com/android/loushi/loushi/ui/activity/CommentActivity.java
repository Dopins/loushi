package com.android.loushi.loushi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.android.loushi.loushi.jsonbean.RecommendJson;
import com.android.loushi.loushi.jsonbean.ResponseJson;
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
        , SwipeRefreshLayout.OnRefreshListener, CommentAdapter.OnItemClickListener {

    private ImageView imageViewBack;  //imageView_back
    private TextView textViewTitle;  //textView_title
    private ImageView imageViewSearch;  //imageView_search
    private RecyclerView recycleView;  //recycleView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager manager;

    private ImageView imageViewComment;//imageView_comment
    private EditText editTextComment;//editText_comment
    private ImageView imageViewSure;//imageView_sure

    private String mType;
    private String mPid;

    private String mReplyId;

    private CommentAdapter mAdapter;
    private List<CommentJson.BodyBean> mCommentList = new ArrayList<CommentJson.BodyBean>();

    private InputMethodManager inputManager;

    private int firstVisibleItemPosition = 0;
    private int lastVisibleItemPosition = 0;
    private boolean isInputOpen;

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

    private void getData() {
        Intent intent = getIntent();

        mType = intent.getStringExtra(KeyConstant.TYPE);
        mPid = intent.getStringExtra(KeyConstant.PID);

        //TODO
//        mType = 1 + "";
//        mPid = 233 + "";

        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void initView() {
        initToolbar();
        initRecyclerView();
        initBottombar();
        loadComment();
    }

    private void loadComment() {
        Log.i("test", "loadComment...");
        loadComment(MainActivity.user_id, mType, mPid);
    }

    private void loadComment(String userId, String type, String pid) {
        OkHttpUtils.post(UrlConstant.COMMENTCURL)
                .tag(this)
                .params(KeyConstant.USER_ID, userId)
                .params(KeyConstant.TYPE, type)
                .params(KeyConstant.PID, pid)
                .execute(new JsonCallback<CommentJson>(CommentJson.class) {

                    @Override
                    public void onResponse(boolean isFromCache, CommentJson commentJson, Request request, @Nullable Response response) {
                        if (commentJson.getState()) {
                            mCommentList.clear();
                            mCommentList.addAll(commentJson.getBody());
                            mAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else
                            Toast.makeText(CommentActivity.this, "请重试", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initBottombar() {
        imageViewComment = (ImageView) findViewById(R.id.imageView_comment);
        editTextComment = (EditText) findViewById(R.id.editText_comment);
        imageViewSure = (ImageView) findViewById(R.id.imageView_sure);
        imageViewComment.setOnClickListener(this);
        imageViewSure.setOnClickListener(this);
    }

    private void initRecyclerView() {
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setProgressViewOffset(true, 0, 24);
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        manager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(manager);
        recycleView.addItemDecoration(new SpaceItemDecoration(this, 18));
        mAdapter = new CommentAdapter(this, mCommentList);
        mAdapter.setmOnItemClickListener(this);
        recycleView.setAdapter(mAdapter);
        recycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        firstVisibleItemPosition == 0) {
                    swipeRefreshLayout.setRefreshing(true);
                    loadComment();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisibleItemPosition == mAdapter.getItemCount() - 1)
                    Toast.makeText(CommentActivity.this, "憋拉啦,没数据惹", Toast.LENGTH_SHORT).show();
                else if(newState==RecyclerView.SCROLL_STATE_DRAGGING)
                    hideInputManager();
            }
        });

    }

    private void initToolbar() {
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);
        textViewTitle = (TextView) findViewById(R.id.textView_title);
        imageViewSearch = (ImageView) findViewById(R.id.imageView_search);
        textViewTitle.setText("评论");
        imageViewBack.setImageResource(R.drawable.arrow_back_s);
        imageViewBack.setOnClickListener(this);
        imageViewSearch.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_comment:
                newComment();
                break;
            case R.id.imageView_back:
                finish();
                break;
            case R.id.imageView_sure:
                Toast.makeText(CommentActivity.this, "评论", Toast.LENGTH_SHORT).show();
                replyComment();
                break;
        }
    }

    private void newComment() {
        mReplyId = "0";
        editTextComment.setHint("新评论");
    }


    private void replyComment() {
        String content = editTextComment.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "说点什么吧...", Toast.LENGTH_SHORT).show();
            return;
        }
        replyComment(MainActivity.user_id, mType, mPid, mReplyId, content);
        editTextComment.setText("");

    }

    private void replyComment(String user_id, String type, String pid, String reply_id, String cotent) {
        OkHttpUtils.post(UrlConstant.USERCOMMENTURL)
                .tag(this)
                .params(KeyConstant.USER_ID, user_id)
                .params(KeyConstant.TYPE, type)
                .params(KeyConstant.PID, pid)
                .params(KeyConstant.REPLYED_ID, reply_id)
                .params(KeyConstant.CONTENT, cotent)
                .execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, ResponseJson responseJson
                            , Request request, @Nullable Response response) {
                        if (responseJson.getState()) {
                            loadComment();
                        } else
                            Toast.makeText(CommentActivity.this
                                    , "error:" + responseJson.getReturn_info(), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    @Override
    public void onRefresh() {
        loadComment();
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this, "下拉刷新成功", Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View v, int postion) {

        if (!isInputOpen) {
            Log.i("test", "editTextComment==" + false);
            editTextComment.requestFocus();
            editTextComment.requestFocus();
            Toast.makeText(this, "" + postion, Toast.LENGTH_SHORT).show();
//            hideInputManager();
            openInputManager();
            editTextComment.setHint("回复" + mCommentList.get(postion).getUserInfo().getNickname() + ":");
            mReplyId = mCommentList.get(postion).getId() + "";
        }else{
            hideInputManager();
        }
//
//        editTextComment.requestFocus();
//        Toast.makeText(this,""+postion,Toast.LENGTH_SHORT).show();
//        hideInputManager();
//        editTextComment.setHint("回复"+mCommentList.get(postion).getUserInfo().getNickname()+":");
//        mReplyId=mCommentList.get(postion).getId()+"";
    }

    private void hideInputManager() {
//        inputManager.toggleSoftInput(0, InputMethodManager.RESULT_HIDDEN);
        inputManager.hideSoftInputFromWindow(editTextComment.getWindowToken(),0);
        isInputOpen=false;
//        Log.i("test","hideInputManager");
    }

    private void openInputManager(){
        inputManager.showSoftInput(editTextComment, InputMethodManager.SHOW_FORCED);
        isInputOpen=true;
//        Log.i("test","openInputManager");
    }
}
