package com.android.loushi.loushi.util;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/4/3.
 */
public class SearchWords extends DataSupport {
    private String words;
    private String date;

    public String getWords() {
        return words;
    }
    public String getDate(){
        return date;
    }

    public void setWords(String words) {
        this.words = words;
    }
    public void setDate(String date){
        this.date=date;
    }
}
