package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.jsonbean.TopicGroupJson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TopicRecycleViewAdapter extends RecyclerView.Adapter<TopicRecycleViewAdapter.ViewHolder>{

    private static final String TAG="TopicRvAdapter";

    public static final String[] IMAGETIPS_CN={"收纳储物仓","实用精品仓","装饰改造仓"};
    private static final String[] IMAGETIPS_EN={"Better storage","Practical products","Decoration&Renovation"};

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private List<TopicGroupJson.BodyBean> topicList;

    public TopicRecycleViewAdapter(Context context, List<TopicGroupJson.BodyBean> topicList){
        this.mContext=context;
        this.topicList=topicList;
    }

    @Override
    public TopicRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(mContext, R.layout.item_recyclerview_guide,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicRecycleViewAdapter.ViewHolder holder, int position) {
        TopicGroupJson.BodyBean topic=topicList.get(position);
        if(!TextUtils.isEmpty(topic.getImgUrl()))
            Picasso.with(mContext).load(topic.getImgUrl()).into(holder.imageView);
        holder.tipsCn.setText(topic.getName());
    }

    @Override
    public int getItemCount() {
        return this.topicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;
        public TextView tipsCn;
        public TextView tipsEn;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.item_imageView_guide);
            tipsCn=(TextView)itemView.findViewById(R.id.item_tips_cn);
            tipsEn=(TextView)itemView.findViewById(R.id.item_tips_en);
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
