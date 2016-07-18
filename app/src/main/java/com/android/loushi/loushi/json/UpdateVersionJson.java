package com.android.loushi.loushi.json;

/**
 * Created by Administrator on 2016/5/9.
 */
public class UpdateVersionJson {

    /**
     * body : <?xml version="1.0" encoding="UTF-8"?>
     <info>
     <version>1.0</version>
     <url></url>
     <description>检测到新版本，跟随小陋来一波更新吧~</description>
     </info>
     * code : null
     * return_info : null
     * state : true
     */

    private String body;
    private Object code;
    private Object return_info;
    private boolean state;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getReturn_info() {
        return return_info;
    }

    public void setReturn_info(Object return_info) {
        this.return_info = return_info;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
