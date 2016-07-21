package com.android.loushi.loushi.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dopin on 2016/7/21.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int left;
    private int right;
    private int top;
    private int buttom;

    public SpacesItemDecoration(int left,int right,int top,int buttom) {
        this.left = left;
        this.right=right;
        this.top=top;
        this.buttom=buttom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = left;
        outRect.right = right;
        outRect.bottom = buttom;

        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildPosition(view) == 0)
            outRect.top = top;
    }
}
