package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import com.android.loushi.loushi.jsonbean.UserMessageJson;
import com.android.loushi.loushi.util.CircleImageTransformation;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by binpeiluo on 2016/7/21 0021.
 */
public class MyMessageAdapter extends RecyclerView.Adapter<MyMessageAdapter.ViewHolder>{

    private OnItemClickListener mOnItemClickListener;
    private CircleImageTransformation mTransformation;

    private Context mContext;
    private List<UserMessageJson.BodyBean> myMessageList;

    public MyMessageAdapter(Context mContext,List<UserMessageJson.BodyBean> myMessageList){
        this.mContext=mContext;
        this.myMessageList=myMessageList;
        this.mTransformation=new CircleImageTransformation();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(mContext, R.layout.item_newmessage,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        UserMessageJson.BodyBean myMessage = myMessageList.get(position);

        //TODO 圆形复用问题
        Picasso.with(mContext).load(myMessage.getComment().getUserInfo().getHeadImgUrl())
//                .resize(Type)
//                .transform(mTransformation)
                .fit()
                .into(holder.imageView_User);
        holder.textView_UserName.setText(myMessage.getComment().getUserInfo().getNickname());
        holder.textView_userContont.setText(myMessage.getComment().getContent());
        String  time_cha = myMessage.getComment().getCDate().replace('T',' ');
        holder.textView_MessageDate.setText(CalulateDate(time_cha));
        holder.textView_MessageTime.setText(CalulateTime(time_cha));
    }

    @Override
    public int getItemCount() {
        return myMessageList.size();
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
        void onItemClick(View v,int postion);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
