package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.jsonbean.GoodsJson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class GoodDetailAdapter extends RecyclerView.Adapter<GoodDetailAdapter.GoodDetailViewHolder> {
    private Context context;
    private List<GoodsJson.BodyBean.ImagesBean> list;
    public GoodDetailAdapter(Context context,List<GoodsJson.BodyBean.ImagesBean> list){
        this.context = context;
        this.list = list;

    }
    @Override
    public GoodDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodDetailViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_good_detail_img, parent,
                false));
    }


    @Override
    public void onBindViewHolder(GoodDetailViewHolder holder, int position) {

        Picasso.with(context).load(list.get(position).getUrl()).into(holder.img_good);
    }

    @Override
    public int getItemCount() {

        return list.size();
        //return 5;
    }
    public static class GoodDetailViewHolder extends RecyclerView.ViewHolder {
        ImageView img_good;
        public GoodDetailViewHolder(View view) {
            super(view);
            img_good = (ImageView)view.findViewById(R.id.img_good);
        }


    }
}
