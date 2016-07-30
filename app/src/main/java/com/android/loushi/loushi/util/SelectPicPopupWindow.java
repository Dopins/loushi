package com.android.loushi.loushi.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.loushi.loushi.R;

/**
 * Created by Administrator on 2016/4/8.
 * 选择照片弹出框
 */
public class SelectPicPopupWindow extends PopupWindow {
    private View MenuView;
    private TextView tv_call_camera;
    private TextView tv_call_gallery;
    private TextView tv_cancel;

    public SelectPicPopupWindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MenuView = inflater.inflate(R.layout.popu_select_photo, null);
        tv_call_camera = (TextView) MenuView.findViewById(R.id.tv_call_camera);
        tv_call_gallery = (TextView) MenuView.findViewById(R.id.tv_call_gallery);
        tv_cancel = (TextView) MenuView.findViewById(R.id.tv_cencel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        tv_call_gallery.setOnClickListener(itemsOnClick);
        tv_call_camera.setOnClickListener(itemsOnClick);
        this.setContentView(MenuView);
        this.setWidth(LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        MenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = MenuView.findViewById(R.id.popu_select_ll).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });


    }
}
