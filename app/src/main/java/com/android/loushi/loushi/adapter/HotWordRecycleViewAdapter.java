package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import java.util.List;

/**
 * Created by dopin on 2016/7/18.
 */
public class HotWordRecycleViewAdapter extends RecyclerView.Adapter<HotWordRecycleViewAdapter.HotwordViewHolder>{

    private Context context;
    private List<String> data;
    public HotWordRecycleViewAdapter(Context context, List<String> data){
        this.context=context;
        this.data=data;
    }
    @Override
        public HotwordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new HotwordViewHolder(LayoutInflater.from(
                context).inflate(R.layout.cardview_hotword, parent,
                false));
    }

    @Override
    public void onBindViewHolder(HotwordViewHolder holder, int position) {
        holder.hotWord.setText(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }
    public static class HotwordViewHolder extends RecyclerView.ViewHolder {
        TextView hotWord;
        public HotwordViewHolder(View itemView) {
            super(itemView);
            hotWord=(TextView)itemView.findViewById(R.id.hot_word);
        }
    }
}
