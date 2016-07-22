package com.android.loushi.loushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loushi.loushi.R;

import java.util.List;
import java.util.Map;

/**
 * Created by dopin on 2016/7/21.
 */
public class SearchRecordAdapter extends RecyclerView.Adapter<SearchRecordAdapter.SearchRecordViewHolder>{
    private Context context;
    private List<Map<String,String>> data;
    // 添加Item点击事件接口
    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public SearchRecordAdapter(Context context,  List<Map<String,String>> data){
        this.context=context;
        this.data=data;
    }
    @Override
    public SearchRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new SearchRecordViewHolder(LayoutInflater.from(
                context).inflate(R.layout.search_record, parent,
                false));
    }

    @Override
    public void onBindViewHolder(SearchRecordViewHolder holder, int position) {

        holder.searchRecordText.setText(data.get(position).get("record"));
        holder.dateText.setText(data.get(position).get("date"));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public class SearchRecordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView searchRecordText;
        TextView dateText;
        public SearchRecordViewHolder(View itemView) {
            super(itemView);
            searchRecordText=(TextView)itemView.findViewById(R.id.search_record_text);
            dateText=(TextView)itemView.findViewById(R.id.date);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getPosition());
            }
        }
    }
}
