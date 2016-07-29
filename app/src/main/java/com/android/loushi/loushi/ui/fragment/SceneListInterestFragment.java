package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.SceneRecyclerViewAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.util.MyRecyclerOnScrollListener;
import com.android.loushi.loushi.util.SpacesItemDecoration;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by dopin on 2016/7/17.
 */
public class SceneListInterestFragment extends SceneListFragment {

    @Override
    protected void GetSomeScene(int take, int skip) {

        OkHttpUtils.post(BaseActivity.url + "base/scene")
                // 请求方式和请求url
                .tag(this).params("user_id", BaseActivity.user_id)
                .params("scene_group_id",0+"")
                .params("skip", skip+"")
                .params("take",take+"")
                .execute(new JsonCallback<SceneJson>(SceneJson.class) {
                    @Override
                    public void onResponse(boolean b, SceneJson sceneJson, Request request, Response response) {
                        if (sceneJson.getState()) {
                            bodyBeanList.addAll(sceneJson.getBody());
                            get_total+=bodyBeanList.size();
                            if(bodyBeanList.size()<oneTakeNum) has_data=false;
                            sceneRecyclerViewAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.d("error", sceneJson.getReturn_info());
                        }
                    }
                });
    }
}
