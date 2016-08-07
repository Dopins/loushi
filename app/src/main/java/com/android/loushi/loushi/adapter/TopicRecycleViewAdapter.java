package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TopicRecycleViewAdapter extends RecyclerView.Adapter<TopicRecycleViewAdapter.ViewHolder>{

    private static final int topicCount=8;
    private final int[] mTopicImageId={
            R.drawable.project_light,R.drawable.project_it,
            R.drawable.project_food,R.drawable.project_strange,
            R.drawable.project_old,R.drawable.project_star,
            R.drawable.project_store,R.drawable.project_cartoon};


    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public TopicRecycleViewAdapter(Context context){
        this.mContext=context;
    }


    @Override
    public TopicRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(mContext, R.layout.item_recyclerview_guide,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicRecycleViewAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(mTopicImageId[position]);
    }

    @Override
    public int getItemCount() {
        return this.topicCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.item_imageView_guide);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener!=null)
                mOnItemClickListener.onItemClick(v,getPosition());
        }
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }


}
