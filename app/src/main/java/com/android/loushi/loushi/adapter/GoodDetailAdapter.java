package com.android.loushi.loushi.adapter;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.jsonbean.GoodsJson;
import com.squareup.picasso.Picasso;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2016/7/25.
 */
public class GoodDetailAdapter extends RecyclerView.Adapter<GoodDetailAdapter.GoodDetailViewHolder> {
    private Context context;
    private List<GoodsJson.BodyBean.ImagesBean> list;
    private ViewPager viewPager;
    private InnerViewPager vpAdapter;
    private Dialog dialog;
    public GoodDetailAdapter(Context context,List<GoodsJson.BodyBean.ImagesBean> list){
        this.context = context;
        this.list = list;
        initViewPager();
        initDialog();
    }

    private void initViewPager(){
        viewPager=new ViewPager(context);
        vpAdapter =new InnerViewPager();
        viewPager.setAdapter(vpAdapter);
    }

    private void initDialog(){

        dialog= new Dialog(context,R.style.Dialog_Fullscreen);
        dialog.setContentView(viewPager);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
    }

    @Override
    public GoodDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodDetailViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_good_detail_img, parent,
                false));
    }


    @Override
    public void onBindViewHolder(GoodDetailViewHolder holder, final int position) {

        Picasso.with(context).load(list.get(position).getUrl()).into(holder.img_good);
        holder.img_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBigImage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        vpAdapter.notifyDataSetChanged();
        return list.size();
    }
    public static class GoodDetailViewHolder extends RecyclerView.ViewHolder {
        ImageView img_good;
        public GoodDetailViewHolder(View view) {
            super(view);
            img_good = (ImageView)view.findViewById(R.id.img_good);
        }


    }
    private void showBigImage(int position){
        //ImageView img_big =new ImageView(context);
//        initViewPager();
//        initDialog();
        viewPager.setCurrentItem(position);
        dialog.show();
    }

    private class InnerViewPager extends PagerAdapter implements PhotoViewAttacher.OnPhotoTapListener{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView img_big = new PhotoView(context);
            img_big.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    ,ViewGroup.LayoutParams.MATCH_PARENT));
            Picasso.with(context).load(list.get(position).getUrl()).into(img_big);
            PhotoViewAttacher attacher = new PhotoViewAttacher(img_big);
            attacher.update();
            attacher.setOnPhotoTapListener(this);
            container.addView(img_big);
            return img_big;
        }

        @Override
        public void onPhotoTap(View view, float x, float y) {
            dialog.dismiss();
        }

        @Override
        public void onOutsidePhotoTap() {
            dialog.dismiss();
        }
    }
}
