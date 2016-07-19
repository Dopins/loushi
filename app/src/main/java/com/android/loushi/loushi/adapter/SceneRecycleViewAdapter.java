package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dopin on 2016/7/17.
 */
public class SceneRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private int mHeaderCount=1;//头部View个数
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    private Context context;
    private List<String> data;
    public SceneRecycleViewAdapter(Context context, List<String> data){
        this.context=context;
        this.data=data;
    }
    //内容长度
    public int getContentItemCount(){
        return data.size();
    }
    //判断当前item是否是HeadView
    @Override
    public int getItemViewType(int position) {
        if (mHeaderCount != 0 && position < mHeaderCount) {
//头部View
            return ITEM_TYPE_HEADER;
        } else {
//内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType ==ITEM_TYPE_HEADER) {
          return new HeaderViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.ad_header, parent,
                    false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
           return new ContentViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.scene_cardview, parent,
                    false));
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            initImageView(((HeaderViewHolder) holder).vp);
        }else{
            ((ContentViewHolder)holder).tv.setText(data.get(position-1));
        }
    }

    @Override
    public int getItemCount()
    {
        return mHeaderCount + getContentItemCount();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        public ContentViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.card_title);
        }
    }
    private void initImageView(ViewPager viewPager){
        //初始化viewPager的内容
        ImageView view1 = (ImageView)LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        ImageView view2 = (ImageView) LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        ImageView view3 = (ImageView) LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);

        view1.setImageResource(R.drawable.ad1);
        view2.setImageResource(R.drawable.ad2);
        view3.setImageResource(R.drawable.ad3);

        ArrayList<ImageView> views = new ArrayList<ImageView>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPager.setAdapter(new AdViewpagerAdapter(views));

    }
    //头部 ViewHolder
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        ViewPager vp;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            vp=(ViewPager)itemView.findViewById(R.id.ad_viewPager);
        }
    }
}

