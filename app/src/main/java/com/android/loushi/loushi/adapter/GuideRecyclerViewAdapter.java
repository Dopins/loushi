package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.json.SceneJson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dopin on 2016/7/24.
 */
public class GuideRecyclerViewAdapter extends RecyclerView.Adapter<GuideRecyclerViewAdapter.SceneViewHolder> {

    private OnItemClickListener itemClickListener;
    private Context context;
    private List<SceneJson.BodyBean> bodyBeanList;
    public GuideRecyclerViewAdapter(Context context, List<SceneJson.BodyBean> bodyBeanList) {
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
    public SceneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new SceneViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.cardview_tip, parent,
                    false));
    }

    @Override
    public void onBindViewHolder(SceneViewHolder holder, int position) {

        setSceneView( holder, position);
    }
    private void setSceneView(SceneViewHolder holder, int position) {

        SceneJson.BodyBean body = bodyBeanList.get(position);

        Picasso.with(context).load(body.getImgUrl()).fit().
                into(holder.image);

        holder.title.setText(body.getName());

        holder.numPrefer.setText(body.getCollectionNum()+"");
        holder.checkBox_prefer.setSelected(body.isCollected());
    }
    class SceneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView title;
        TextView detail;
        TextView numPrefer;
        TextView numWatch;
        TextView publishTime;
        CheckBox checkBox_prefer;

        public SceneViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.card_image);
            title = (TextView) view.findViewById(R.id.card_title);
            detail = (TextView) view.findViewById(R.id.card_detail);
            numPrefer = (TextView) view.findViewById(R.id.num_prefer);
            numWatch = (TextView) view.findViewById(R.id.num_watch);
            publishTime = (TextView) view.findViewById(R.id.publish_time);
            checkBox_prefer= (CheckBox) view.findViewById(R.id.checkbox_prefer);
            checkBox_prefer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "item" + getPosition() + " 点赞", Toast.LENGTH_SHORT).show();
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
