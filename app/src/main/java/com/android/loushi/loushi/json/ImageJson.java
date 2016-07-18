package com.android.loushi.loushi.json;

/**
 * Created by Administrator on 2016/4/30.
 */
public class ImageJson {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : lianjie
     */

    private Boolean state;
    private Object code;
    private Object return_info;
    private String body;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getReturn_info() {
        return return_info;
    }

    public void setReturn_info(String return_info) {
        this.return_info = return_info;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
