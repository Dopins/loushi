package com.android.loushi.loushi.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by binpeiluo on 2016/7/20 0020.
 * 已经将dp转成px
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int mSpacePx;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);

        int spanCount=1;
        RecyclerView.LayoutManager manager=parent.getLayoutManager();
        if(manager instanceof StaggeredGridLayoutManager){
            spanCount=((StaggeredGridLayoutManager) manager).getSpanCount();
        }else if(manager instanceof GridLayoutManager){
            spanCount=((GridLayoutManager) manager).getSpanCount();
        }

        if(parent.getChildPosition(view)>=spanCount){
            outRect.top=mSpacePx;
//            outRect.
        }
    }

    public SpaceItemDecoration(Context context, int spaceDip) {
        super();
        this.mSpacePx= (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,spaceDip,context.getResources().getDisplayMetrics());
    }
}
