package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import com.android.loushi.loushi.jsonbean.UserCollectionsJson;
import com.squareup.picasso.Picasso;


import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/7/22.
 */
public class CollectGoodAdapter  extends RecyclerView.Adapter<CollectGoodAdapter.CollectGoodViewHolder> {

    private Context context;
    private List<UserCollectionsJson.BodyBean.GoodsBean> goodsBeanList;
    public CollectGoodAdapter(Context context,List<UserCollectionsJson.BodyBean.GoodsBean> goodsBeanList){
        this.context = context;
        this.goodsBeanList=goodsBeanList;
    }
    @Override
    public CollectGoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectGoodViewHolder(LayoutInflater.from(
                context).inflate(R.layout.collect_good_item, parent,
                false));
    }

    @Override
    public void onBindViewHolder(final CollectGoodViewHolder holder, final int position) {
        final UserCollectionsJson.BodyBean.GoodsBean goodsBean =goodsBeanList.get(position);
        holder.tv_name.setText(goodsBean.getName());
        Picasso.with(context).load(goodsBean.getImages().get(0).getUrl()).fit().into(holder.img_good);

        holder.tv_like_count.setText(Integer.toString(goodsBean.getCollectionNum()));
        holder.btn_like.setChecked(true);
//        holder.btn_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
//                OkHttpUtils.post().url("http://119.29.187.58:10000/LouShi/user/userCollect.action"
//                ).addParams("user_id", "32").addParams("type", "3")
//                        .addParams("pid", Integer.toString(goodsBean.getId())).build().execute(new NormalCallBack() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        Log.e("goodadapter", Log.getStackTraceString(e));
//                    }
//
//                    @Override
//                    public void onResponse(ResponseJson responseJson) {
//                        if (responseJson.getState()) {
//
//                            if (isChecked) {
//
//                                //good.setCollectionNum(good.getCollectionNum() + 1);
//                                holder.tv_like_count.setText(Integer.toString(goodsBean.getCollectionNum()));
//                                //goodsList.remove(position);
//                            } else {
//
//                                //good.setCollectionNum(good.getCollectionNum() - 1);
//                                holder.tv_like_count.setText(Integer.toString(goodsBean.getCollectionNum()));
//
//                            }
//                            goodsBeanList.remove(position);
//
//                            notifyDataSetChanged();
//                        }
//
//                    }
//                });
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return goodsBeanList.size();
    }
    public static class CollectGoodViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView img_good;
        TextView tv_like_count;
        CheckBox btn_like;
        public CollectGoodViewHolder(View view) {
            super(view);
            tv_name = (TextView)view.findViewById(R.id.collect_good_item_tv_name);
            img_good=(ImageView)view.findViewById(R.id.collect_good_item_img_good);
            btn_like = (CheckBox)view.findViewById(R.id.collect_good_item_btn_like);
            tv_like_count=(TextView)view.findViewById(R.id.collect_good_item_tv_like_count);
        }


}
}

