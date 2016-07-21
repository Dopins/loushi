package com.android.loushi.loushi.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * Created by binpeiluo on 2016/7/20 0020.
 */
public class SmoothScrollView extends ScrollView{

    private int mDownY;
    private int mTouchSlop;

    public SmoothScrollView(Context context) {
        super(context);
        initSlop(context);
    }

    public SmoothScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSlop(context);
    }

    public SmoothScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSlop(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SmoothScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initSlop(context);
    }

    private void initSlop(Context context){
        mTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY= (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY= (int) ev.getRawY();
                if((Math.abs(moveY-mDownY))>mTouchSlop)
                    return true;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
