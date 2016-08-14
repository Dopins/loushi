package com.android.loushi.loushi.util;

/**
 * Created by Administrator on 2016/8/4.
 */
/*
 * Copyright (C) 2016 Jared Rummler <jared.rummler@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


/**
 * A spinner that shows a {@link PopupWindow} under the view when clicked.
 */
public class MaterialSpinner extends TextView {

    private OnNothingSelectedListener onNothingSelectedListener;
    private OnItemSelectedListener onItemSelectedListener;
    private MaterialSpinnerBaseAdapter adapter;
    private PopupWindow popupWindow;
    private ListView listView;
    private Drawable arrowDrawable;
    private boolean hideArrow;
    private boolean nothingSelected;
    private int popupWindowMaxHeight;
    private int popupWindowHeight;
    private int selectedIndex;
    private int backgroundColor;
    private int arrowColor;
    private int arrowColorDisabled;
    private int textColor;
    private int numberOfItems;

    private Paint linePaint;
    private int paperColor;

    public MaterialSpinner(Context context) {
        super(context);
        init(context, null);
    }

    public MaterialSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        // TODO Auto-generated constructor stub
        this.linePaint = new Paint();
        linePaint.setStrokeWidth(3);
        linePaint.setColor(0x77D9D8DE);//设置下划线颜色
    }

    protected void onDraw(Canvas paramCanvas) {
        paramCanvas.drawColor(this.paperColor); //设置背景色

        int j = getHeight();
        int n = getCompoundPaddingTop();

        paramCanvas.drawLine(0.0F, j*5/6, getRight(), j*5/6, this.linePaint);
        paramCanvas.save();

        super.onDraw(paramCanvas);
        paramCanvas.restore();
    }


    public MaterialSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MaterialSpinner);
        int defaultColor = getTextColors().getDefaultColor();
        boolean rtl = Utils.isRtl(context);

        try {
            backgroundColor = ta.getColor(R.styleable.MaterialSpinner_ms_background_color, Color.WHITE);
            textColor = ta.getColor(R.styleable.MaterialSpinner_ms_text_color, defaultColor);
            arrowColor = ta.getColor(R.styleable.MaterialSpinner_ms_arrow_tint, textColor);
            hideArrow = ta.getBoolean(R.styleable.MaterialSpinner_ms_hide_arrow, false);
            popupWindowMaxHeight = (int) ta.getDimension(R.styleable.MaterialSpinner_ms_dropdown_max_height, 0f);
            popupWindowHeight = ta.getLayoutDimension(R.styleable.MaterialSpinner_ms_dropdown_height,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            arrowColorDisabled = Utils.lighter(arrowColor, 0.8f);
        } finally {
            ta.recycle();
        }
        Log.i("mytest","popupWindowMaxHeight=="+popupWindowMaxHeight);

        Resources resources = getResources();
        int left, right, bottom, top;
        left = right = bottom = top = resources.getDimensionPixelSize(R.dimen.ms__padding_top);
        right = resources.getDimensionPixelSize(R.dimen.ms__padding_left);
        if (rtl) {
            right = resources.getDimensionPixelSize(R.dimen.ms__padding_left);
        } else {
            left = resources.getDimensionPixelSize(R.dimen.ms__padding_left);
        }

        setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        setClickable(true);
        setPadding(left, top, right, bottom);
        setBackgroundResource(R.drawable.ms__selector);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && rtl) {
            setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setTextDirection(View.TEXT_DIRECTION_RTL);
        }

        if (!hideArrow) {
            arrowDrawable = Utils.getDrawable(context, R.drawable.ms__arrow).mutate();
            arrowDrawable.setColorFilter(arrowColor, PorterDuff.Mode.SRC_IN);
            if (rtl) {
                setCompoundDrawablesWithIntrinsicBounds(arrowDrawable, null, null, null);
            } else {
                arrowDrawable.setBounds(10,0,50,50);
                setCompoundDrawables(null, null, arrowDrawable, null);
            }
        }

        listView = new ListView(context);
        listView.setId(getId());
        listView.setDivider(null);
        listView.setItemsCanFocus(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= selectedIndex && position < adapter.getCount()) {
                    position++;
                }
                selectedIndex = position;
                nothingSelected = false;
                Object item = adapter.get(position);
                adapter.notifyItemSelected(position);
                setText(item.toString());
                collapse();
                if (onItemSelectedListener != null) {
                    //noinspection unchecked
                    onItemSelectedListener.onItemSelected(MaterialSpinner.this, position, id, item);
                }
            }
        });

        popupWindow = new PopupWindow(context);
        popupWindow.setContentView(listView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(16);
            popupWindow.setBackgroundDrawable(Utils.getDrawable(context, R.drawable.ms__drawable));
        } else {
            popupWindow.setBackgroundDrawable(Utils.getDrawable(context, R.drawable.ms__drop_down_shadow));
        }

        if (backgroundColor != Color.WHITE) { // default color is white
            setBackgroundColor(backgroundColor);
        }
        if (textColor != defaultColor) {
            setTextColor(textColor);
        }

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                if (nothingSelected && onNothingSelectedListener != null) {
                    onNothingSelectedListener.onNothingSelected(MaterialSpinner.this);
                }
                if (!hideArrow) {
                    animateArrow(false);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        popupWindow.setWidth(MeasureSpec.getSize(widthMeasureSpec));
        popupWindow.setHeight(calculatePopupWindowHeight());
//        popupWindow.setHeight(popupWindowMaxHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isEnabled() && isClickable()) {
                if (!popupWindow.isShowing()) {
                    expand();
                } else {
                    collapse();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setBackgroundColor(int color) {
        backgroundColor = color;
        Drawable background = getBackground();
        if (background instanceof StateListDrawable) { // pre-L
            try {
                Method getStateDrawable = StateListDrawable.class.getDeclaredMethod("getStateDrawable", int.class);
                if (!getStateDrawable.isAccessible()) getStateDrawable.setAccessible(true);
                int[] colors = {Utils.darker(color, 0.85f), color};
                for (int i = 0; i < colors.length; i++) {
                    ColorDrawable drawable = (ColorDrawable) getStateDrawable.invoke(background, i);
                    drawable.setColor(colors[i]);
                }
            } catch (Exception e) {
                Log.e("MaterialSpinner", "Error setting background color", e);
            }
        } else if (background != null) { // 21+ (RippleDrawable)
            background.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        popupWindow.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void setTextColor(int color) {
        textColor = color;
        super.setTextColor(color);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("state", super.onSaveInstanceState());
        bundle.putInt("selected_index", selectedIndex);
        if (popupWindow != null) {
            bundle.putBoolean("is_popup_showing", popupWindow.isShowing());
            collapse();
        } else {
            bundle.putBoolean("is_popup_showing", false);
        }
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable savedState) {
        if (savedState instanceof Bundle) {
            Bundle bundle = (Bundle) savedState;
            selectedIndex = bundle.getInt("selected_index");
            if (adapter != null) {
                setText(adapter.get(selectedIndex).toString());
                adapter.notifyItemSelected(selectedIndex);
            }
            if (bundle.getBoolean("is_popup_showing")) {
                if (popupWindow != null) {
                    // Post the show request into the looper to avoid bad token exception
                    post(new Runnable() {

                        @Override
                        public void run() {
                            expand();
                        }
                    });
                }
            }
            savedState = bundle.getParcelable("state");
        }
        super.onRestoreInstanceState(savedState);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (arrowDrawable != null) {
            arrowDrawable.setColorFilter(enabled ? arrowColor : arrowColorDisabled, PorterDuff.Mode.SRC_IN);
        }
    }

    /**
     * @return the selected item position
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Set the default spinner item using its index
     *
     * @param position the item's position
     */
    public void setSelectedIndex(int position) {
        if (adapter != null) {
            if (position >= 0 && position <= adapter.getCount()) {
                adapter.notifyItemSelected(position);
                selectedIndex = position;
                setText(adapter.get(position).toString());
            } else {
                throw new IllegalArgumentException("Position must be lower than adapter count!");
            }
        }
    }

    /**
     * Register a callback to be invoked when an item in the dropdown is selected.
     *
     * @param onItemSelectedListener The callback that will run
     */
    public void setOnItemSelectedListener(@Nullable OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    /**
     * Register a callback to be invoked when the {@link PopupWindow} is shown but the user didn't select an item.
     *
     * @param onNothingSelectedListener the callback that will run
     */
    public void setOnNothingSelectedListener(@Nullable OnNothingSelectedListener onNothingSelectedListener) {
        this.onNothingSelectedListener = onNothingSelectedListener;
    }

    /**
     * Set the dropdown items
     *
     * @param items A list of items
     * @param <T>   The item type
     */
    public <T> void setItems(@NonNull List<T> items) {
        numberOfItems = items.size();
        //adapter = new MaterialSpinnerAdapter<>(getContext(), items).setTextColor(textColor);
        adapter = new MaterialSpinnerAdapter<>(getContext(), items).setTextColor(textColor);
        setAdapterInternal(adapter);
    }

    /**
     * Set the dropdown items
     *
     * @param items A list of items
     * @param <T>   The item type
     */
    public <T> void setItems(@NonNull T... items) {
        setItems(Arrays.asList(items));
    }

    /**
     * Set a custom adapter for the dropdown items
     *
     * @param adapter The list adapter
     */
    public void setAdapter(@NonNull ListAdapter adapter) {
        this.adapter = new MaterialSpinnerAdapterWrapper(getContext(), adapter);
        setAdapterInternal(this.adapter);
    }

    private void setAdapterInternal(@NonNull MaterialSpinnerBaseAdapter adapter) {
        listView.setAdapter(adapter);
        if (selectedIndex >= numberOfItems) {
            selectedIndex = 0;
        }
        setText(adapter.get(selectedIndex).toString());
    }

    /**
     * Show the dropdown menu
     */
    public void expand() {
        if (!hideArrow) {
            animateArrow(true);
        }
        nothingSelected = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popupWindow.setOverlapAnchor(false);
            popupWindow.showAsDropDown(this);
        } else {
            int[] location = new int[2];
            getLocationOnScreen(location);
            int x = location[0];
            int y = getHeight() + location[1];
            popupWindow.showAtLocation(this, Gravity.TOP | Gravity.START, x, y);
        }
    }

    /**
     * Closes the dropdown menu
     */
    public void collapse() {
        if (!hideArrow) {
            animateArrow(false);
        }
        popupWindow.dismiss();
    }

    /**
     * Set the tint color for the dropdown arrow
     *
     * @param color the color value
     */
    public void setArrowColor(@ColorInt int color) {
        arrowColor = color;
        arrowColorDisabled = Utils.lighter(arrowColor, 0.8f);
        if (arrowDrawable != null) {
            arrowDrawable.setColorFilter(arrowColor, PorterDuff.Mode.SRC_IN);
        }
    }

    private void animateArrow(boolean shouldRotateUp) {
        int start = shouldRotateUp ? 0 : 10000;
        int end = shouldRotateUp ? 10000 : 0;
        ObjectAnimator animator = ObjectAnimator.ofInt(arrowDrawable, "level", start, end);
        animator.start();
    }

    /**
     * Set the maximum height of the dropdown menu.
     *
     * @param height the height in pixels
     */
    public void setDropdownMaxHeight(int height) {
        popupWindowMaxHeight = height;
        popupWindow.setHeight(calculatePopupWindowHeight());
    }

    /**
     * Set the height of the dropdown menu
     *
     * @param height the height in pixels
     */
    public void setDropdownHeight(int height) {
        popupWindowHeight = height;
        popupWindow.setHeight(calculatePopupWindowHeight());
    }

    private int calculatePopupWindowHeight() {
        if (adapter == null) {
            return WindowManager.LayoutParams.WRAP_CONTENT;
        }
        float listViewHeight = adapter.getCount() * getResources().getDimension(R.dimen.ms__item_height);

        Log.i("mytest","listViewHeight,popupWindowMaxHeight=="+listViewHeight+","+popupWindowMaxHeight);
        if (popupWindowMaxHeight > 0 && listViewHeight > popupWindowMaxHeight) {
            return popupWindowMaxHeight;
        } else if (popupWindowHeight != WindowManager.LayoutParams.MATCH_PARENT
                && popupWindowHeight != WindowManager.LayoutParams.WRAP_CONTENT
                && popupWindowHeight <= listViewHeight) {
            return popupWindowHeight;
        }
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * Get the {@link PopupWindow}.
     *
     * @return The {@link PopupWindow} that is displayed when the view has been clicked.
     */
    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    /**
     * Interface definition for a callback to be invoked when an item in this view has been selected.
     *
     * @param <T> Adapter item type
     */
    public interface OnItemSelectedListener<T> {

        /**
         * <p>Callback method to be invoked when an item in this view has been selected. This callback is invoked only when
         * the newly selected position is different from the previously selected position or if there was no selected
         * item.</p>
         *
         * @param view     The {@link MaterialSpinner} view
         * @param position The position of the view in the adapter
         * @param id       The row id of the item that is selected
         * @param item     The selected item
         */
        void onItemSelected(MaterialSpinner view, int position, long id, T item);

    }

    /**
     * Interface definition for a callback to be invoked when the dropdown is dismissed and no item was selected.
     */
    public interface OnNothingSelectedListener {

        /**
         * Callback method to be invoked when the {@link PopupWindow} is dismissed and no item was selected.
         *
         * @param spinner the {@link MaterialSpinner}
         */
        void onNothingSelected(MaterialSpinner spinner);
    }

    public static class MaterialSpinnerAdapter<T> extends MaterialSpinnerBaseAdapter {

        private final List<T> items;

        public MaterialSpinnerAdapter(Context context, List<T> items) {
            super(context);
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size() - 1;
        }

        @Override
        public T getItem(int position) {
            if (position >= getSelectedIndex()) {
                return items.get(position + 1);
            } else {
                return items.get(position);
            }
        }

        @Override
        public T get(int position) {
            return items.get(position);
        }

    }

    static final class MaterialSpinnerAdapterWrapper extends MaterialSpinnerBaseAdapter {

        private final ListAdapter listAdapter;

        public MaterialSpinnerAdapterWrapper(Context context, ListAdapter toWrap) {
            super(context);
            listAdapter = toWrap;
        }

        @Override
        public int getCount() {
            return listAdapter.getCount() - 1;
        }

        @Override
        public Object getItem(int position) {
            if (position >= getSelectedIndex()) {
                return listAdapter.getItem(position + 1);
            } else {
                return listAdapter.getItem(position);
            }
        }

        @Override
        public Object get(int position) {
            return listAdapter.getItem(position);
        }

    }

    public abstract static class MaterialSpinnerBaseAdapter<T> extends BaseAdapter {

        private final Context context;
        private int selectedIndex;
        private int textColor;

        public MaterialSpinnerBaseAdapter(Context context) {
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final TextView textView;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.ms__list_item, parent, false);
                textView = (TextView) convertView.findViewById(R.id.tv_tinted_spinner);
                textView.setTextColor(textColor);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    Configuration config = context.getResources().getConfiguration();
                    if (config.getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        textView.setTextDirection(TEXT_DIRECTION_RTL);
                    }
                }
                convertView.setTag(new ViewHolder(textView));
            } else {
                textView = ((ViewHolder) convertView.getTag()).textView;
            }
            textView.setText(getItem(position).toString());
            return convertView;
        }

        public int getSelectedIndex() {
            return selectedIndex;
        }

        public void notifyItemSelected(int index) {
            selectedIndex = index;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public abstract T getItem(int position);

        @Override
        public abstract int getCount();

        public abstract T get(int position);

        public MaterialSpinnerBaseAdapter<T> setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        private static class ViewHolder {

            private TextView textView;

            private ViewHolder(TextView textView) {
                this.textView = textView;
            }

        }

    }

    static final class Utils {

        /**
         * Darkens a color by a given factor.
         *
         * @param color
         *     the color to darken
         * @param factor
         *     The factor to darken the color.
         * @return darker version of specified color.
         */
        static int darker(int color, float factor) {
            return Color.argb(Color.alpha(color), Math.max((int) (Color.red(color) * factor), 0),
                    Math.max((int) (Color.green(color) * factor), 0),
                    Math.max((int) (Color.blue(color) * factor), 0));
        }

        /**
         * Lightens a color by a given factor.
         *
         * @param color
         *     The color to lighten
         * @param factor
         *     The factor to lighten the color. 0 will make the color unchanged. 1 will make the
         *     color white.
         * @return lighter version of the specified color.
         */
        static int lighter(int color, float factor) {
            int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
            int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
            int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
            return Color.argb(Color.alpha(color), red, green, blue);
        }

        /**
         * Check if layout direction is RTL
         *
         * @param context
         *     the current context
         * @return {@code true} if the layout direction is right-to-left
         */
        static boolean isRtl(Context context) {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 &&
                    context.getResources().getConfiguration().getLayoutDirection() == LAYOUT_DIRECTION_RTL;
        }

        /**
         * Return a drawable object associated with a particular resource ID.
         *
         * <p>Starting in {@link Build.VERSION_CODES#LOLLIPOP}, the returned drawable will be styled for the
         * specified Context's theme.</p>
         *
         * @param id
         *     The desired resource identifier, as generated by the aapt tool.
         *     This integer encodes the package, type, and resource entry.
         *     The value 0 is an invalid identifier.
         * @return Drawable An object that can be used to draw this resource.
         */
        static Drawable getDrawable(Context context, int id) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return context.getDrawable(id);
            }
            return context.getResources().getDrawable(id);
        }

    }
}
