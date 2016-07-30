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

        String[] temp = mComment.getCDate().replace("-","/").split("T");
        holder.textView_MessageDate.setText(CalulateDate(temp[0]));
        holder.textView_MessageTime.setText(CalulateTime(temp[1]));

//        Log.i("test","date,time=="+CalulateDate(time_cha)+","+CalulateTime(time_cha));


        //TODO
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    /*
    * 获取输入时间   如   22:37
    * */
    private String CalulateTime(String date) {
//        SimpleDateFormat dfs = new SimpleDateFormat("HH:mm");
//        Date d = new Date(date);
//        return dfs.format(d);
        String[] strs=date.split(":");
        return strs[0]+":"+strs[1];
    }

    /*
    * 通过输入当前时间判断距离时间
    * */
    private String CalulateDate(String date) {
        String result=date;
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy/MM/dd");
        Date begin = new Date(date);
        Date end=new Date(System.currentTimeMillis());
        Calendar cBegin=Calendar.getInstance();
        Calendar cEnd=Calendar.getInstance();
        cBegin.setTime(begin);
        cEnd.setTime(end);
        int beginDay=cBegin.get(Calendar.DAY_OF_YEAR);
        int beginMonth=cBegin.get(Calendar.MONTH);
        int beginYear=cBegin.get(Calendar.YEAR);
        int endDay=cEnd.get(Calendar.DAY_OF_YEAR);
        int endMonth=cEnd.get(Calendar.MONTH);
        int endYear=cEnd.get(Calendar.YEAR);

        if(endDay-beginDay==0)
            result="今天";
        else if(endDay-beginDay==1)
            result="昨天";
        else if(endDay-beginDay==2)
            result="前天";
        else if((endYear-beginYear==1)
                &&(endMonth-beginMonth==-11)
                &&(cEnd.get(Calendar.DAY_OF_MONTH)-cBegin.get(Calendar.DAY_OF_MONTH)==-30))
            result="昨天";
        return result;

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
