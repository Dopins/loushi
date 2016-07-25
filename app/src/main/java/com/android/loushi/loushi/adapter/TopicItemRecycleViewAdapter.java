package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.Call;

/**
 * Created by binpeiluo on 2016/7/24 0024.
 */
public class TopicItemRecycleViewAdapter extends RecyclerView.Adapter<TopicItemRecycleViewAdapter.ViewHolder> {

    private Context mContext;
    private List<TopicJson.BodyBean> mTopicList;

    public TopicItemRecycleViewAdapter(Context context,List<TopicJson.BodyBean> topicList) {
        this.mContext = context;
        this.mTopicList=topicList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.cardview_topic, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TopicJson.BodyBean topic = mTopicList.get(position);
        holder.card_title.setText(topic.getName());
        Picasso.with(mContext)
                .load(topic.getImgUrl())
                .into(holder.card_image);
        holder.num_prefer.setText(Integer.toString(topic.getCollectionNum()));
//        holder.checkbox_prefer.setChecked(topic.isCollected());
//        holder.card_date.setText(topic.get);
        // 设置点赞响应
        //TODO
//        holder.mCheckBox_zan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
//                OkHttpUtils.post().url("http://119.29.187.58:10000/LouShi/user/userCollect.action"
//                ).addParams("user_id", user_id).addParams("type","1")
//                        .addParams("pid",Integer.toString(topic.getId())).build().execute(new NormalCallBack() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(ResponseJson responseJson) {
//                        if(responseJson.getState()){
//
//                            if (isChecked){
//                                Topicjson.BodyBean temp = topics.get(position);
//                                topics.get(position).setCollected(true);
//                                topics.get(position).setCollectionNum(temp.getCollectionNum() + 1);
//                                //temp.setCollectionNum(temp.getCollectionNum()+1);
//                                holder.mText_zan.setText(Integer.toString(temp.getCollectionNum()));
//                            } else{
//                                Topicjson.BodyBean temp = topics.get(position);
//                                topics.get(position).setCollected(false);
//                                topics.get(position).setCollectionNum(temp.getCollectionNum()-1);
//                                //temp.setCollectionNum(temp.getCollectionNum()-1);
//                                holder.mText_zan.setText(Integer.toString(temp.getCollectionNum()));
//                            }
//                            notifyDataSetChanged();
//                        }
//
//                    }
//                });
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mTopicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView card_image;
        private TextView card_title;
        private TextView card_date;
        private TextView card_detail;
        private CheckBox checkbox_prefer;
        private TextView num_prefer;
        private ImageView image_watch;
        private TextView num_watch;

        public ViewHolder(View itemView) {
            super(itemView);
            card_image = (ImageView) itemView.findViewById(R.id.card_image);
            card_title = (TextView) itemView.findViewById(R.id.card_title);
            card_date = (TextView) itemView.findViewById(R.id.date);
            card_detail = (TextView) itemView.findViewById(R.id.card_detail);
            checkbox_prefer = (CheckBox) itemView.findViewById(R.id.checkbox_prefer);
            num_prefer = (TextView) itemView.findViewById(R.id.num_prefer);
            image_watch = (ImageView) itemView.findViewById(R.id.image_watch);
            num_watch = (TextView) itemView.findViewById(R.id.num_watch);
        }
    }


}
