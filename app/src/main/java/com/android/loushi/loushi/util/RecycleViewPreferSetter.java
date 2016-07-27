package com.android.loushi.loushi.util;

import android.util.Log;

import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.RecommendJson;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.SceneJson;
import com.android.loushi.loushi.jsonbean.StrategyJson;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dopin on 2016/7/27.
 */
public class RecycleViewPreferSetter {
    private SelectedStateSetter selectedStateSetter;
    public void setSelectedStateSetter(SelectedStateSetter selectedStateSetter){
        this.selectedStateSetter=selectedStateSetter;
    }
    public interface SelectedStateSetter{
        void SetSelectedState(String num,boolean is_collected);
            //重写这个函数，喜欢人数为num，是否点赞给is_collected
    }

    public void setScenePrefer( String type,final int position ,final List<SceneJson.BodyBean> bodyBeanList) {
        //SceneJson.BodyBean body=bodyBeanList.get(position);

        String pid=bodyBeanList.get(position).getId()+"";

        OkHttpUtils.post(BaseActivity.url + "/LouShi/user/userCollect")
                // 请求方式和请求url
                //.tag(this)
                .params("user_id", BaseActivity.user_id)
                .params("type", type)
                .params("pid", pid)
                .execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
                    @Override
                    public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
                        if (responseJson.getState()) {
                            boolean is_collected=bodyBeanList.get(position).getCollected();
                            int prefer_num=bodyBeanList.get(position).getCollectionNum();

                            Log.d("tag",is_collected+"");
                            Log.d("tag",prefer_num+"");
                            if(is_collected){
                                bodyBeanList.get(position).setCollected(false);
                                bodyBeanList.get(position).setCollectionNum(prefer_num - 1);
                                selectedStateSetter.SetSelectedState((prefer_num - 1)+"", false);
                            }else{
                                bodyBeanList.get(position).setCollected(true);
                                bodyBeanList.get(position).setCollectionNum(prefer_num+1);
                                selectedStateSetter.SetSelectedState((prefer_num + 1)+"", true);
                            }

                        } else {
                            Log.d("error", responseJson.getReturn_info());
                        }
                    }
                });
    }
    public void setTopicPrefer( String type,final int position ,final List<TopicJson.BodyBean> bodyBeanList) {
        //SceneJson.BodyBean body=bodyBeanList.get(position);

        String pid=bodyBeanList.get(position).getId()+"";

        OkHttpUtils.post(BaseActivity.url + "/LouShi/user/userCollect")
                // 请求方式和请求url
                //.tag(this)
                .params("user_id", BaseActivity.user_id)
                .params("type", type)
                .params("pid", pid)
                .execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
                    @Override
                    public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
                        if (responseJson.getState()) {
                            boolean is_collected=bodyBeanList.get(position).getCollected();
                            int prefer_num=bodyBeanList.get(position).getCollectionNum();

                            Log.d("tag",is_collected+"");
                            Log.d("tag",prefer_num+"");
                            if(is_collected){
                                bodyBeanList.get(position).setCollected(false);
                                bodyBeanList.get(position).setCollectionNum(prefer_num - 1);
                                selectedStateSetter.SetSelectedState((prefer_num - 1)+"", false);
                            }else{
                                bodyBeanList.get(position).setCollected(true);
                                bodyBeanList.get(position).setCollectionNum(prefer_num+1);
                                selectedStateSetter.SetSelectedState((prefer_num + 1)+"", true);
                            }

                        } else {
                            Log.d("error", responseJson.getReturn_info());
                        }
                    }
                });
    }
    public void setStrategyPrefer( String type,final int position ,final List<StrategyJson.BodyBean> bodyBeanList) {
        //SceneJson.BodyBean body=bodyBeanList.get(position);

        String pid=bodyBeanList.get(position).getId()+"";

        OkHttpUtils.post(BaseActivity.url + "/LouShi/user/userCollect")
                // 请求方式和请求url
                //.tag(this)
                .params("user_id", BaseActivity.user_id)
                .params("type", type)
                .params("pid", pid)
                .execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
                    @Override
                    public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
                        if (responseJson.getState()) {
                            boolean is_collected=bodyBeanList.get(position).getCollected();
                            int prefer_num=bodyBeanList.get(position).getCollectionNum();

                            Log.d("tag",is_collected+"");
                            Log.d("tag",prefer_num+"");
                            if(is_collected){
                                bodyBeanList.get(position).setCollected(false);
                                bodyBeanList.get(position).setCollectionNum(prefer_num - 1);
                                selectedStateSetter.SetSelectedState((prefer_num - 1)+"", false);
                            }else{
                                bodyBeanList.get(position).setCollected(true);
                                bodyBeanList.get(position).setCollectionNum(prefer_num+1);
                                selectedStateSetter.SetSelectedState((prefer_num + 1)+"", true);
                            }

                        } else {
                            Log.d("error", responseJson.getReturn_info());
                        }
                    }
                });
    }
}
