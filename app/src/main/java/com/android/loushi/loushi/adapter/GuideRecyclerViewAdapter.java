package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.jsonbean.GuideJson;
import com.android.loushi.loushi.util.RecycleViewPreferSetter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dopin on 2016/7/24.
 */
public class GuideRecyclerViewAdapter extends RecyclerView.Adapter<GuideRecyclerViewAdapter.GuideViewHolder> {

    private OnItemClickListener itemClickListener;
    private Context context;
    private List<GuideJson.BodyBean> bodyBeanList;
    public GuideRecyclerViewAdapter(Context context, List<GuideJson.BodyBean> bodyBeanList) {
        this.context = context;
        this.bodyBeanList = bodyBeanList;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new GuideViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.cardview_topic, parent,
                    false));
    }

    @Override
    public void onBindViewHolder(GuideViewHolder holder, int position) {

        setGuideView( holder, position);
    }
    private void setGuideView(GuideViewHolder holder, int position) {

        GuideJson.BodyBean body = bodyBeanList.get(position);

        Picasso.with(context).load(body.getImgUrl()).fit().
                into(holder.image);

        holder.title.setText(body.getName());

        holder.numPrefer.setText(body.getCollectionNum()+"");
        holder.checkBox_prefer.setChecked(body.getCollected());
    }
    class GuideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView title;
        TextView detail;
        TextView numPrefer;
        TextView numWatch;
        TextView publishTime;
        CheckBox checkBox_prefer;
        LinearLayout btn_prefer;

        public GuideViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.card_image);
            title = (TextView) view.findViewById(R.id.card_title);
            detail = (TextView) view.findViewById(R.id.card_detail);
            numPrefer = (TextView) view.findViewById(R.id.num_prefer);
            numWatch = (TextView) view.findViewById(R.id.num_watch);
            publishTime = (TextView) view.findViewById(R.id.publish_time);
            checkBox_prefer= (CheckBox) view.findViewById(R.id.checkbox_prefer);
            btn_prefer=(LinearLayout)view.findViewById(R.id.btn_prefer);
            btn_prefer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   String type=bodyBeanList.get(getPosition()).getType()+"";

                    RecycleViewPreferSetter recycleViewPreferSetter=new RecycleViewPreferSetter();
                    recycleViewPreferSetter.setSelectedStateSetter(new RecycleViewPreferSetter.SelectedStateSetter() {
                        @Override
                        public void SetSelectedState(String num, boolean is_collected) {
                            numPrefer.setText(num);
                            checkBox_prefer.setChecked(is_collected);
                        }
                    });
                    recycleViewPreferSetter.setTopicPrefer(type, getPosition(), bodyBeanList);
                }
            });

            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
            if(itemClickListener!=null){
                itemClickListener.onItemClick(v,getPosition());
            }
        }
    }

    @Override
    public int getItemCount() {
        return bodyBeanList.size();
    }
}
