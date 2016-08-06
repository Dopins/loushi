package com.android.loushi.loushi.ui.fragment;


import android.util.Log;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.RecommendJson;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;

import com.lzy.okhttputils.OkHttpUtils;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by dopin on 2016/7/17.
 */
public class SceneListHabitFragment extends SceneListFragment {

    @Override
    protected void GetSomeScene(int take, int skip) {

        OkHttpUtils.post(BaseActivity.url + "base/scene")
                // 请求方式和请求url
                .tag(this).params("user_id", BaseActivity.user_id)
                .params("scene_group_id",3+"")
                .params("skip", skip+"")
                .params("take",take+"")
                .execute(new JsonCallback<SceneJson>(SceneJson.class) {
                    @Override
                    public void onResponse(boolean b, SceneJson sceneJson, Request request, Response response) {
                        if (sceneJson.getState()) {
                            bodyBeanList.addAll(sceneJson.getBody());
                            get_total+=bodyBeanList.size();
                            if(sceneJson.getBody().size()<oneTakeNum) has_data=false;
                            sceneRecyclerViewAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.d("error", sceneJson.getReturn_info());
                        }
                    }
                });
    }
}
