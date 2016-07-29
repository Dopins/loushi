package com.android.loushi.loushi.ui.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.HotWordRecycleViewAdapter;
import com.android.loushi.loushi.adapter.SearchRecordAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.HotWordJson;
import com.android.loushi.loushi.jsonbean.RecommendJson;
import com.android.loushi.loushi.jsonbean.SearchJson;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.SearchActivity;
import com.android.loushi.loushi.util.SearchWords;
import com.lzy.okhttputils.OkHttpUtils;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dopin on 2016/7/21.
 */
public class SearchFragment extends Fragment {
    List<SearchWords> allNews;
    private RecyclerView hRecyclerView;
    private HotWordRecycleViewAdapter hRecycleAdapter;
    private RecyclerView sRecyclerView;
    private SearchRecordAdapter searchRecordAdapter;

    private LinearLayout linearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        init(view);
        return view;
    }
    private void init(View view){
        SQLiteDatabase db = Connector.getDatabase();
        linearLayout=(LinearLayout)view.findViewById(R.id.search_record_layout);
        setSearchRecode(view);
        setHotWord(view);
        Button btn_clear_search=(Button)view.findViewById(R.id.btn_clear_search);
        btn_clear_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(SearchWords.class);
                linearLayout.setVisibility(View.GONE);
            }
        });


    }
    private void setSearchRecode(View view){
        allNews = DataSupport.findAll(SearchWords.class);
        int len = allNews.size();
        final List<Map<String,String>> search_record_list=new ArrayList<>();
        if(len==0){
            linearLayout.setVisibility(View.GONE);
        }else{
            for(int i=len-1;i>=0&&len-i-1<5;i--){
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
                SearchActivity.editText_search.setSelection(keyword.length());
            }
        });
    }
    private void setHotWord(final View view)
    {
        OkHttpUtils.post(BaseActivity.url + "base/hotword")
                .execute(new JsonCallback<HotWordJson>(HotWordJson.class) {
                    @Override
                    public void onResponse(boolean b,final HotWordJson hotWordJson, Request request, Response response) {
                        if (hotWordJson.getState()) {

                            hRecycleAdapter= new HotWordRecycleViewAdapter(view.getContext(),hotWordJson.getBody());
                            hRecycleAdapter.setOnItemClickListener(new HotWordRecycleViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    String keyword=hotWordJson.getBody().get(position);
                                    SearchActivity.editText_search.setText(keyword);
                                    SearchActivity.editText_search.setSelection(keyword.length());
                                }
                            });
                            hRecyclerView = (RecyclerView)view.findViewById(R.id.hotWordRecycleView);
                            hRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
                            hRecyclerView.setAdapter(hRecycleAdapter);

                        } else {
                            Log.d("error", hotWordJson.getReturn_info());
                        }
                    }
                });
    }

}
