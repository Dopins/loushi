package com.android.loushi.loushi.util;

/**
 * Created by Administrator on 2016/7/19.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class UnderLineEditText extends EditText {

    private Paint linePaint;
    private int paperColor;

    public UnderLineEditText(Context context, AttributeSet paramAttributeSet) {
        super(context, paramAttributeSet);
        // TODO Auto-generated constructor stub
        this.linePaint = new Paint();
        linePaint.setColor(0x77D9D8DE);//设置下划线颜色
    }

    protected void onDraw(Canvas paramCanvas) {
        paramCanvas.drawColor(this.paperColor); //设置背景色
        int i = getLineCount();
        int j = getHeight();
        int k = getLineHeight();
        int m = 1 + j / k;
        if (i < m) i = m;
        int n = getCompoundPaddingTop();

        for (int i2 = 0; ; i2++) {
            if (i2 >= i) {
                super.onDraw(paramCanvas);
                paramCanvas.restore();
                return;
            }

            n += k;
            paramCanvas.drawLine(0.0F, n, getRight(), n, this.linePaint);
            paramCanvas.save();
        }
    }

}