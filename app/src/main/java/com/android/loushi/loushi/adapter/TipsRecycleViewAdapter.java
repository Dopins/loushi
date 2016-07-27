package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import com.android.loushi.loushi.jsonbean.StrategyJson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class TipsRecycleViewAdapter extends RecyclerView.Adapter<TipsRecycleViewAdapter.ViewHolder>{

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private List<StrategyJson.BodyBean> mTipsList;

    public TipsRecycleViewAdapter(Context context,List<StrategyJson.BodyBean> tipsList){
        this.mContext=context;
        this.mTipsList=tipsList;
    }

    @Override
    public TipsRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(mContext, R.layout.cardview_tip,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TipsRecycleViewAdapter.ViewHolder holder, int position) {
        final StrategyJson.BodyBean strategy = mTipsList.get(position);
        holder.textViewTitle.setText(strategy.getName());
        String url = strategy.getImgUrl();
        if(url.indexOf("|||")>=0)
            url = url.substring(0, url.indexOf("|||"));
        Picasso.with(mContext)
                .load(url)
//                .transform(new Transformation() {
//                    @Override
//                    public Bitmap transform(Bitmap bitmap) {
//                        final float scale = mContext.getResources().getDisplayMetrics().density;
//                        float scaleX = ((float) width) / bitmap.getWidth();
//                        float scaleY = ((float) 180*scale+0.5f) / bitmap.getHeight();
//                        Matrix matrix = new Matrix();
//                        matrix.postScale(scaleX, scaleY);
//
//                        Bitmap temp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//
//                        if (temp != bitmap)
//                            bitmap.recycle();
//
//                        return temp;
//                    }
//
//                    @Override
//                    public String key() {
//                        return "scale";
//                    }
//                })
                .into(holder.imageViewCard);
        holder.textViewPreferCount.setText(Integer.toString(strategy.getCollectionNum()));
        holder.checkBoxPrefer.setChecked(strategy.getCollected());
    }

    @Override
    public int getItemCount() {
        return mTipsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageViewCard;
        private TextView textViewTitle;
        private TextView textViewDate;
        private CheckBox checkBoxPrefer;
        private TextView textViewPreferCount;
        private ImageView imageViewWatch;
        private TextView textViewWatchCount;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewCard= (ImageView) itemView.findViewById(R.id.card_image);
            textViewTitle= (TextView) itemView.findViewById(R.id.card_title);
            textViewDate= (TextView) itemView.findViewById(R.id.date);
            textViewDate= (TextView) itemView.findViewById(R.id.num_prefer);
            checkBoxPrefer= (CheckBox) itemView.findViewById(R.id.checkbox_prefer);
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
