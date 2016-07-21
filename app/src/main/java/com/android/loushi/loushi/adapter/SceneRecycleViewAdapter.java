package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.SceneCallBack;
import com.android.loushi.loushi.json.SceneJson;
import com.android.loushi.loushi.viewpager.CarouselViewPager;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by dopin on 2016/7/17.
 */
public class SceneRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    public OnItemClickListener itemClickListener;
    private int mHeaderCount=1;//头部View个数
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_SCENE = 1;
    public static final int ITEM_TYPE_TIP=2;
    public static final int ITEM_TYPE_TOPIC=3;
    private Context context;
    private List<SceneJson.BodyBean> bodyBeanList =new ArrayList<SceneJson.BodyBean>();
    public SceneRecycleViewAdapter(Context context, List<SceneJson.BodyBean> bodyBeanList ){
        this.context=context;
        this.bodyBeanList=bodyBeanList;

    }
    //内容长度
    public int getContentItemCount(){
        return bodyBeanList.size();
    }
    //判断当前item类型
    @Override
    public int getItemViewType(int position) {

        if (position==0) {
//头部View
            return ITEM_TYPE_HEADER;
        } if(position%3==1){
//内容View
            return ITEM_TYPE_SCENE;
        }else if(position%3==2){
            return ITEM_TYPE_TIP;
        }else if(position%3==0&&position!=0){
            return ITEM_TYPE_TOPIC;
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType ==ITEM_TYPE_HEADER) {
          return new HeaderViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.ad_header, parent,
                    false));
        } else if (viewType == ITEM_TYPE_SCENE) {
           return new SceneViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.cardview_scene, parent,
                    false));
        }else if(viewType==ITEM_TYPE_TIP){
            return new TipViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.cardview_tip, parent,
                    false));
        }else{
            return new TopicViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.cardview_topic, parent,
                    false));
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            setImageView(((HeaderViewHolder) holder).mCarouselView);
        }else if(holder instanceof SceneViewHolder){
            setSceneView(((SceneViewHolder) holder),position);
        }else if(holder instanceof TipViewHolder){
            setTipView(((TipViewHolder) holder), position);
        }else{
            setTopicView(((TopicViewHolder) holder),position);
        }
    }

    @Override
    public int getItemCount()
    {
        return mHeaderCount + getContentItemCount();
    }

    private void setTipView(TipViewHolder holder,int position){

            SceneJson.BodyBean body= bodyBeanList.get(position-1);

            Picasso.with(context).load(body.getImgUrl()).fit().
                    into(holder.image);

            holder.title.setText(body.getName());

    }
    private void setTopicView(TopicViewHolder holder,int position){

            SceneJson.BodyBean body= bodyBeanList.get(position-1);

            Picasso.with(context).load(body.getImgUrl()).fit().
                    into(holder.image);

            holder.title.setText(body.getName());

    }
    private void setSceneView(SceneViewHolder holder,int position){

            SceneJson.BodyBean body= bodyBeanList.get(position-1);

            Picasso.with(context).load(body.getImgUrl()).fit().
                    into(holder.image);

            holder.title.setText(body.getName());
    }
    private void setImageView(CarouselViewPager viewPager){
        ImageView view1 = (ImageView)LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        ImageView view2 = (ImageView) LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        ImageView view3 = (ImageView) LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        ImageView view4 = (ImageView) LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        ArrayList<ImageView> views = new ArrayList<ImageView>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        //List<SceneJson.BodyBean> adList=getAdList();

//        for(int i=0;i<4;i++){
//            String imgUrl=adList.get(i).getImgUrl();
//            Picasso.with(views.get(i).getContext()).load(imgUrl).fit().
//                    into(views.get(i));
//        }

        AdViewpagerAdapter adViewpagerAdapter=new AdViewpagerAdapter(views);
        viewPager.setAdapter(adViewpagerAdapter);

    }
    private List<SceneJson.BodyBean> getAdList(){
        final List<SceneJson.BodyBean>  adList = new ArrayList<SceneJson.BodyBean>();
        OkHttpUtils.post().url("http://119.29.187.58:10000/LouShi/base/scene.action")
                .addParams("user_id", "0").addParams("scene_group_id", Integer.toString(1))
                .addParams("recommended", "true")
                .addParams("skip", Integer.toString(0))
                .addParams("take", Integer.toString(4)).build().execute(new SceneCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                e.printStackTrace();
                Log.d("TAG3", Log.getStackTraceString(e));
            }

            @Override
            public void onResponse(SceneJson sceneJson) {
                if (sceneJson.isState()) {
                    Log.d("TAG3", sceneJson.getBody().size()+"");
                    adList.addAll(sceneJson.getBody());
                }
            }
        });

        return adList;
    }
    //场景holder
    class SceneViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView title;
        TextView detail;
        TextView numPrefer;
        TextView numWatch;
        TextView publishTime;
        public SceneViewHolder(View view)
        {
            super(view);
            image=(ImageView)view.findViewById(R.id.card_image);
            title = (TextView) view.findViewById(R.id.card_title);
            detail = (TextView) view.findViewById(R.id.card_detail);
            numPrefer = (TextView) view.findViewById(R.id.num_prefer);
            numWatch = (TextView) view.findViewById(R.id.num_watch);
            publishTime = (TextView) view.findViewById(R.id.publish_time);
        }
    }
    class TipViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView title;
        TextView detail;
        TextView numPrefer;
        TextView numWatch;
        TextView publishTime;
        public TipViewHolder(View view)
        {
            super(view);
            image=(ImageView)view.findViewById(R.id.card_image);
            title = (TextView) view.findViewById(R.id.card_title);
            detail = (TextView) view.findViewById(R.id.card_detail);
            numPrefer = (TextView) view.findViewById(R.id.num_prefer);
            numWatch = (TextView) view.findViewById(R.id.num_watch);
            publishTime = (TextView) view.findViewById(R.id.publish_time);
        }
    }
    class TopicViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView title;
        TextView detail;
        TextView numPrefer;
        TextView numWatch;
        TextView publishTime;
        public TopicViewHolder(View view)
        {
            super(view);
            image=(ImageView)view.findViewById(R.id.card_image);
            title = (TextView) view.findViewById(R.id.card_title);
            detail = (TextView) view.findViewById(R.id.card_detail);
            numPrefer = (TextView) view.findViewById(R.id.num_prefer);
            numWatch = (TextView) view.findViewById(R.id.num_watch);
            publishTime = (TextView) view.findViewById(R.id.publish_time);
        }
    }
    //头部 ViewHolder
     class HeaderViewHolder extends RecyclerView.ViewHolder {
        private CarouselViewPager mCarouselView;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            mCarouselView=(CarouselViewPager)itemView.findViewById(R.id.ad_viewPager);
        }
    }
}

