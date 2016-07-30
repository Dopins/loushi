package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import java.util.Date;
import java.util.List;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    private OnItemClickListener mOnItemClickListener;

    private Context mContext;
    private List<CommentJson.BodyBean> mCommentList;

    public CommentAdapter(Context mContext, List<CommentJson.BodyBean> mCommentList){
        this.mContext=mContext;
        this.mCommentList = mCommentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(mContext, R.layout.item_comment,null);
        ViewHolder holder=new ViewHolder(view);
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
        holder.textView_UserName.setText(mComment.getUserInfo().getNickname());
        holder.textView_userContont.setText(mComment.getContent());
        String[] t=mComment.getCDate().split(" ");

        //TODO
        holder.textView_MessageDate.setText(CalulateDate(t[0]));
//        holder.textView_MessageTime.setText(CalulateTime(t[1]));
//        holder.textView_MessageTime.setText("time--"+CalulateTime(t[1]));
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    /*
    * 获取输入时间   如   22:37
    * */
    private String CalulateTime(String date){
        SimpleDateFormat dfs = new SimpleDateFormat("HH:mm");
        Date begin = new Date();
        try {
            begin = dfs.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result=dfs.format(begin);
        return result;
    }

    /*
    * 通过输入当前时间判断距离时间
    * */
    private String CalulateDate(String date){
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = new Date();
        try {
            begin = dfs.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date end=null;
        try {
            end = dfs.parse(dfs.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(begin.getYear()==end.getYear()&&begin.getMonth()==end.getMonth()){
            int offset=end.getDay()-begin.getYear();
            if(offset==0)
                return "今天";
            else if(offset==1)
                return "昨天";
            else if(offset==2)
                return "前天";
        }
        return dfs.format(begin);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView_User;
        private TextView textView_UserName;
        private TextView textView_userContont;
        private TextView textView_MessageDate;
        private TextView textView_MessageTime;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView_User= (ImageView) itemView.findViewById(R.id.item_imageView_message);
            textView_UserName= (TextView) itemView.findViewById(R.id.item_textView_messageUserName);
            textView_userContont= (TextView) itemView.findViewById(R.id.item_textView_messageContent);
            textView_MessageDate= (TextView) itemView.findViewById(R.id.item_textView_messageDate);
            textView_MessageTime= (TextView) itemView.findViewById(R.id.item_textView_messageTime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener!=null)
                mOnItemClickListener.onItemClick(v,getPosition());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int postion);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
