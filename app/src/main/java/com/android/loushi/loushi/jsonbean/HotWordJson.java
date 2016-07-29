package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dopin on 2016/7/29.
 */
public class HotWordJson implements Serializable {
    private boolean state;
    private String code;
    private String return_info;

    private List<String> body;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReturn_info() {
        return return_info;
    }

    public void setReturn_info(String return_info) {
        this.return_info = return_info;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }

}
