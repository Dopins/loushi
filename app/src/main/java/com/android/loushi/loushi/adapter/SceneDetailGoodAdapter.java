package com.android.loushi.loushi.adapter;

/**
 * Created by Administrator on 2016/7/24.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import com.android.loushi.loushi.jsonbean.SceneGoodJson;

import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneDetailGoodAdapter extends RecyclerView.Adapter<SceneDetailGoodAdapter.SceneDetailGoodViewHolder>  {

    private Context context;
    private List<SceneGoodJson.BodyBean> bodyBeanList;
    @Override
    public SceneDetailGoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SceneDetailGoodAdapter.SceneDetailGoodViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class SceneDetailGoodViewHolder extends RecyclerView.ViewHolder {

        public SceneDetailGoodViewHolder(View view) {
            super(view);

        }


    }

}
