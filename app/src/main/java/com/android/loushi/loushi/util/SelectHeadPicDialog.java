package com.android.loushi.loushi.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.android.loushi.loushi.R;

/**
 * Created by Administrator on 2016/8/9.
 */
public class SelectHeadPicDialog extends Dialog {

    private TextView tv_call_camera;
    private TextView tv_call_gallery;
    private TextView tv_cancel;

    public SelectHeadPicDialog(Context context,View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView =  inflater.inflate(R.layout.popu_select_photo, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        tv_call_camera = (TextView) dialogView.findViewById(R.id.tv_call_camera);
        tv_call_gallery = (TextView) dialogView.findViewById(R.id.tv_call_gallery);
        tv_cancel = (TextView) dialogView.findViewById(R.id.tv_cencel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        tv_call_gallery.setOnClickListener(itemsOnClick);
        tv_call_camera.setOnClickListener(itemsOnClick);
        setContentView(dialogView);

    }
}
