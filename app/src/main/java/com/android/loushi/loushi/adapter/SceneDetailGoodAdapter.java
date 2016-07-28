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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneDetailGoodAdapter extends RecyclerView.Adapter<SceneDetailGoodAdapter.SceneDetailGoodViewHolder>  {

    private Context context;
    private List<SceneGoodJson.BodyBean> bodyBeanList;
    public SceneDetailGoodAdapter(Context context,List<SceneGoodJson.BodyBean> bodyBeanList){
        this.context = context;
        this.bodyBeanList = bodyBeanList;
    }
    @Override
    public SceneDetailGoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_scene_detail_good,null);
        return new SceneDetailGoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SceneDetailGoodAdapter.SceneDetailGoodViewHolder holder, int position) {
         SceneGoodJson.BodyBean bodyBean = bodyBeanList.get(position);
         Picasso.with(context).load(bodyBean.getImages().get(0).getUrl()).fit().into(holder.img_good);
         holder.tv_name.setText(bodyBean.getName());
         holder.img_good.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });
    }

    @Override
    public int getItemCount() {
        return bodyBeanList.size();
    }
    public static class SceneDetailGoodViewHolder extends RecyclerView.ViewHolder {
        ImageView img_good;
        TextView tv_name;
        public SceneDetailGoodViewHolder(View view) {
            super(view);
            img_good= (ImageView)view.findViewById(R.id.img_good);
            tv_name=(TextView)view.findViewById(R.id.tv_name);
        }


    }

}
