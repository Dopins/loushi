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
import com.android.loushi.loushi.jsonbean.CommentJson;
import com.android.loushi.loushi.jsonbean.UserMessageJson;
import com.android.loushi.loushi.util.DateUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private static final String DEFALUTNAME="路人甲";
    private static final String REPLYSTR="回复";

    private OnItemClickListener mOnItemClickListener;

    private Context mContext;
    private List<CommentJson.BodyBean> mCommentList;

    public CommentAdapter(Context mContext, List<CommentJson.BodyBean> mCommentList) {
        this.mContext = mContext;
        this.mCommentList = mCommentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_comment, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CommentJson.BodyBean mComment = mCommentList.get(position);
        Picasso.with(mContext).load(mComment.getUserInfo().getHeadImgUrl())
//                .resize(Type)
//                .transform(mTransformation)
                .fit()
                .into(holder.imageView_User);
        String userName=mComment.getUserInfo().getNickname();
        if (TextUtils.isEmpty(userName))
            userName=DEFALUTNAME;
        holder.textView_UserName.setText(userName);
        if(mComment.getReplyedInfo()!=null){
            String replyName=mComment.getReplyedInfo().getNickname();
            String content=TextUtils.isEmpty(replyName)?mComment.getContent():REPLYSTR+replyName+":"+mComment.getContent();
            holder.textView_userContont.setText(content);
        }else
            holder.textView_userContont.setText(mComment.getContent());

        holder.textView_MessageDate.setText(DateUtils.calulateDate(mComment.getCDate()));
        holder.textView_MessageTime.setText(DateUtils.calulateTime(mComment.getCDate()));

    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView_User;
        private TextView textView_UserName;
        private TextView textView_userContont;
        private TextView textView_MessageDate;
        private TextView textView_MessageTime;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView_User = (ImageView) itemView.findViewById(R.id.item_imageView_message);
            textView_UserName = (TextView) itemView.findViewById(R.id.item_textView_messageUserName);
            textView_userContont = (TextView) itemView.findViewById(R.id.item_textView_messageContent);
            textView_MessageDate = (TextView) itemView.findViewById(R.id.item_textView_messageDate);
            textView_MessageTime = (TextView) itemView.findViewById(R.id.item_textView_messageTime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int postion);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
