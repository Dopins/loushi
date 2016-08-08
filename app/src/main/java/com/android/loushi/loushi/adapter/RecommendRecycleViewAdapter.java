package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;


import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.CarouselJson;
import com.android.loushi.loushi.jsonbean.RecommendJson;


import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.fragment.RecommendFragment;
import com.android.loushi.loushi.viewpager.CarouselViewPager;
import com.lzy.okhttputils.OkHttpUtils;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dopin on 2016/7/17.
 */
public class RecommendRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener itemClickListener;
    private List<CarouselJson.BodyBean> adList;
    private ArrayList<ImageView> views;
    private AdViewpagerAdapter adViewpagerAdapter;
    private int mHeaderCount = 1;//头部View个数
    //item类型

    public static final int ITEM_TYPE_SCENE = 0;
    public static final int ITEM_TYPE_TOPIC = 1;
    public static final int ITEM_TYPE_TIP = 2;
    public static final int ITEM_TYPE_HEADER = 3;
    private Context context;
    private List<RecommendJson.BodyBean> bodyBeanList = new ArrayList<>();

    public RecommendRecycleViewAdapter(Context context, List<RecommendJson.BodyBean> bodyBeanList) {
        this.context = context;
        this.bodyBeanList = bodyBeanList;
    }

    private int getBodyBeanListPosition(int position){
        return (position-1)/3;
    }
    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
       return getViewType(position);
    }
    private int getViewType(int position){
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        }else if (position % 3 == 1 && bodyBeanList.get(getBodyBeanListPosition(position)).getScene()!=null ) {
            return ITEM_TYPE_SCENE;
        } else if (position % 3 == 2 && bodyBeanList.get(getBodyBeanListPosition(position)).getStrategy()!=null ) {
            return ITEM_TYPE_TIP;
        } else if (position % 3 == 0 && position != 0 && bodyBeanList.get(getBodyBeanListPosition(position)).getTopic()!=null ) {
            return ITEM_TYPE_TOPIC;
        }
        return -1;
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPE_HEADER) {
                return new HeaderViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.ad_header, parent,
                        false));
            } else if (viewType == ITEM_TYPE_SCENE) {
                return new SceneViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.cardview_scene, parent,
                        false));
            } else if (viewType == ITEM_TYPE_TIP) {
                return new TipViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.cardview_tip, parent,
                        false));
            } else if (viewType == ITEM_TYPE_TOPIC){
                return new TopicViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.cardview_topic, parent,
                        false));
            }else { //返回一个不做任何处理的ViewHolder
                return new EmptyViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.cardview_topic, parent,
                        false));
            }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderViewHolder) {
                setImageView(((HeaderViewHolder) holder).mCarouselView);
            } else if (holder instanceof SceneViewHolder) {
                setSceneView(((SceneViewHolder) holder), getBodyBeanListPosition(position));
            } else if (holder instanceof TipViewHolder) {
                setTipView(((TipViewHolder) holder), getBodyBeanListPosition(position));
            } else if (holder instanceof TopicViewHolder) {
                setTopicView(((TopicViewHolder) holder), getBodyBeanListPosition(position));
            }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount + bodyBeanList.size()*3;
    }

    private void setTipView(TipViewHolder holder, int position) {

        RecommendJson.BodyBean body = bodyBeanList.get(position);
        if(body.getStrategy()==null) return;

        Picasso.with(context).load(body.getStrategy().getImgUrl()).fit().
                into(holder.image);

        holder.title.setText(body.getStrategy().getName());
        holder.detail.setText(body.getStrategy().getDigest());
        holder.numPrefer.setText(body.getStrategy().getCollectionNum() + "");
        holder.checkBox_prefer.setChecked(body.getStrategy().getCollected());
        holder.numWatch.setText(body.getStrategy().getBrowseNum()+"");
    }

    private void setTopicView(TopicViewHolder holder, int position) {

        RecommendJson.BodyBean body = bodyBeanList.get(position);
        if(body.getTopic()==null) return;

        Picasso.with(context).load(body.getTopic().getImgUrl()).fit().
                into(holder.image);
        holder.title.setText(body.getTopic().getName());
        holder.detail.setText(body.getTopic().getDigest());
        holder.numPrefer.setText(body.getTopic().getCollectionNum() + "");
        holder.checkBox_prefer.setChecked(body.getTopic().getCollected());
        holder.numWatch.setText(body.getTopic().getBrowseNum() + "");
    }

    private void setSceneView(SceneViewHolder holder, int position) {

        RecommendJson.BodyBean body = bodyBeanList.get(position);
        if(body.getScene()==null) return ;

        Picasso.with(context).load(body.getScene().getImgUrl()).fit().
                into(holder.image);

        holder.title.setText(body.getScene().getName());
        holder.detail.setText(body.getScene().getDigest());
        holder.numPrefer.setText(body.getScene().getCollectionNum() + "");
        holder.checkBox_prefer.setChecked(body.getScene().getCollected());
        holder.numWatch.setText(body.getScene().getBrowseNum() + "");

        if(position==0){
            holder.dateText.setText("今天");
        }else if(position==1){
            holder.dateText.setText("昨天");
        }else{
            String str_date=bodyBeanList.get(position).getRDate().substring(0,10);
            holder.dateText.setText(str_date);
        }

    }

    private void initAdViewPager(CarouselViewPager viewPager){
        ImageView view1 = (ImageView) LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        ImageView view2 = (ImageView) LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        ImageView view3 = (ImageView) LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        ImageView view4 = (ImageView) LayoutInflater.from(
                context).inflate(R.layout.ad_image, null);
        views = new ArrayList<ImageView>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        adViewpagerAdapter = new AdViewpagerAdapter(views);
        adViewpagerAdapter.setOnItemClickListener(new AdViewpagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), "点击adItem" + position, Toast.LENGTH_SHORT).show();
            }
        });
        viewPager.setAdapter(adViewpagerAdapter);

        if(adList==null){
            adList= new ArrayList<>();
            getAdList();
        }else{
            loadAdImage();
        }

    }
    private void setImageView(CarouselViewPager viewPager) {
        initAdViewPager(viewPager);
    }

    private void loadAdImage(){
        for(int i=0;i<adList.size()&&i<4;i++){
            String imgUrl=adList.get(i).getImgUrl();
            Picasso.with(views.get(i).getContext()).load(imgUrl).fit().
                    into(views.get(i));
        }
    }
    private void getAdList() {

        OkHttpUtils.post(BaseActivity.url + "base/carousel")
                // 请求方式和请求url
                .execute(new JsonCallback<CarouselJson>(CarouselJson.class) {
                    @Override
                    public void onResponse(boolean b, CarouselJson carouselJson, Request request, Response response) {
                        if (carouselJson.getState()) {
                            adList.addAll(carouselJson.getBody());
                            loadAdImage();
                        } else {
                            Log.d("error", carouselJson.getReturn_info());
                        }
                    }
                });
    }

    public class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView title;
        TextView detail;
        TextView numPrefer;
        TextView numWatch;
        TextView publishTime;
        CheckBox checkBox_prefer;
        LinearLayout btn_prefer;
        mViewHolder holder;
        public mViewHolder(View view){
            super(view);
            holder=this;
            image = (ImageView) view.findViewById(R.id.card_image);
            title = (TextView) view.findViewById(R.id.card_title);
            detail = (TextView) view.findViewById(R.id.card_detail);
            numPrefer = (TextView) view.findViewById(R.id.num_prefer);
            numWatch = (TextView) view.findViewById(R.id.num_watch);
            publishTime = (TextView) view.findViewById(R.id.publish_time);
            checkBox_prefer= (CheckBox) view.findViewById(R.id.checkbox_prefer);
            btn_prefer=(LinearLayout)view.findViewById(R.id.btn_prefer);
            btn_prefer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int type=getViewType(getPosition());
                    int pid;
                    int position=getBodyBeanListPosition(getPosition());
                    RecommendJson.BodyBean body = bodyBeanList.get(position);
                    if(type==ITEM_TYPE_SCENE){
                        pid=body.getScene().getId();
                        setPrefer(type+"",pid+"",holder ,position);
                    }else if(type==ITEM_TYPE_TOPIC){
                        pid=body.getTopic().getId();
                        setPrefer(type+"",pid+"",holder ,position);
                    }else if(type==ITEM_TYPE_TIP){
                        pid=body.getStrategy().getId();
                        setPrefer(type+"",pid+"",holder ,position);
                    }
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
    //场景holder
    class SceneViewHolder extends mViewHolder  {
        TextView dateText;
        public SceneViewHolder(View view) {
            super(view);
            dateText=(TextView)view.findViewById(R.id.date);
        }
    }

    class TipViewHolder extends mViewHolder  {

        public TipViewHolder(View view) {
            super(view);
        }
    }

    class TopicViewHolder extends mViewHolder  {
        public TopicViewHolder(View view) {
            super(view);
        }
    }

    //头部 ViewHolder
    class HeaderViewHolder extends RecyclerView.ViewHolder{
        private CarouselViewPager mCarouselView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mCarouselView = (CarouselViewPager) itemView.findViewById(R.id.ad_viewPager);
        }
    }
    //空viewHolder,用于处理推荐数据其中一项为空的情况，防止发生闪退
    class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }

    private void changeBodyList(int type,int position,int num,boolean is_collected){
        if(type==ITEM_TYPE_SCENE){
            bodyBeanList.get(position).getScene().setCollected(is_collected);
            bodyBeanList.get(position).getScene().setCollectionNum(num);
        }else if(type==ITEM_TYPE_TIP){
            bodyBeanList.get(position).getStrategy().setCollected(is_collected);
            bodyBeanList.get(position).getStrategy().setCollectionNum(num);
        }else if(type==ITEM_TYPE_TOPIC){
            bodyBeanList.get(position).getTopic().setCollected(is_collected);
            bodyBeanList.get(position).getTopic().setCollectionNum(num);
        }
    }
    private void changeSelectedState(String str_type,mViewHolder holder,int position){
        int type =Integer.parseInt(str_type);

        if (holder.checkBox_prefer.isChecked()) {
            int num = Integer.parseInt(holder.numPrefer.getText().toString()) - 1;
            holder.numPrefer.setText(Integer.toString(num));
            holder.checkBox_prefer.setChecked(false);

            changeBodyList(type,position,num,false);
        } else {
            int num = Integer.parseInt(holder.numPrefer.getText().toString()) + 1;
            holder.numPrefer.setText(Integer.toString(num));
            holder.checkBox_prefer.setChecked(true);

            changeBodyList(type, position, num, true);
        }
    }
    private void setPrefer(final String type, String pid,final mViewHolder holder,final int position) {

        OkHttpUtils.post(BaseActivity.url + "user/userCollect")
                // 请求方式和请求url
                .tag(this)
                .params("user_id", BaseActivity.user_id)
                .params("type", type)
                .params("pid", pid)
                .execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
                    @Override
                    public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
                        if (responseJson.getState()) {
                            changeSelectedState(type,holder,position);
                        } else {
                            Log.d("error", responseJson.getReturn_info());
                        }
                    }
                });
    }
}

