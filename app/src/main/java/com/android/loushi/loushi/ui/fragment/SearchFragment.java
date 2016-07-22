package com.android.loushi.loushi.ui.fragment;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.HotWordRecycleViewAdapter;
import com.android.loushi.loushi.adapter.SearchRecordAdapter;
import com.android.loushi.loushi.ui.activity.SearchActivity;
import com.android.loushi.loushi.util.SearchWords;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dopin on 2016/7/21.
 */
public class SearchFragment extends Fragment {
    List<SearchWords> allNews;
    private RecyclerView hRecyclerView;
    private HotWordRecycleViewAdapter hRecycleAdapter;
    private RecyclerView sRecyclerView;
    private SearchRecordAdapter searchRecordAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        init(view);
        return view;
    }
    private void init(View view){
        setSearchRecode(view);
        setHotWord(view);
    }
    private void setSearchRecode(View view){
        SQLiteDatabase db = Connector.getDatabase();
        allNews = DataSupport.findAll(SearchWords.class);
        int len = allNews.size();
        final List<Map<String,String>> search_record_list=new ArrayList<>();
        if(len==0){
            LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.search_record_layout);
            linearLayout.setVisibility(View.GONE);
        }else{
            for(int i=len-1;i>0&&len-i-1<5;i--){
                Map map=new HashMap();
                map.put("record",allNews.get(i).getWords());
                map.put("date", allNews.get(i).getDate());
                search_record_list.add(map);
            }
        }
        searchRecordAdapter=new SearchRecordAdapter(view.getContext(),search_record_list);
        sRecyclerView=(RecyclerView)view.findViewById(R.id.search_record);
        sRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        sRecyclerView.setAdapter(searchRecordAdapter);
        searchRecordAdapter.setOnItemClickListener(new SearchRecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String keyword=search_record_list.get(position).get("record");
                SearchActivity.editText_search.setText(keyword);
            }
        });
    }
    private void setHotWord(View view)
    {
        List<String> data=new ArrayList<>();
        data.add("热搜词1");
        data.add("热搜词2");
        data.add("热搜词3");
        data.add("热搜词4");
        hRecycleAdapter= new HotWordRecycleViewAdapter(view.getContext(),data);
        hRecyclerView = (RecyclerView)view.findViewById(R.id.hotWordRecycleView);
        hRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        hRecyclerView.setAdapter(hRecycleAdapter);

    }

}
