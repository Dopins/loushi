package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.StrategyJson;
import com.android.loushi.loushi.jsonbean.TopicJson;
import com.android.loushi.loushi.ui.activity.MainActivity;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.UrlConstant;
import com.lzy.okhttputils.OkHttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binpeiluo on 2016/7/24 0024.
 */
public class TopicItemAdapter extends RecyclerView.Adapter<TopicItemAdapter.ViewHolder> {

    private static final String TAG = "TopicItemRVAdapter";

    public enum AdapterType {
        HOLDER, TOPIC, TIPS;
    }

    private AdapterType mAdapterType;

    private OnItemClickListener mOnItemClickListener;

    private Context mContext;
    private List<TopicJson.BodyBean> mTopicList;
    private List<StrategyJson.BodyBean> mTipsList;

    public TopicItemAdapter(Context context, List dataList, AdapterType type) {
        this.mContext = context;
        this.mAdapterType = type;
        if (AdapterType.TOPIC == type)
            this.mTopicList = (List<TopicJson.BodyBean>) dataList;
        else
            this.mTipsList = (List<StrategyJson.BodyBean>) dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.cardview_topic, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mAdapterType == AdapterType.TOPIC)
            bindTopicViewHolder(holder, position);
        else
            bindTipsViewHolder(holder, position);
    }

    /**
     * 为tipsadapter类型设置viewholder
     *
     * @param holder
     * @param position
     */
    private void bindTipsViewHolder(final ViewHolder holder, int position) {
        final StrategyJson.BodyBean tips = mTipsList.get(position);
        String[] urls = tips.getImgUrl().split("\\|\\|\\|");
        String urlImg = urls[0];
//        String urlClick=urls[1];
//        Log.i(TAG,"urlImg=="+urlImg+",urlClick=="+urlClick);

        //TODO 两个url的使用
        holderInitView(holder, tips, urlImg);
    }

    private void holderInitView(final ViewHolder holder, final StrategyJson.BodyBean tips, String urlImg) {
        Picasso.with(mContext)
                .load(urlImg)
                .into(holder.card_image);
        holder.num_prefer.setText(Integer.toString(tips.getCollectionNum()));
        holder.checkbox_prefer.setChecked(tips.getCollected());
//        holder.card_date.setText(tips.getWDate());
        holder.num_prefer.setText(tips.getCollectionNum() + "");
        holder.num_watch.setText(tips.getBrowseNum() + "");
        //TODO 没提供标题
        holder.card_detail.setText(tips.getName());
        // 设置点赞响应
        holder.btn_prefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean isChecked = !holder.checkbox_prefer.isChecked();
                OkHttpUtils.post(UrlConstant.USERCOLLECTURL)
                        .tag(this)
                        .params(KeyConstant.USER_ID, MainActivity.user_id)
                        .params(KeyConstant.TYPE, AdapterType.TIPS.ordinal() + "")
                        .params(KeyConstant.PID, tips.getId() + "")
                        .execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
                            @Override
                            public void onResponse(boolean isFromCache, ResponseJson responseJson, Request request, @Nullable Response response) {
                                if (responseJson.getState()) {
                                    if (isChecked) {
                                        holder.num_prefer.setText(Integer.parseInt(holder.num_prefer.getText() + "") + 1 + "");
                                    } else {
                                        holder.num_prefer.setText(Integer.parseInt(holder.num_prefer.getText() + "") - 1 + "");
                                    }
                                    holder.checkbox_prefer.setChecked(isChecked);
                                } else {
                                    Toast.makeText(mContext, "出了点小问题，请重试..." + responseJson.getReturn_info(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }


    /**
     * 为topicadapter类型设置viewholder
     *
     * @param holder
     * @param position
     */
    private void bindTopicViewHolder(final ViewHolder holder, int position) {
        final TopicJson.BodyBean topic = mTopicList.get(position);
        holderInitView(holder, topic);
    }

    private void holderInitView(final ViewHolder holder, final TopicJson.BodyBean topic) {
        Picasso.with(mContext)
                .load(topic.getImgUrl())
                .into(holder.card_image);
        holder.num_prefer.setText(Integer.toString(topic.getCollectionNum()));
        holder.checkbox_prefer.setChecked(topic.getCollected());
//        holder.card_date.setText(topic.getWDate());
        holder.num_prefer.setText(topic.getCollectionNum() + "");
        holder.num_watch.setText(topic.getBrowseNum() + "");
//        holder.card_title.setText(topic.getTopicGroup().getName());
        holder.card_title.setText(topic.getName());
        holder.card_detail.setText(topic.getDigest());
        // 设置点赞响应
        //TODO   抽取方法
        holder.btn_prefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean isChecked = !holder.checkbox_prefer.isChecked();
                OkHttpUtils.post(UrlConstant.USERCOLLECTURL)
                        .tag(this)
                        .params(KeyConstant.USER_ID, MainActivity.user_id)
                        .params(KeyConstant.TYPE, AdapterType.TOPIC.ordinal() + "")
                        .params(KeyConstant.PID, topic.getId() + "")
                        .execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
                            @Override
                            public void onResponse(boolean isFromCache, ResponseJson responseJson, Request request, @Nullable Response response) {
                                if (responseJson.getState()) {
                                    if (isChecked) {
                                        holder.num_prefer.setText(Integer.parseInt(holder.num_prefer.getText() + "") + 1 + "");
                                    } else {
                                        holder.num_prefer.setText(Integer.parseInt(holder.num_prefer.getText() + "") - 1 + "");
                                    }
                                    holder.checkbox_prefer.setChecked(isChecked);
                                } else {
                                    Toast.makeText(mContext, "出了点小问题，请重试" + responseJson.getReturn_info(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mAdapterType == AdapterType.TOPIC)
            return mTopicList.size();
        else
            return mTipsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView card_image;
        private TextView card_title;
        private TextView card_date;
        private TextView card_detail;
        private CheckBox checkbox_prefer;
        private TextView num_prefer;
        private ImageView image_watch;
        private TextView num_watch;
        private LinearLayout btn_prefer;

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
            btn_prefer = (LinearLayout) itemView.findViewById(R.id.btn_prefer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(v, getPosition());
        }
    }


    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


}
