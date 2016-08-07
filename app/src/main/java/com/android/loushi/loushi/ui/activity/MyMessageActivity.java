package com.android.loushi.loushi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.MyMessageAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.UserMessageJson;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.android.loushi.loushi.util.UrlConstant;
import com.lzy.okhttputils.OkHttpUtils;
import com.umeng.analytics.MobclickAgent;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/31 0031.
 */
public class MyMessageActivity extends BaseActivity implements
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private ImageView imageViewBack;  //imageView_back
    private TextView textViewTitle;//textView_title
    private ImageView imageViewSearch;//imageView_search
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recycleView;

    private MyMessageAdapter mAdapter;
    private static List<UserMessageJson.BodyBean> myMessageList = new ArrayList<UserMessageJson.BodyBean>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mymessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {

        initToolbar();
        initRecycleView();
        loadMessage();

    }

    /**
     * 判断是否有新消息
     * @return
     */
    public static boolean hasNewMessage(){
        boolean result=false;
        int messageCount= CurrentAccount.getMessageCount();
        return messageCount>0;
    }

    private void loadMessage(){
        if(myMessageList==null||myMessageList.size()==0)
            loadComment(MainActivity.user_id);
        else{
            if(mAdapter!=null){
                try {
                    mAdapter.parseMessage();
                    mAdapter.notifyDataSetChanged();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadComment(String user_id) {
        OkHttpUtils.post(UrlConstant.MYMESSAGE)
                .tag(this)
                .params(KeyConstant.USER_ID, user_id)
                .execute(new JsonCallback<UserMessageJson>(UserMessageJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserMessageJson userMessageJson,
                                           Request request, @Nullable Response response) {
                        if (userMessageJson.getState()) {
                            myMessageList.clear();
                            myMessageList.addAll(userMessageJson.getBody());
                            try {
                                mAdapter.parseMessage();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            mAdapter.notifyDataSetChanged();
                        } else
                            Toast.makeText(MyMessageActivity.this, "网络异常...", Toast.LENGTH_SHORT)
                                    .show();
                    }
                });
    }

    private void initRecycleView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.addItemDecoration(new SpaceItemDecoration(this,14));
        mAdapter = new MyMessageAdapter(this, myMessageList);
        mAdapter.setmOnItemClickListener(new MyMessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {
                String pid=mAdapter.getPositionPid(postion)+"";
                String type=mAdapter.getPositionType(postion)+"";
                int p=mAdapter.getPositionCommentId(postion);
//                Toast.makeText(MyMessageActivity.this,
//                        "pid,type,position"+pid+type+postion, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MyMessageActivity.this,CommentActivity.class);
                intent.putExtra(KeyConstant.TYPE,type);
                intent.putExtra(KeyConstant.PID,pid);
                intent.putExtra(KeyConstant.COMMENT_ID,p);
                startActivity(intent);
            }
        });
        recycleView.setAdapter(mAdapter);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setProgressViewOffset(
                true,
                0,
                (int)TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initToolbar() {
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);
        textViewTitle = (TextView) findViewById(R.id.textView_title);
        imageViewSearch = (ImageView) findViewById(R.id.imageView_search);
        imageViewBack.setOnClickListener(this);
        textViewTitle.setText("我的消息");
        imageViewSearch.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_back:
                finish();
                break;
        }
    }

    @Override
    public void onRefresh() {
        myMessageList.clear();
        loadMessage();
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MyMessageActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MyMessageActivity");
    }
}
