package com.android.loushi.loushi.ui.activity;


import android.app.FragmentTransaction;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;
import com.android.loushi.loushi.R;

import com.android.loushi.loushi.ui.fragment.SearchFragment;
import com.android.loushi.loushi.ui.fragment.SearchResultFragment;
import com.android.loushi.loushi.util.SearchWords;


/**
 * Created by dopin on 2016/7/18.
 */
public class SearchActivity extends BaseActivity {

    public static EditText editText_search;
    static FragmentManager fragmentManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }
    private void init(){
        fragmentManager = getSupportFragmentManager();
        SearchFragment searchFragment=new SearchFragment();
        fragmentManager.beginTransaction().replace(R.id.search_frame, searchFragment).commit();

        editText_search=(EditText)findViewById(R.id.search_word);

        final TextView btn_search=(TextView)findViewById(R.id.search_text);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=btn_search.getText().toString();
                if(str.equals("搜索")){
                    String keyword = editText_search.getText().toString();
                    search(keyword);
                }
               else{
                    finish();
                }
            }
        });

        editText_search.addTextChangedListener(new TextWatcher() {
            boolean flag=false;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    flag=true;//添加搜索关键词
                }else{
                    flag=false;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = editText_search.getText().toString();
                if(keyword.length()!=0) btn_search.setText("取消");
                else btn_search.setText("搜索");
                if(flag) {
                    search(keyword);
                }
            }
        });

    }
    public static void search(String keyword){
        if(!keyword.equals("")){
            if(keyword.length()>10){
                keyword=keyword.substring(0,9);
            }
            SearchWords searchWords = new SearchWords();
            searchWords.setWords(keyword);
            android.text.format.Time time=new android.text.format.Time();
            time.setToNow();
            String date=(time.month+1)+"/"+time.monthDay;
            searchWords.setDate(date);

            searchWords.save();

            SearchResultFragment searchResultFragment = new SearchResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("keyword",keyword);
            searchResultFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.search_frame, searchResultFragment).commit();

        }
    }
}
