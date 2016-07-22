package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TipsRecycleViewAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public TipsRecycleViewAdapter(Context context){
        this.mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(mContext, R.layout.cardview_tip,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageViewCard;
        private TextView textViewTitle;
        private TextView textViewDate;
        private ImageView imageViewPrefer;
        private TextView textViewPreferCount;
        private ImageView imageViewWatch;
        private TextView textViewWatchCount;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewCard= (ImageView) itemView.findViewById(R.id.card_image);
            textViewTitle= (TextView) itemView.findViewById(R.id.card_title);
            textViewDate= (TextView) itemView.findViewById(R.id.date);
            textViewDate= (TextView) itemView.findViewById(R.id.num_prefer);
            imageViewPrefer= (ImageView) itemView.findViewById(R.id.image_prefer);
            textViewPreferCount= (TextView) itemView.findViewById(R.id.num_prefer);
            imageViewWatch= (ImageView) itemView.findViewById(R.id.image_watch);
            textViewWatchCount= (TextView) itemView.findViewById(R.id.num_watch);
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
        void onItemClick(View v, int position);
    }


}
