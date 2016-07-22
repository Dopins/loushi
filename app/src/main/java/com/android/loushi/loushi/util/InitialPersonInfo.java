package com.android.loushi.loushi.util;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/5/7.
 */
public class InitialPersonInfo extends DataSupport {

    private String personinfoString;


    public String getPersoninfoString() {
        return personinfoString;
    }

    public void setPersoninfoString(String personinfoString) {
        this.personinfoString = personinfoString;
    }
}
